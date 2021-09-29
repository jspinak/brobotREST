package com.brobot.brobotREST.actions.methods.wrappers;

import com.brobot.brobotREST.database.primitives.region.Region;
import com.brobot.brobotREST.mock.Mock;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class App {

    private Mock mock;

    public App(Mock mock) {
        this.mock = mock;
    }

    public Optional<Region> focusedWindow() {
        if (mock.isActive()) return Optional.of(mock.getFocusedWindow());
        org.sikuli.script.Region reg = org.sikuli.script.App.focusedWindow();
        if (reg == null) return Optional.empty();
        return Optional.of(new Region(reg));
    }
}
