package com.brobot.brobotREST.brobots.BDM.GameStates.Rewards.LoginRewards;

import com.brobot.brobotREST.stateData.wrappers.StateObjectWrapperFactory;
import com.brobot.brobotREST.stateData.wrappers.StateRIPWrapper;
import com.brobot.brobotREST.stateObjects.objectMethods.AllStateObjectActions;
import org.springframework.stereotype.Component;

@Component
public class LoginRewardsActivities {

    private final LoginRewards loginRewards;
    private final AllStateObjectActions we;

    private StateRIPWrapper erhalten;

    public LoginRewardsActivities(LoginRewards loginRewards, AllStateObjectActions we,
                                  StateObjectWrapperFactory stateObjectWrapperFactory) {

        this.loginRewards = loginRewards;
        this.we = we;

        //erhalten = stateObjectWrapperFactory.buildRIPWrapper(loginRewards.getErhalten());
    }

    public boolean belohnungenAbholen() {
        System.out.print(" get daily login rewards. ");
        we.clickUntil().objectDisappears(erhalten, 6);
        return true;
    }

}