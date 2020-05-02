package com.brobot.brobotREST.stateObjects.objectMethods.click;

import com.brobot.brobotREST.database.state.StateRIPData;
import com.brobot.brobotREST.stateObjects.sikuliWrappers.Click;
import com.brobot.brobotREST.stateObjects.sikuliWrappers.MockRegion;
import org.sikuli.script.Match;
import org.springframework.stereotype.Component;

@Component
public class ClickStateRIP {

    private MockRegion mockRegion;
    private Click click;

    public ClickStateRIP(MockRegion mockRegion, Click click) {
        this.mockRegion = mockRegion;
        this.click = click;
    }

    public boolean click(StateRIPData stateRIPData) {
        return clickFirstMatch(stateRIPData) != null;
    }

    public Match clickFirstMatch(StateRIPData stateRIPData) {
        if (mockRegion.isUseMock()) System.out.print(stateRIPData.getName()+"::clickFirstMatch | ");
        Match mockMatch = mockRegion.doActionToChangeState(stateRIPData) ? mockRegion.getMockMatch() : null;
        if (mockRegion.isUseMock()) return mockMatch;
        return click.clickFirstMatch(
                stateRIPData.getSearchRegion(), stateRIPData.getRegionImagePairs().getPairs());
    }

}
