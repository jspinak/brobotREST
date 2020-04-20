package com.brobot.brobotREST.Brobots.BDM.GameStates.Camp.WeltSammeln;

import com.brobot.brobotREST.Brobots.BDM.DataModel.Items;
import com.brobot.brobotREST.Brobots.BDM.GameStates.GameStateEnumsBDM;
import com.brobot.brobotREST.StateObjects.State.State;
import com.brobot.brobotREST.Brobots.BDM.StateBuilders.GameStateDirectorBDM;
import com.brobot.brobotREST.StateObjects.BaseObjects.RIP.StateRIP;
import com.brobot.brobotREST.StateObjects.BaseObjects.String.StateString;
import lombok.Data;
import org.sikuli.script.Key;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public @Data class WeltSammeln {

    private State weltSammelnOpenGS;
    private StateRIP weltSammelnMenuIcon;
    private StateString escOpen;

    private State weltSammelnGS;
    private StateRIP weltSammelnStateImage;
    private StateRIP sammelnTab;
    private StateRIP holzfallenTab;
    private StateRIP bergbauTab;
    private Map<Items.ItemType, StateRIP> sammelTabs = new HashMap<>();
    {
        sammelTabs.put(Items.ItemType.PFLANZE, sammelnTab);
        sammelTabs.put(Items.ItemType.HOLZ, holzfallenTab);
        sammelTabs.put(Items.ItemType.ERZ, bergbauTab);
    }
    private State weltSammelnArbeiterWahlenGS;
    private StateRIP weltSammelnAlles; // alle arbeiter wahlen
    private StateRIP aufgabeBeginnen; //is also defined in another class
    private StateString escArbeiterWahlen;

    public WeltSammeln(GameStateDirectorBDM gameStateDirectorBDM, Items items) {

        weltSammelnOpenGS = gameStateDirectorBDM
                .init(GameStateEnumsBDM.WELT_SAMMELN_OPEN)
                .build();
        weltSammelnMenuIcon = gameStateDirectorBDM
                .addStateRIP("sammelnMenuItemWeltSammeln")
                .addStatesToActivate(GameStateEnumsBDM.WELT_SAMMELN)
                .build();
        escOpen = gameStateDirectorBDM
                .addStateString(Key.ESC)
                .addStatesToActivate(GameStateEnumsBDM.CAMP)
                .build();

        weltSammelnGS = gameStateDirectorBDM
                .init(GameStateEnumsBDM.WELT_SAMMELN)
                .addStateText("Welt-","Sammeln")
                .build();
        weltSammelnStateImage = gameStateDirectorBDM
                .addStateRIP("weltSammelnStateImage")
                .build();
        sammelnTab = gameStateDirectorBDM
                .addStateRIP("sammelnTab")
                .build();
        holzfallenTab = gameStateDirectorBDM
                .addStateRIP("holzfallenTab")
                .build();
        bergbauTab = gameStateDirectorBDM
                .addStateRIP("bergbauTab")
                .build();
                                            // how do you close this state?

        weltSammelnArbeiterWahlenGS = gameStateDirectorBDM
                .init(GameStateEnumsBDM.WELT_SAMMELN_ARBEITER_WAHLEN)
                .build();
        weltSammelnAlles = gameStateDirectorBDM
                .addStateRIP("weltSammelnAlles")
                .build();
        aufgabeBeginnen = gameStateDirectorBDM
                .addStateRIP("aufgabeBeginnen")
                .addStatesToActivate(GameStateEnumsBDM.WELT_SAMMELN)
                .build();
        escArbeiterWahlen = gameStateDirectorBDM
                .addStateString(Key.ESC)
                .addStatesToActivate(GameStateEnumsBDM.WELT_SAMMELN)
                .build();

    }

}