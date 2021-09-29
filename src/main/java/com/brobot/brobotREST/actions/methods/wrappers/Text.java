package com.brobot.brobotREST.actions.methods.wrappers;

import com.brobot.brobotREST.database.primitives.match.MatchObject;
import com.brobot.brobotREST.database.primitives.region.Region;
import com.brobot.brobotREST.database.state.stateObject.otherStateObjects.StateRegion;
import com.brobot.brobotREST.mock.Mock;
import org.springframework.stereotype.Component;

@Component
public class Text {

    private Mock mock;
    private String mockText = "mock text";

    public Text(Mock mock) {
        this.mock = mock;
    }

    public String text(MatchObject matchObject) {
        if (mock.isActive()) return matchObject.getText();
        String textFound = new Region(matchObject.getMatch()).text();
        matchObject.setText(textFound);
        return textFound;
    }

    public String text(StateRegion stateRegion) {
        if (mock.isActive()) return stateRegion.getMockText();
        return stateRegion.getSearchRegion().text();
    }

    public String text(Region region) {
        if (mock.isActive()) return mockText;
        return region.text();
    }
}
