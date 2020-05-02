package com.brobot.brobotREST.brobots.BDM.GameStates.MenuAndQuests.MenuStates.Talent;

import com.brobot.brobotREST.brobots.BDM.GameStates.StateEnumsBDM;
import com.brobot.brobotREST.database.state.StateData;
import com.brobot.brobotREST.database.state.StateRIPData;
import com.brobot.brobotREST.stateObjects.builders.StateDirector;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
public @Data class Talent {

    private StateData state;
    private StateRIPData talentIcon;
    private StateRIPData talenStateText;
    private StateRIPData alleTalentbucherVerwenden;
    private StateRIPData verwendenBestatigung;

    public Talent(StateDirector stateDirector) {

        state = stateDirector
                .initState(StateEnumsBDM.TALENT)
                .addStateText("Talent")
                .build();
        talentIcon = stateDirector
                .addStateRIP("talentIcon")
                .build();
        talenStateText = stateDirector
                .addStateRIP("talenStateText")
                .build();
        alleTalentbucherVerwenden = stateDirector
                .addStateRIP("alleTalentbucherVerwenden","alleTalentbucherVerwenden2")
                .build();
        //verwendenBestatigung = stateDirector
        //        .addBestatigung()
        //        .build();
        stateDirector.save();
    }

}