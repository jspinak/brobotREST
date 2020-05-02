package com.brobot.brobotREST.brobots.BDM.GameStates.World.Pets;

import com.brobot.brobotREST.brobots.BDM.GameStates.StateEnumsBDM;
import com.brobot.brobotREST.database.state.StateData;
import com.brobot.brobotREST.database.state.StateRIPData;
import com.brobot.brobotREST.database.state.StateImageData;
import com.brobot.brobotREST.stateObjects.builders.StateDirector;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
public @Data class Pets {

    private StateData state;
    private StateRIPData begleiterText;
    private StateRIPData petTalentTraining;
    private StateImageData startTalentTraining;
    private StateRIPData trainingAbgeschlossen;
    private StateRIPData futtern;
    private StateImageData petFoodIcon;
    private StateRIPData fertig;

    public Pets(StateDirector stateDirector) {

        state = stateDirector
                .initState(StateEnumsBDM.PETS)
                .addStateText("Pets")
                .build();
        begleiterText = stateDirector
                .addStateRIP("begleiter")
                .build();
        petTalentTraining = stateDirector
                .addStateRIP("petTalentTraining")
                .build();
        startTalentTraining = stateDirector
                .addStateImage("startTalentTraining")
                .build();
        trainingAbgeschlossen = stateDirector
                .addStateRIP("trainingAbgeschlossen")
                .build();
        futtern = stateDirector
                .addStateRIP("futtern")
                .build();
        petFoodIcon = stateDirector
                .addStateImage("petFoodIcon")
                .build();
        //fertig = stateDirector
        //        .addBestatigung()
        //        .build();
        stateDirector.save();
    }

}