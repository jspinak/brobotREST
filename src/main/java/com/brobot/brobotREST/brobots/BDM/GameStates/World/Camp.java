package com.brobot.brobotREST.brobots.BDM.GameStates.World;

import com.brobot.brobotREST.brobots.BDM.GameStates.StateEnumsBDM;
import com.brobot.brobotREST.brobots.BDM.StateBuilders.StateDirectorBDM;
import com.brobot.brobotREST.database.state.StateData;
import com.brobot.brobotREST.database.state.StateRIPData;
import com.brobot.brobotREST.brobots.BDM.StateBuilders.GameObjectBuilderBDM;
import com.brobot.brobotREST.database.state.StateImageData;
import com.brobot.brobotREST.stateObjects.builders.StateDirector;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
public @Data class Camp {

    private StateData campOpenBestatigung;
    private StateData campCloseBestatigung;

    private StateData camp;
    private StateRIPData campIcon;
    private StateRIPData birdsEyeView;
    private StateRIPData exitCamp;
    private StateRIPData openSidebar;
    private StateImageData openGarten;
    private StateRIPData sammelnKnopf;
    private StateRIPData arbeiter;

    public Camp(StateDirector stateDirector, StateDirectorBDM directorBDM) {

        campOpenBestatigung = directorBDM.newBestatigung(
                StateEnumsBDM.CAMP_OPEN_BESTATIGUNG,
                StateEnumsBDM.CAMP,
                StateEnumsBDM.WORLD);

        campCloseBestatigung = directorBDM.newBestatigung(
                StateEnumsBDM.CAMP_CLOSE_BESTATIGUNG,
                StateEnumsBDM.WORLD,
                StateEnumsBDM.CAMP);

        camp = stateDirector
                .initState(StateEnumsBDM.CAMP)
                .addStateText("Camp")
                .addCoexistingState(StateEnumsBDM.MAIN_MENU, true)
                .build();
        campIcon = stateDirector
                .addStateRIP("campIcon") // identifies state only
                .build();
        birdsEyeView = stateDirector
                .addStateRIP("birdsEyeView")
                .build();
        exitCamp = stateDirector
                .addStateRIP("exitCamp")
                .addStatesToActivate(StateEnumsBDM.CAMP_CLOSE_BESTATIGUNG)
                .build();
        openSidebar = stateDirector
                .addStateRIP("buildIcon","buildIcon2")
                .setName("openSidebar")
                .addStatesToActivate(StateEnumsBDM.CAMP_SIDEBAR)
                .build();
        // to see these images, search after exiting form viehstahl, which is next to the garden
        openGarten = stateDirector
                .addStateImage("campManageBuilding","openGartenText")
                .setName("openGarten")
                .addStatesToActivate(StateEnumsBDM.GARTEN)
                .build();
        sammelnKnopf = stateDirector
                .addStateRIP("sammelnKnopf")
                .addStatesToActivate(StateEnumsBDM.WELT_SAMMELN_OPEN)
                .build();
        arbeiter = stateDirector
                .addStateRIP("arbeiter","fertigeArbeiter","workersIcon")
                .addStatesToActivate(StateEnumsBDM.WORKERS)
                .build();
        stateDirector.save();
        //when clicking on the handIcon (openGarten) in Camp, it could open another building
        //make a number of handIcons (stateIamges) with the same image but different statesToActivate
        //the method will determine which one to call

    }

}