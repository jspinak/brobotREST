package com.brobot.brobotREST.Brobots.BDM.GameStates.MenuAndQuests.MenuStates.Arena;

import com.brobot.brobotREST.StateObjects.ObjectMethods.AllGameObjectActions;
import org.springframework.stereotype.Component;

@Component
public class ArenaActivities {

    private Arena arena;
    private AllGameObjectActions we;

    public ArenaActivities(Arena arena, AllGameObjectActions we) {
        this.arena = arena;
        this.we = we;
    }

    public boolean kisteErhaltenUndOffnen() {
        if (we.click().image(arena.getArenaKisteErhalten())) we.pause(.5);
        if (we.click().image(arena.getKisteOffnen())) {
            we.pause(.5);
            we.click().RIP(arena.getKisteOffnenBestatigung());
        }
        return true;
    }

}