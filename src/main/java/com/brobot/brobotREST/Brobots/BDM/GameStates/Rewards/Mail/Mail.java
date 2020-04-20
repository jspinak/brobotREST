package com.brobot.brobotREST.Brobots.BDM.GameStates.Rewards.Mail;

import com.brobot.brobotREST.Brobots.BDM.GameStates.GameStateEnumsBDM;
import com.brobot.brobotREST.StateObjects.State.State;
import com.brobot.brobotREST.Brobots.BDM.StateBuilders.GameStateDirectorBDM;
import com.brobot.brobotREST.StateObjects.BaseObjects.RIP.StateRIP;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
public @Data class Mail {

    private State state;
    private StateRIP postfachText;
    private StateRIP allgemeinesPostfach;
    private StateRIP serverPostfach;
    private StateRIP allesErhalten;
    private StateRIP bestatigen;

    public Mail(GameStateDirectorBDM gameStateDirectorBDM) {

        state = gameStateDirectorBDM
                .init(GameStateEnumsBDM.MAIL)
                .addStateText("Postfach")
                .build();
        postfachText = gameStateDirectorBDM
                .addStateRIP("postfach")
                .build();
        allgemeinesPostfach = gameStateDirectorBDM
                .addStateRIP("allgemeinesPostfach")
                .build();
        serverPostfach = gameStateDirectorBDM
                .addStateRIP("serverPostfach")
                .build();
        allesErhalten = gameStateDirectorBDM
                .addStateRIP("allesErhalten")
                .build();
        bestatigen = gameStateDirectorBDM
                .addBestatigung()
                .build();
    }

}