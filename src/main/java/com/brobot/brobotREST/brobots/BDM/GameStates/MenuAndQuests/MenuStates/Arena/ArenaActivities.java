package com.brobot.brobotREST.brobots.BDM.GameStates.MenuAndQuests.MenuStates.Arena;

import com.brobot.brobotREST.stateObjects.objectMethods.AllStateObjectActions;
import org.springframework.stereotype.Component;

@Component
public class ArenaActivities {

    private Arena arena;
    private AllStateObjectActions we;

    public ArenaActivities(Arena arena, AllStateObjectActions we) {
        this.arena = arena;
        this.we = we;
    }

    public boolean kisteErhaltenUndOffnen() {
        if (we.click().click(arena.getArenaKisteErhalten())) we.pause(.5);
        if (we.click().click(arena.getKisteOffnen())) {
            we.pause(.5);
            we.click().RIP(arena.getKisteOffnenBestatigung());
        }
        return true;
    }

}