package com.brobot.brobotREST.Brobots.BDM.GameStates.MenuAndQuests.QuestStates;

import com.brobot.brobotREST.StateObjects.BaseObjects.String.StateString;
import com.brobot.brobotREST.Brobots.BDM.GameStates.GameStateEnumsBDM;
import com.brobot.brobotREST.StateObjects.State.State;
import com.brobot.brobotREST.Brobots.BDM.StateBuilders.GameStateDirectorBDM;
import com.brobot.brobotREST.StateObjects.BaseObjects.RIP.StateRIP;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
public @Data class QuestBestatigung {

    private State state;
    private StateRIP bestatigenScreens;
    private StateRIP questFortsetzen;
    private StateRIP annehmen;
    private StateString closeState;

    public QuestBestatigung(GameStateDirectorBDM gameStateDirectorBDM) {

        // the RIP should recognize different screens with different images, all part of a Quest Bestatigung
        state = gameStateDirectorBDM
                .init(GameStateEnumsBDM.QUEST_BESTATIGUNG)
                .addStateText("Bestatigung")
                .build();
        questFortsetzen = gameStateDirectorBDM
                .addStateRIP("schwarzgeistQuestFortsetzen")
                .build();
        bestatigenScreens = gameStateDirectorBDM
                .addStateRIP("bestatigenBlackSpiritQuestFertig")
                .build();
        annehmen = gameStateDirectorBDM
                .addStateRIP("questAnnehmen2")
                .build();
                //need "druck" too
        closeState = gameStateDirectorBDM
                .addStateString("Key.ESC")
                .build();
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