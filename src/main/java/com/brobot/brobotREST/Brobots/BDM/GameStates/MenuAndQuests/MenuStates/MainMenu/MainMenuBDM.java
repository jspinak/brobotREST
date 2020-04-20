package com.brobot.brobotREST.Brobots.BDM.GameStates.MenuAndQuests.MenuStates.MainMenu;

import com.brobot.brobotREST.Brobots.BDM.GameStates.GameStateEnumsBDM;
import com.brobot.brobotREST.StateObjects.State.State;
import com.brobot.brobotREST.Brobots.BDM.StateBuilders.GameStateDirectorBDM;
import com.brobot.brobotREST.StateObjects.BaseObjects.RIP.StateRIP;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
public @Data class MainMenuBDM {

    private State mainMenu;
    private StateRIP open;
    private StateRIP mailButton;
    private StateRIP loginRewardsIcon;
    private StateRIP eventRewardsIcon;
    private StateRIP petIcon;

    private State mainMenuOpen;
    private StateRIP menuExists;
    private StateRIP arenaIcon;
    private StateRIP gildeIcon;
    private StateRIP schwarzgeistKnopf;
    private StateRIP lexikonIcon;

    public MainMenuBDM(GameStateDirectorBDM gameStateDirectorBDM) {

        mainMenu = gameStateDirectorBDM
                .init(GameStateEnumsBDM.MAIN_MENU)
                .build();
        open = gameStateDirectorBDM
                .addStateRIP("optionsButton","optionsButton2")
                .addStatesToActivate(GameStateEnumsBDM.MAIN_MENU_OPEN)
                .build();
        mailButton = gameStateDirectorBDM
                .addStateRIP("mailButton")
                .addStatesToActivate(GameStateEnumsBDM.MAIL)
                .build();
        loginRewardsIcon = gameStateDirectorBDM
                .addStateRIP("loginRewardIcon","loginRewardIcon2")
                .addStatesToActivate(GameStateEnumsBDM.LOGIN_REWARDS)
                .build();
        eventRewardsIcon = gameStateDirectorBDM
                .addStateRIP("dailyRewardButton")
                .addStatesToActivate(GameStateEnumsBDM.EVENT_REWARDS)
                .build();
        petIcon = gameStateDirectorBDM
                .addStateRIP("dog") //add others here
                .addStatesToActivate(GameStateEnumsBDM.PETS)
                .build();

        mainMenuOpen = gameStateDirectorBDM
                .init(GameStateEnumsBDM.MAIN_MENU_OPEN)
                .addStateText("Black Desert","Mobile")
                .build();
        menuExists = gameStateDirectorBDM
                .addStateRIP("mainMenuStateImage")
                .build();
        arenaIcon = gameStateDirectorBDM
                .addStateRIP("arenaIcon")
                .addStatesToActivate(GameStateEnumsBDM.ARENA_EMPFANG)
                .build();
        gildeIcon = gameStateDirectorBDM
                .addStateRIP("gildeIcon")
                .addStatesToActivate(GameStateEnumsBDM.GILDE)
                .build();
        schwarzgeistKnopf = gameStateDirectorBDM
                .addStateRIP("schwarzgeistKnopf")
                .addStatesToActivate(GameStateEnumsBDM.SCHWARZGEIST_EMPFANG)
                .build();
        lexikonIcon = gameStateDirectorBDM
                .addStateRIP("lexikonIcon")
                .addStatesToActivate(GameStateEnumsBDM.LEXIKON)
                .build();
    }

}
