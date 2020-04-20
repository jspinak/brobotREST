package com.brobot.brobotREST.Brobots.BDM.GameStates.World.Pets;

import com.brobot.brobotREST.Brobots.BDM.GameStates.GameStateEnumsBDM;
import com.brobot.brobotREST.StateObjects.State.State;
import com.brobot.brobotREST.Brobots.BDM.StateBuilders.GameStateDirectorBDM;
import com.brobot.brobotREST.StateObjects.BaseObjects.Image.StateImage;
import com.brobot.brobotREST.StateObjects.BaseObjects.RIP.StateRIP;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
public @Data class Pets {

    private State state;
    private StateRIP begleiterText;
    private StateRIP petTalentTraining;
    private StateImage startTalentTraining;
    private StateRIP trainingAbgeschlossen;
    private StateRIP futtern;
    private StateImage petFoodIcon;
    private StateRIP fertig;

    public Pets(GameStateDirectorBDM gameStateDirectorBDM) {

        state = gameStateDirectorBDM
                .init(GameStateEnumsBDM.PETS)
                .addStateText("Pets")
                .build();
        begleiterText = gameStateDirectorBDM
                .addStateRIP("begleiter")
                .build();
        petTalentTraining = gameStateDirectorBDM
                .addStateRIP("petTalentTraining")
                .build();
        startTalentTraining = gameStateDirectorBDM
                .addStateImage("startTalentTraining")
                .build();
        trainingAbgeschlossen = gameStateDirectorBDM
                .addStateRIP("trainingAbgeschlossen")
                .build();
        futtern = gameStateDirectorBDM
                .addStateRIP("futtern")
                .build();
        petFoodIcon = gameStateDirectorBDM
                .addStateImage("petFoodIcon")
                .build();
        fertig = gameStateDirectorBDM
                .addBestatigung()
                .build();
    }

}