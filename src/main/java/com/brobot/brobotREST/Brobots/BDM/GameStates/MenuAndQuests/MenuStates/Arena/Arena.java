package com.brobot.brobotREST.Brobots.BDM.GameStates.MenuAndQuests.MenuStates.Arena;

import com.brobot.brobotREST.Brobots.BDM.GameStates.GameStateEnumsBDM;
import com.brobot.brobotREST.StateObjects.State.State;
import com.brobot.brobotREST.Brobots.BDM.StateBuilders.GameStateDirectorBDM;
import com.brobot.brobotREST.StateObjects.BaseObjects.Image.StateImage;
import com.brobot.brobotREST.StateObjects.BaseObjects.RIP.StateRIP;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
public @Data class Arena {

    private State arenaKampf;

    private State arenaEmpfang;
    private StateRIP arenaText;
    private StateRIP mitDemKampfBeginnen;
    private StateImage kisteOffnen;
    private StateRIP kisteOffnenBestatigung;
    private StateImage arenaKisteErhalten;

    public Arena(GameStateDirectorBDM gameStateDirectorBDM) {

        arenaEmpfang = gameStateDirectorBDM
                .init(GameStateEnumsBDM.ARENA_EMPFANG)
                .addStateText("Arena")
                .build();
        arenaText = gameStateDirectorBDM
                .addStateRIP("arenaStateText")
                .build();
        mitDemKampfBeginnen = gameStateDirectorBDM
                .addStateRIP("mitDemKampfBeginnen")
                .build();
        kisteOffnen = gameStateDirectorBDM
                .addStateImage("kisteOffnen")
                .build();
        kisteOffnenBestatigung = gameStateDirectorBDM
                .addStateRIP("kisteOffnenBestatigung")
                .build();
        arenaKisteErhalten = gameStateDirectorBDM
                .addStateImage("arenaKisteErhalten")
                .build();


        arenaKampf = gameStateDirectorBDM
                .init(GameStateEnumsBDM.ARENA_KAMPF)
                .build();



    }

}