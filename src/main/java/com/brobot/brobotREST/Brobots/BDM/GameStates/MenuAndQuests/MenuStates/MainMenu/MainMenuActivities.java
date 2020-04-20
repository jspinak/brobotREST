package com.brobot.brobotREST.Brobots.BDM.GameStates.MenuAndQuests.MenuStates.MainMenu;

import com.brobot.brobotREST.StateObjects.ObjectMethods.AllGameObjectActions;
import org.springframework.stereotype.Component;

@Component
public class MainMenuActivities {

    private final MainMenuBDM mainMenuBDM;
    private final AllGameObjectActions we;

    public MainMenuActivities(MainMenuBDM mainMenuBDM, AllGameObjectActions we) {
        this.mainMenuBDM = mainMenuBDM;
        this.we = we;
    }

}
