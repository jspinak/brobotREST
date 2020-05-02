package com.brobot.brobotREST.stateObjects.objectMethods.click;

import com.brobot.brobotREST.database.state.StateRegionData;
import com.brobot.brobotREST.stateObjects.sikuliWrappers.Click;
import com.brobot.brobotREST.stateObjects.sikuliWrappers.MockRegion;
import com.brobot.brobotREST.stateObjects.sikuliWrappers.Wait;
import org.springframework.stereotype.Component;

@Component
public class ClickStateRegion {

    private MockRegion mockRegion;
    private Click click;
    private Wait wait;

    public ClickStateRegion(MockRegion mockRegion, Click click, Wait wait) {
        this.mockRegion = mockRegion;
        this.click = click;
        this.wait = wait;
    }

    public boolean region(StateRegionData stateRegionData) {
        boolean mockSucceeds = mockRegion.doActionToChangeState(stateRegionData);
        if (mockRegion.isUseMock()) return mockSucceeds;
        return click.click(stateRegionData.getStateRegion());
    }

    public boolean regions(double pauseBetweenClicks, StateRegionData... stateRegions) {
        boolean mockSucceeds = mockRegion.doActionToChangeStates(stateRegions);
        if (mockRegion.isUseMock()) return mockSucceeds;
        for (StateRegionData stateRegionData : stateRegions) {
            click.click(stateRegionData.getStateRegion());
            wait.wait(pauseBetweenClicks);
        }
        return true;
    }
}
