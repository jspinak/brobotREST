package com.brobot.brobotREST.brobots.BDM.GameStates.Rewards.LoginRewards;

import com.brobot.brobotREST.brobots.BDM.GameStates.StateEnumsBDM;
import com.brobot.brobotREST.database.state.StateData;
import com.brobot.brobotREST.database.state.StateRIPData;
import com.brobot.brobotREST.stateObjects.builders.StateDirector;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
public @Data class LoginRewards {

    private StateData state;
    private StateRIPData loginRewardPageIcon;
    //private StateRIPData erhalten;
    private StateRIPData closeLoginRewards;

    public LoginRewards(StateDirector stateDirector) {

        state = stateDirector
                .initState(StateEnumsBDM.LOGIN_REWARDS)
                .addStateText("Anwesenheitsbelohnung","Login-Belohnung")
                .build();
        loginRewardPageIcon = stateDirector
                .addStateRIP("loginRewardPageIcon") //state identifying image
                .build();
        //erhalten = stateDirector
        //        .addBestatigung()
        //        .build();
        closeLoginRewards = stateDirector
                .addStateRIP("closeLoginRewards")
                .addStatesToActivate(StateEnumsBDM.WORLD)
                .build();
        stateDirector.save();
    }


}