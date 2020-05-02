package com.brobot.brobotREST.brobots.BDM.GameStates.Camp.Posten;

import com.brobot.brobotREST.brobots.BDM.GameStates.StateEnumsBDM;
import com.brobot.brobotREST.brobots.BDM.StateBuilders.StateDirectorBDM;
import com.brobot.brobotREST.database.state.StateData;
import com.brobot.brobotREST.database.state.StateRIPData;
import com.brobot.brobotREST.stateObjects.builders.StateDirector;
import com.brobot.brobotREST.database.state.StateImageData;
import com.brobot.brobotREST.database.state.StateStringData;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
public @Data class Posten {

    private StateData postenOffnenBestatigung;
    private StateData postenCloseBestatigung;

    private StateData posten;
    private StateRIPData lokalerRuf;
    private StateRIPData exitPosten;
    private StateImageData postenReward;
    private StateImageData handelFertig;
    //private StateRIP uberblickKnopf;//("uberblickKnopf");
    //private StateRIP arbeiterKnopf;//("arbeiterKnopf");

    private StateData postenGebaude;
    private StateRIPData handelAnfangen;
    private StateStringData escPostenGebaude;

    private StateData postenVerwalten;
    private StateImageData postenErhalten;
    private StateImageData entsenden;
    private StateStringData escPostenVerwalten;

    private StateData arbeiterEntsenden;
    private StateRIPData autoAuswahl;
    private StateRIPData arbeiterSenden;
    private StateStringData escArbeiterEntsenden;

    private StateData ergebnisDesHandelsState;
    private StateRIPData ergeibnisDesPostenhandels;
    private StateRIPData druckHier;

    public Posten(StateDirector stateDirector, StateDirectorBDM directorBDM) {

        postenOffnenBestatigung = directorBDM.newBestatigung(
                StateEnumsBDM.POSTEN_OFFNEN_BESTATIGUNG,
                StateEnumsBDM.POSTEN,
                StateEnumsBDM.POSTEN_GEBAUDE);

        postenCloseBestatigung = directorBDM.newBestatigung(
                StateEnumsBDM.POSTEN_CLOSE_BESTATIGUNG,
                StateEnumsBDM.POSTEN,
                StateEnumsBDM.ERGEBNIS_DES_POSTEN_HANDELS);

        posten = stateDirector
                .initState(StateEnumsBDM.POSTEN)
                .addStateText("Lokaler","Ruf")
                .build();
        lokalerRuf = stateDirector
                .addStateRIP("lokalerRuf")
                .build();
        exitPosten = stateDirector
                .addStateRIP("exitPosten")
                .addStatesToActivate(StateEnumsBDM.POSTEN_CLOSE_BESTATIGUNG)
                .build();
        postenReward = stateDirector
                .addStateImage("postenReward")
                .build();
        handelFertig = stateDirector
                .addStateImage("handelFertig")
                .build();
        stateDirector.save();

        postenGebaude = stateDirector
                .initState(StateEnumsBDM.POSTEN_GEBAUDE)
                .addStateText("Posten")
                .build();
        handelAnfangen = stateDirector
                .addStateRIP("handelAnfangen")
                .addStatesToActivate(StateEnumsBDM.POSTEN_OFFNEN_BESTATIGUNG)
                .build();
        escPostenGebaude = stateDirector
                .addESC(StateEnumsBDM.CAMP);
        stateDirector.save();

        postenVerwalten = stateDirector
                .initState(StateEnumsBDM.POSTEN_VERWALTEN)
                .build();
        postenErhalten = stateDirector
                .addStateImage("postenErhalten")
                .build();
        entsenden = stateDirector
                .addStateImage("entsenden")
                .addStatesToActivate(StateEnumsBDM.ARBEITER_ENTSENDEN)
                .build();
        escPostenVerwalten = stateDirector
                .addESC(StateEnumsBDM.POSTEN);
        stateDirector.save();

        arbeiterEntsenden = stateDirector
                .initState(StateEnumsBDM.ARBEITER_ENTSENDEN)
                .build();
        autoAuswahl = stateDirector
                .addStateRIP("postenAutoAuswahl")
                .build();
        arbeiterSenden = stateDirector
                .addStateRIP("postenArbeiterSenden")
                .addStatesToActivate(StateEnumsBDM.POSTEN_VERWALTEN)
                .build();
        escArbeiterEntsenden = stateDirector
                .addESC(StateEnumsBDM.POSTEN);
        stateDirector.save();

        ergebnisDesHandelsState = stateDirector
                .initState(StateEnumsBDM.ERGEBNIS_DES_POSTEN_HANDELS)
                .addStateText("Postenhandels")
                .build();
        ergeibnisDesPostenhandels = stateDirector
                .addStateRIP("ergeibnisDesPostenhandels")
                .build();
        druckHier = stateDirector
                .addStateRIP("druckHier") // "druckHier" !!!!!!!!! need to make image
                .setName("close")
                .addStatesToActivate(StateEnumsBDM.CAMP)
                .build();
        stateDirector.save();
    }


}