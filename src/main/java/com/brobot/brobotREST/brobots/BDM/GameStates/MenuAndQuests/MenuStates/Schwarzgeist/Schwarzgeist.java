package com.brobot.brobotREST.brobots.BDM.GameStates.MenuAndQuests.MenuStates.Schwarzgeist;

import com.brobot.brobotREST.brobots.BDM.GameStates.StateEnumsBDM;
import com.brobot.brobotREST.database.state.StateData;
import com.brobot.brobotREST.database.state.StateRIPData;
import com.brobot.brobotREST.database.state.StateImageData;
import com.brobot.brobotREST.stateObjects.builders.StateDirector;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
public @Data class Schwarzgeist {

    private StateData empfang;
    private StateRIPData schwarzgeistSymbol;
    private StateRIPData schwarzenergie;
    private StateRIPData schwarzgeistQuest;

    private StateData geistFuttern;
    private StateRIPData autoAuswahl;
    private StateRIPData energieAbsorbieren;
    private StateRIPData schmuckTab;
    //private StateRIPData energieAbsorbierenBestatigen;

    private StateData quests;
    private StateImageData questAnnehmen;
    //private StateRIPData questAnnehmenBestatigen;

    public Schwarzgeist(StateDirector stateDirector) {

        empfang = stateDirector
                .initState(StateEnumsBDM.SCHWARZGEIST_EMPFANG)
                .addStateText("VP")
                .build();
        schwarzgeistSymbol = stateDirector
                .addStateRIP("schwarzgeistSymbol")
                .build();
        schwarzenergie = stateDirector
                .addStateRIP("schwarzenergie","schwarzenergieKnopf")
                .addStatesToActivate(StateEnumsBDM.GEIST_FUTTERN)
                .build();
        schwarzgeistQuest = stateDirector
                .addStateRIP("schwarzgeistQuest")
                .addStatesToActivate(StateEnumsBDM.SCHWARZGEIST_QUESTS)
                .build();
        stateDirector.save();

        geistFuttern = stateDirector
                .initState(StateEnumsBDM.GEIST_FUTTERN)
                .build();
        autoAuswahl = stateDirector
                .addStateRIP("autoAuswahl")
                .build();
        energieAbsorbieren = stateDirector
                .addStateRIP("energieAbsorbieren")
                .build();
        schmuckTab = stateDirector
                .addStateRIP("schmuckTab")
                .build();
        //energieAbsorbierenBestatigen = stateDirector
        //        .addBestatigung()
        //        .build();
        stateDirector.save();

        quests = stateDirector
                .initState(StateEnumsBDM.SCHWARZGEIST_QUESTS)
                .build();
        questAnnehmen = stateDirector
                .addStateImage("questAnnehmen")
                .build();
        //questAnnehmenBestatigen = stateDirector
        //        .addBestatigung()
        //        .build();
        stateDirector.save();
    }

}