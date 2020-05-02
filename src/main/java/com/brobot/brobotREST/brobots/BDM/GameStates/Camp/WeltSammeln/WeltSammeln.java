package com.brobot.brobotREST.brobots.BDM.GameStates.Camp.WeltSammeln;

import com.brobot.brobotREST.brobots.BDM.DataModel.Items;
import com.brobot.brobotREST.brobots.BDM.GameStates.StateEnumsBDM;
import com.brobot.brobotREST.database.state.StateData;
import com.brobot.brobotREST.database.state.StateRIPData;
import com.brobot.brobotREST.database.state.StateStringData;
import com.brobot.brobotREST.stateObjects.builders.StateDirector;
import lombok.Data;
import org.sikuli.script.Key;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public @Data class WeltSammeln {

    private StateData weltSammelnOpenGS;
    private StateRIPData weltSammelnMenuIcon;
    private StateStringData escOpen;

    private StateData weltSammelnGS;
    private StateRIPData weltSammelnStateImage;
    private StateRIPData sammelnTab;
    private StateRIPData holzfallenTab;
    private StateRIPData bergbauTab;
    private Map<Items.ItemType, StateRIPData> sammelTabs = new HashMap<>();
    {
        sammelTabs.put(Items.ItemType.PFLANZE, sammelnTab);
        sammelTabs.put(Items.ItemType.HOLZ, holzfallenTab);
        sammelTabs.put(Items.ItemType.ERZ, bergbauTab);
    }
    private StateData weltSammelnArbeiterWahlenGS;
    private StateRIPData weltSammelnAlles; // alle arbeiter wahlen
    private StateRIPData aufgabeBeginnen; //is also defined in another class
    private StateStringData escArbeiterWahlen;

    public WeltSammeln(StateDirector stateDirector, Items items) {

        weltSammelnOpenGS = stateDirector
                .initState(StateEnumsBDM.WELT_SAMMELN_OPEN)
                .build();
        weltSammelnMenuIcon = stateDirector
                .addStateRIP("sammelnMenuItemWeltSammeln")
                .addStatesToActivate(StateEnumsBDM.WELT_SAMMELN)
                .build();
        escOpen = stateDirector
                .addStateString(Key.ESC)
                .addStatesToActivate(StateEnumsBDM.CAMP)
                .build();
        stateDirector.save();

        weltSammelnGS = stateDirector
                .initState(StateEnumsBDM.WELT_SAMMELN)
                .addStateText("Welt-","Sammeln")
                .build();
        weltSammelnStateImage = stateDirector
                .addStateRIP("weltSammelnStateImage")
                .build();
        sammelnTab = stateDirector
                .addStateRIP("sammelnTab")
                .build();
        holzfallenTab = stateDirector
                .addStateRIP("holzfallenTab")
                .build();
        bergbauTab = stateDirector
                .addStateRIP("bergbauTab")
                .build();
        stateDirector.save();
                                            // how do you close this state?

        weltSammelnArbeiterWahlenGS = stateDirector
                .initState(StateEnumsBDM.WELT_SAMMELN_ARBEITER_WAHLEN)
                .build();
        weltSammelnAlles = stateDirector
                .addStateRIP("weltSammelnAlles")
                .build();
        aufgabeBeginnen = stateDirector
                .addStateRIP("aufgabeBeginnen")
                .addStatesToActivate(StateEnumsBDM.WELT_SAMMELN)
                .build();
        escArbeiterWahlen = stateDirector
                .addStateString(Key.ESC)
                .addStatesToActivate(StateEnumsBDM.WELT_SAMMELN)
                .build();
        stateDirector.save();
    }

}