package com.brobot.brobotREST.brobots.BDM.GameStates.MenuAndQuests.MenuStates.Arena;

import com.brobot.brobotREST.brobots.BDM.GameStates.StateEnumsBDM;
import com.brobot.brobotREST.database.state.StateData;
import com.brobot.brobotREST.database.state.StateRIPData;
import com.brobot.brobotREST.database.state.StateImageData;
import com.brobot.brobotREST.stateObjects.builders.StateDirector;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
public @Data class Arena {

    private StateData arenaKampf;

    private StateData arenaEmpfang;
    private StateRIPData arenaText;
    private StateRIPData mitDemKampfBeginnen;
    private StateImageData kisteOffnen;
    private StateRIPData kisteOffnenBestatigung;
    private StateImageData arenaKisteErhalten;

    public Arena(StateDirector stateDirector) {

        arenaEmpfang = stateDirector
                .initState(StateEnumsBDM.ARENA_EMPFANG)
                .addStateText("Arena")
                .build();
        arenaText = stateDirector
                .addStateRIP("arenaStateText")
                .build();
        mitDemKampfBeginnen = stateDirector
                .addStateRIP("mitDemKampfBeginnen")
                .build();
        kisteOffnen = stateDirector
                .addStateImage("kisteOffnen")
                .build();
        kisteOffnenBestatigung = stateDirector
                .addStateRIP("kisteOffnenBestatigung")
                .build();
        arenaKisteErhalten = stateDirector
                .addStateImage("arenaKisteErhalten")
                .build();
        stateDirector.save();

        arenaKampf = stateDirector
                .initState(StateEnumsBDM.ARENA_KAMPF)
                .build();
        stateDirector.save();


    }

}