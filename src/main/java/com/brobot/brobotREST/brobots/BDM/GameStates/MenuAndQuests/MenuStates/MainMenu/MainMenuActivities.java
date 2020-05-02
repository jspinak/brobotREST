package com.brobot.brobotREST.brobots.BDM.GameStates.MenuAndQuests.MenuStates.MainMenu;

import com.brobot.brobotREST.stateObjects.objectMethods.AllStateObjectActions;
import org.springframework.stereotype.Component;

@Component
public class MainMenuActivities {

    private final MainMenuBDM mainMenuBDM;
    private final AllStateObjectActions we;

    public MainMenuActivities(MainMenuBDM mainMenuBDM, AllStateObjectActions we) {
        this.mainMenuBDM = mainMenuBDM;
        this.we = we;
    }

}
