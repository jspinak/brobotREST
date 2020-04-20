package com.brobot.brobotREST.Brobots.BDM.GameStates.Rewards.LoginRewards;

import com.brobot.brobotREST.StateObjects.ObjectMethods.AllGameObjectActions;
import org.springframework.stereotype.Component;

@Component
public class LoginRewardsActivities {

    private final LoginRewards loginRewards;
    private final AllGameObjectActions we;

    public LoginRewardsActivities(LoginRewards loginRewards, AllGameObjectActions we) {

        this.loginRewards = loginRewards;
        this.we = we;
    }

    public boolean belohnungenAbholen() {
        System.out.print(" get daily login rewards. ");
        we.clickUntil().objectDisappears(loginRewards.getErhalten(), 6);
        return true;
    }

}