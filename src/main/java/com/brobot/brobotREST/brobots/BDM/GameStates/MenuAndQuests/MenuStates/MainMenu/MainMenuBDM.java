package com.brobot.brobotREST.brobots.BDM.GameStates.MenuAndQuests.MenuStates.MainMenu;

import com.brobot.brobotREST.brobots.BDM.GameStates.StateEnumsBDM;
import com.brobot.brobotREST.database.state.StateData;
import com.brobot.brobotREST.database.state.StateRIPData;
import com.brobot.brobotREST.stateObjects.builders.StateDirector;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
public @Data class MainMenuBDM {

    private StateData mainMenu;
    private StateRIPData open;
    private StateRIPData mailButton;
    private StateRIPData loginRewardsIcon;
    private StateRIPData eventRewardsIcon;
    private StateRIPData petIcon;

    private StateData mainMenuOpen;
    private StateRIPData menuExists;
    private StateRIPData arenaIcon;
    private StateRIPData gildeIcon;
    private StateRIPData schwarzgeistKnopf;
    private StateRIPData lexikonIcon;

    public MainMenuBDM(StateDirector stateDirector) {

        mainMenu = stateDirector
                .initState(StateEnumsBDM.MAIN_MENU)
                .build();
        open = stateDirector
                .addStateRIP("optionsButton","optionsButton2")
                .addStatesToActivate(StateEnumsBDM.MAIN_MENU_OPEN)
                .build();
        mailButton = stateDirector
                .addStateRIP("mailButton")
                .addStatesToActivate(StateEnumsBDM.MAIL)
                .build();
        loginRewardsIcon = stateDirector
                .addStateRIP("loginRewardIcon","loginRewardIcon2")
                .addStatesToActivate(StateEnumsBDM.LOGIN_REWARDS)
                .build();
        eventRewardsIcon = stateDirector
                .addStateRIP("dailyRewardButton")
                .addStatesToActivate(StateEnumsBDM.EVENT_REWARDS)
                .build();
        petIcon = stateDirector
                .addStateRIP("dog") //add others here
                .addStatesToActivate(StateEnumsBDM.PETS)
                .build();
        stateDirector.save();

        mainMenuOpen = stateDirector
                .initState(StateEnumsBDM.MAIN_MENU_OPEN)
                .addStateText("Black Desert","Mobile")
                .build();
        menuExists = stateDirector
                .addStateRIP("mainMenuStateImage")
                .build();
        arenaIcon = stateDirector
                .addStateRIP("arenaIcon")
                .addStatesToActivate(StateEnumsBDM.ARENA_EMPFANG)
                .build();
        gildeIcon = stateDirector
                .addStateRIP("gildeIcon")
                .addStatesToActivate(StateEnumsBDM.GILDE)
                .build();
        schwarzgeistKnopf = stateDirector
                .addStateRIP("schwarzgeistKnopf")
                .addStatesToActivate(StateEnumsBDM.SCHWARZGEIST_EMPFANG)
                .build();
        lexikonIcon = stateDirector
                .addStateRIP("lexikonIcon")
                .addStatesToActivate(StateEnumsBDM.LEXIKON)
                .build();
        stateDirector.save();
    }

}
