package com.brobot.brobotREST.brobots.BDM.GameStates.Rewards.Mail;

import com.brobot.brobotREST.brobots.BDM.GameStates.StateEnumsBDM;
import com.brobot.brobotREST.database.state.StateData;
import com.brobot.brobotREST.database.state.StateRIPData;
import com.brobot.brobotREST.stateObjects.builders.StateDirector;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
public @Data class Mail {

    private StateData state;
    private StateRIPData postfachText;
    private StateRIPData allgemeinesPostfach;
    private StateRIPData serverPostfach;
    private StateRIPData allesErhalten;
    private StateRIPData bestatigen;

    public Mail(StateDirector stateDirector) {

        state = stateDirector
                .initState(StateEnumsBDM.MAIL)
                .addStateText("Postfach")
                .build();
        postfachText = stateDirector
                .addStateRIP("postfach")
                .build();
        allgemeinesPostfach = stateDirector
                .addStateRIP("allgemeinesPostfach")
                .build();
        serverPostfach = stateDirector
                .addStateRIP("serverPostfach")
                .build();
        allesErhalten = stateDirector
                .addStateRIP("allesErhalten")
                .build();
        //bestatigen = stateDirector
        //        .addBestatigung()
        //        .build();
        stateDirector.save();
    }

}