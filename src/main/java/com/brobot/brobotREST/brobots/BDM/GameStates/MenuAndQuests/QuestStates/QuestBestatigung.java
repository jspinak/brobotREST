package com.brobot.brobotREST.brobots.BDM.GameStates.MenuAndQuests.QuestStates;

import com.brobot.brobotREST.database.state.StateData;
import com.brobot.brobotREST.database.state.StateRIPData;
import com.brobot.brobotREST.database.state.StateStringData;
import com.brobot.brobotREST.brobots.BDM.GameStates.StateEnumsBDM;
import com.brobot.brobotREST.stateObjects.builders.StateDirector;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
public @Data class QuestBestatigung {

    private StateData state;
    private StateRIPData bestatigenScreens;
    private StateRIPData questFortsetzen;
    private StateRIPData annehmen;
    private StateStringData closeState;

    public QuestBestatigung(StateDirector stateDirector) {

        // the RIP should recognize different screens with different images, all part of a Quest Bestatigung
        state = stateDirector
                .initState(StateEnumsBDM.QUEST_BESTATIGUNG)
                .addStateText("Bestatigung")
                .build();
        questFortsetzen = stateDirector
                .addStateRIP("schwarzgeistQuestFortsetzen")
                .build();
        bestatigenScreens = stateDirector
                .addStateRIP("bestatigenBlackSpiritQuestFertig")
                .build();
        annehmen = stateDirector
                .addStateRIP("questAnnehmen2")
                .build();
                //need "druck" too
        closeState = stateDirector
                .addStateString("Key.ESC")
                .build();
        stateDirector.save();
    }

    public boolean advanceScreenFast() {
        return true;
    }
    public boolean advanceScreen() { return true; }

    /*
    public boolean clickAnnehmen() {
        return questAnnehmen.clickSicherUntilGone(2,.4);
    }

    private boolean defineSichereClickRegion() {
        if (sichereClickRegion.defined()) return true;
        sichereClickRegion.x = bdoGameRegion.getCenter().x;
        sichereClickRegion.y = bdoGameRegion.y + bdoGameRegion.h - 50;
        sichereClickRegion.w = 5;
        sichereClickRegion.h = 5;
        return true;
    }

    public boolean advanceScreenFast() {
        defineSichereClickRegion();
        for (int i=0; i<12; i++) {
            sichereClickRegion.clickSicher();
            bdoGameRegion.pause(.6);
            questAnnehmen.clickSicherUntilGone(2,.4);
        }
        return true;
    }

    public boolean advanceScreen() {
        if (npcListeAndMinimap.exists()) return false;
        System.out.print(" advance screen | ");
        boolean advanced = false;
        DefineRegion nextScreenRegion = nextScreen.getRegion();
        if (nextScreenRegion.defined()) {
            for (int i=0; i<5; i++) {
                nextScreenRegion.clickSicher();
                bdoGameRegion.pause(1.0);
            }
        }
        if (npcListeAndMinimap.exists()) return true;
        for (int i=0; i<10; i++) {
            if (!nextScreen.clickSicher()) break;
            advanced = true;
            bdoGameRegion.pause(1.0);
        }
        if (npcListeAndMinimap.exists()) return true;
        bdoGameRegion.clickIfExists(bdoButtons.bestatigen,bdoButtons.annehmen);
        if (questFortsetzen.clickAndDefineRegion()) bdoGameRegion.pause(1.0);
        if (bestatigenKnopf.clickAndDefineRegion()) bdoGameRegion.pause(1.0);
        return advanced;
    }


     */


}