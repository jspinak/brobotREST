package com.brobot.brobotREST.Brobots.BDM.StateBuilders;

import com.brobot.brobotREST.Brobots.BDM.Steuerung.SharedImages;
import com.brobot.brobotREST.StateObjects.State.State;
import com.brobot.brobotREST.StateObjects.State.StateBuilderGeneric;
import com.brobot.brobotREST.StateObjects.BaseObjects.RIP.StateRIP;
import com.brobot.brobotREST.StateObjects.BaseObjects.RIP.StateRIPBuilder;
import com.brobot.brobotREST.StateObjects.BaseObjects.String.StateString;
import com.brobot.brobotREST.StateObjects.BaseObjects.String.StateStringBuilder;
import com.brobot.brobotREST.Primatives.Enums.GameStateEnum;
import org.sikuli.script.Key;
import org.springframework.stereotype.Component;

@Component
public class GameStateBuilderBDMBestatigung {

    private StateRIPBuilder stateRIPBuilder;
    private StateStringBuilder stateStringBuilder;
    private StateBuilderGeneric stateBuilderGeneric;
    private SharedImages sharedImages;

    private StateRIPBuilder bestatigungRIPbuilder;
    private StateRIP bestatigungRIP;
    private StateStringBuilder bestatigungESCbuilder;
    private StateString bestatigungESC;
    private StateBuilderGeneric gameStateBuilder;
    private State state;

    public GameStateBuilderBDMBestatigung(StateRIPBuilder stateRIPBuilder, StateStringBuilder stateStringBuilder,
                                          StateBuilderGeneric stateBuilderGeneric,
                                          SharedImages sharedImages) {

        this.stateRIPBuilder = stateRIPBuilder;
        this.stateStringBuilder = stateStringBuilder;
        this.stateBuilderGeneric = stateBuilderGeneric;
        this.sharedImages = sharedImages;
    }

    public GameStateBuilderBDMBestatigung init(GameStateEnum name) {
        bestatigungRIPbuilder = addBestatigungRIP(name);
        bestatigungESCbuilder = stateStringBuilder
                .init(name)
                .setString(Key.ESC);
        gameStateBuilder = stateBuilderGeneric
                .init(name)
                .addStateText("Nachricht")
                .setOnTopOfActivatingState();
        return this;
    }

    public StateRIPBuilder addBestatigungRIP(GameStateEnum name) {
        return stateRIPBuilder
                .init(name)
                .addRIPs(sharedImages.bestatigen);
    }

    public GameStateBuilderBDMBestatigung addStatesToActivateToRIP(GameStateEnum name) {
        bestatigungRIPbuilder.addStatesToActivate(name);
        return this;
    }

    public GameStateBuilderBDMBestatigung setSecondsToWaitAfterClick(double seconds) {
        bestatigungRIPbuilder.setTimeToWaitAfterAction(seconds);
        return this;
    }

    public GameStateBuilderBDMBestatigung addStatesToActivateToESC(GameStateEnum name) {
        bestatigungESCbuilder.addStatesToActivate(name);
        return this;
    }

    public State build() {
        bestatigungRIP = bestatigungRIPbuilder.build();
        bestatigungESC = bestatigungESCbuilder.build();
        state = gameStateBuilder.build();
        return state;
    }

}
