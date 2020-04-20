package com.brobot.brobotREST.Brobots.BDM.GameStates.Rewards.LoginRewards;

import com.brobot.brobotREST.Brobots.BDM.GameStates.GameStateEnumsBDM;
import com.brobot.brobotREST.StateObjects.State.State;
import com.brobot.brobotREST.Brobots.BDM.StateBuilders.GameStateDirectorBDM;
import com.brobot.brobotREST.StateObjects.BaseObjects.RIP.StateRIP;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
public @Data class LoginRewards {

    private State state;
    private StateRIP loginRewardPageIcon;
    private StateRIP erhalten;
    private StateRIP closeLoginRewards;

    public LoginRewards(GameStateDirectorBDM gameStateDirectorBDM) {

        state = gameStateDirectorBDM
                .init(GameStateEnumsBDM.LOGIN_REWARDS)
                .addStateText("Anwesenheitsbelohnung","Login-Belohnung")
                .build();
        loginRewardPageIcon = gameStateDirectorBDM
                .addStateRIP("loginRewardPageIcon") //state identifying image
                .build();
        erhalten = gameStateDirectorBDM
                .addBestatigung()
                .build();
        closeLoginRewards = gameStateDirectorBDM
                .addStateRIP("closeLoginRewards")
                .addStatesToActivate(GameStateEnumsBDM.WORLD)
                .build();
    }


}