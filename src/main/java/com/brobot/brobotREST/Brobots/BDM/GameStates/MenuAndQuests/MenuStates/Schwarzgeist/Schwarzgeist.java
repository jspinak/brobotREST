package com.brobot.brobotREST.Brobots.BDM.GameStates.MenuAndQuests.MenuStates.Schwarzgeist;

import com.brobot.brobotREST.Brobots.BDM.GameStates.GameStateEnumsBDM;
import com.brobot.brobotREST.StateObjects.State.State;
import com.brobot.brobotREST.Brobots.BDM.StateBuilders.GameStateDirectorBDM;
import com.brobot.brobotREST.StateObjects.BaseObjects.Image.StateImage;
import com.brobot.brobotREST.StateObjects.BaseObjects.RIP.StateRIP;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
public @Data class Schwarzgeist {

    private State empfang;
    private StateRIP schwarzgeistSymbol;
    private StateRIP schwarzenergie;
    private StateRIP schwarzgeistQuest;

    private State geistFuttern;
    private StateRIP autoAuswahl;
    private StateRIP energieAbsorbieren;
    private StateRIP schmuckTab;
    private StateRIP energieAbsorbierenBestatigen;

    private State quests;
    private StateImage questAnnehmen;
    private StateRIP questAnnehmenBestatigen;

    public Schwarzgeist(GameStateDirectorBDM gameStateDirectorBDM) {

        empfang = gameStateDirectorBDM
                .init(GameStateEnumsBDM.SCHWARZGEIST_EMPFANG)
                .addStateText("VP")
                .build();
        schwarzgeistSymbol = gameStateDirectorBDM
                .addStateRIP("schwarzgeistSymbol")
                .build();
        schwarzenergie = gameStateDirectorBDM
                .addStateRIP("schwarzenergie","schwarzenergieKnopf")
                .addStatesToActivate(GameStateEnumsBDM.GEIST_FUTTERN)
                .build();
        schwarzgeistQuest = gameStateDirectorBDM
                .addStateRIP("schwarzgeistQuest")
                .addStatesToActivate(GameStateEnumsBDM.SCHWARZGEIST_QUESTS)
                .build();

        geistFuttern = gameStateDirectorBDM
                .init(GameStateEnumsBDM.GEIST_FUTTERN)
                .build();
        autoAuswahl = gameStateDirectorBDM
                .addStateRIP("autoAuswahl")
                .build();
        energieAbsorbieren = gameStateDirectorBDM
                .addStateRIP("energieAbsorbieren")
                .build();
        schmuckTab = gameStateDirectorBDM
                .addStateRIP("schmuckTab")
                .build();
        energieAbsorbierenBestatigen = gameStateDirectorBDM
                .addBestatigung()
                .build();

        quests = gameStateDirectorBDM
                .init(GameStateEnumsBDM.SCHWARZGEIST_QUESTS)
                .build();
        questAnnehmen = gameStateDirectorBDM
                .addStateImage("questAnnehmen")
                .build();
        questAnnehmenBestatigen = gameStateDirectorBDM
                .addBestatigung()
                .build();
    }

}