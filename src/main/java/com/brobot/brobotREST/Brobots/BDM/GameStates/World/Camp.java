package com.brobot.brobotREST.Brobots.BDM.GameStates.World;

import com.brobot.brobotREST.Brobots.BDM.GameStates.GameStateEnumsBDM;
import com.brobot.brobotREST.StateObjects.State.State;
import com.brobot.brobotREST.Brobots.BDM.StateBuilders.GameObjectBuilderBDM;
import com.brobot.brobotREST.Brobots.BDM.StateBuilders.GameStateDirectorBDM;
import com.brobot.brobotREST.StateObjects.BaseObjects.Image.StateImage;
import com.brobot.brobotREST.StateObjects.BaseObjects.RIP.StateRIP;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
public @Data class Camp {

    private State campOpenBestatigung;
    private State campCloseBestatigung;

    private State camp;
    private StateRIP campIcon;
    private StateRIP birdsEyeView;
    private StateRIP exitCamp;
    private StateRIP openSidebar;
    private StateImage openGarten;
    private StateRIP sammelnKnopf;
    private StateRIP arbeiter;

    public Camp(GameStateDirectorBDM gameStateDirectorBDM, GameObjectBuilderBDM gameObjectBuilder) {

        campOpenBestatigung = gameObjectBuilder.newBestatigung(GameStateEnumsBDM.CAMP_OPEN_BESTATIGUNG)
                .addStatesToActivateToRIP(GameStateEnumsBDM.CAMP)
                .addStatesToActivateToESC(GameStateEnumsBDM.WORLD)
                .setSecondsToWaitAfterClick(10.0)
                .build();

        campCloseBestatigung = gameObjectBuilder.newBestatigung(GameStateEnumsBDM.CAMP_CLOSE_BESTATIGUNG)
                .addStatesToActivateToRIP(GameStateEnumsBDM.WORLD)
                .addStatesToActivateToESC(GameStateEnumsBDM.CAMP)
                .setSecondsToWaitAfterClick(10.0)
                .build();

        camp = gameStateDirectorBDM
                .init(GameStateEnumsBDM.CAMP)
                .addStateText("Camp")
                .addCoexistingGameState(GameStateEnumsBDM.MAIN_MENU, true)
                .build();
        campIcon = gameStateDirectorBDM
                .addStateRIP("campIcon") // identifies state only
                .build();
        birdsEyeView = gameStateDirectorBDM
                .addStateRIP("birdsEyeView")
                .build();
        exitCamp = gameStateDirectorBDM
                .addStateRIP("exitCamp")
                .addStatesToActivate(GameStateEnumsBDM.CAMP_CLOSE_BESTATIGUNG)
                .build();
        openSidebar = gameStateDirectorBDM
                .addStateRIP("buildIcon","buildIcon2")
                .setName("openSidebar")
                .addStatesToActivate(GameStateEnumsBDM.CAMP_SIDEBAR)
                .build();
        // to see these images, search after exiting form viehstahl, which is next to the garden
        openGarten = gameStateDirectorBDM
                .addStateImage("campManageBuilding","openGartenText")
                .setName("openGarten")
                .addStatesToActivate(GameStateEnumsBDM.GARTEN)
                .build();
        sammelnKnopf = gameStateDirectorBDM
                .addStateRIP("sammelnKnopf")
                .addStatesToActivate(GameStateEnumsBDM.WELT_SAMMELN_OPEN)
                .build();
        arbeiter = gameStateDirectorBDM
                .addStateRIP("arbeiter","fertigeArbeiter","workersIcon")
                .addStatesToActivate(GameStateEnumsBDM.WORKERS)
                .build();
        //when clicking on the handIcon (openGarten) in Camp, it could open another building
        //make a number of handIcons (stateIamges) with the same image but different statesToActivate
        //the method will determine which one to call

    }

}