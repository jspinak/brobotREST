package com.brobot.brobotREST.Brobots.BDM.GameStates.Camp.Posten;

import com.brobot.brobotREST.Brobots.BDM.GameStates.GameStateEnumsBDM;
import com.brobot.brobotREST.Brobots.BDM.StateBuilders.GameObjectBuilderBDM;
import com.brobot.brobotREST.Brobots.BDM.StateBuilders.GameStateDirectorBDM;
import com.brobot.brobotREST.StateObjects.State.*;
import com.brobot.brobotREST.StateObjects.BaseObjects.Image.StateImage;
import com.brobot.brobotREST.StateObjects.BaseObjects.RIP.StateRIP;
import com.brobot.brobotREST.StateObjects.BaseObjects.String.StateString;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
public @Data class Posten {

    private State postenOffnenBestatigung;
    private State postenCloseBestatigung;

    private State posten;
    private StateRIP lokalerRuf;
    private StateRIP exitPosten;
    private StateImage postenReward;
    private StateImage handelFertig;
    //private StateRIP uberblickKnopf;//("uberblickKnopf");
    //private StateRIP arbeiterKnopf;//("arbeiterKnopf");

    private State postenGebaude;
    private StateRIP handelAnfangen;
    private StateString escPostenGebaude;

    private State postenVerwalten;
    private StateImage postenErhalten;
    private StateImage entsenden;
    private StateString escPostenVerwalten;

    private State arbeiterEntsenden;
    private StateRIP autoAuswahl;
    private StateRIP arbeiterSenden;
    private StateString escArbeiterEntsenden;

    private State ergebnisDesHandelsState;
    private StateRIP ergeibnisDesPostenhandels;
    private StateRIP druckHier;

    public Posten(GameStateDirectorBDM gameStateDirectorBDM, GameObjectBuilderBDM gameObjectBuilder) {

        postenOffnenBestatigung = gameObjectBuilder.newBestatigung(GameStateEnumsBDM.POSTEN_OFFNEN_BESTATIGUNG)
                .addStatesToActivateToRIP(GameStateEnumsBDM.POSTEN)
                .addStatesToActivateToESC(GameStateEnumsBDM.POSTEN_GEBAUDE)
                .setSecondsToWaitAfterClick(10.0)
                .build();

        postenCloseBestatigung = gameObjectBuilder.newBestatigung(GameStateEnumsBDM.POSTEN_CLOSE_BESTATIGUNG)
                .addStatesToActivateToESC(GameStateEnumsBDM.POSTEN)
                .addStatesToActivateToRIP(GameStateEnumsBDM.ERGEBNIS_DES_POSTEN_HANDELS)
                .setSecondsToWaitAfterClick(5.0)
                .build();

        posten = gameStateDirectorBDM
                .init(GameStateEnumsBDM.POSTEN)
                .addStateText("Lokaler","Ruf")
                .build();
        lokalerRuf = gameStateDirectorBDM
                .addStateRIP("lokalerRuf")
                .build();
        exitPosten = gameStateDirectorBDM
                .addStateRIP("exitPosten")
                .addStatesToActivate(GameStateEnumsBDM.POSTEN_CLOSE_BESTATIGUNG)
                .build();
        postenReward = gameStateDirectorBDM
                .addStateImage("postenReward")
                .build();
        handelFertig = gameStateDirectorBDM
                .addStateImage("handelFertig")
                .build();

        postenGebaude = gameStateDirectorBDM
                .init(GameStateEnumsBDM.POSTEN_GEBAUDE)
                .addStateText("Posten")
                .build();
        handelAnfangen = gameStateDirectorBDM
                .addStateRIP("handelAnfangen")
                .addStatesToActivate(GameStateEnumsBDM.POSTEN_OFFNEN_BESTATIGUNG)
                .build();
        escPostenGebaude = gameStateDirectorBDM
                .escActivates(GameStateEnumsBDM.CAMP);

        postenVerwalten = gameStateDirectorBDM
                .init(GameStateEnumsBDM.POSTEN_VERWALTEN)
                .build();
        postenErhalten = gameStateDirectorBDM
                .addStateImage("postenErhalten")
                .build();
        entsenden = gameStateDirectorBDM
                .addStateImage("entsenden")
                .addStatesToActivate(GameStateEnumsBDM.ARBEITER_ENTSENDEN)
                .build();
        escPostenVerwalten = gameStateDirectorBDM
                .escActivates(GameStateEnumsBDM.POSTEN);

        arbeiterEntsenden = gameStateDirectorBDM
                .init(GameStateEnumsBDM.ARBEITER_ENTSENDEN)
                .build();
        autoAuswahl = gameStateDirectorBDM
                .addStateRIP("postenAutoAuswahl")
                .build();
        arbeiterSenden = gameStateDirectorBDM
                .addStateRIP("postenArbeiterSenden")
                .addStatesToActivate(GameStateEnumsBDM.POSTEN_VERWALTEN)
                .build();
        escArbeiterEntsenden = gameStateDirectorBDM
                .escActivates(GameStateEnumsBDM.POSTEN);

        ergebnisDesHandelsState = gameStateDirectorBDM
                .init(GameStateEnumsBDM.ERGEBNIS_DES_POSTEN_HANDELS)
                .addStateText("Postenhandels")
                .build();
        ergeibnisDesPostenhandels = gameStateDirectorBDM
                .addStateRIP("ergeibnisDesPostenhandels")
                .build();
        druckHier = gameStateDirectorBDM
                .addStateRIP("druckHier") // "druckHier" !!!!!!!!! need to make image
                .setName("close")
                .addStatesToActivate(GameStateEnumsBDM.CAMP)
                .build();
    }


}