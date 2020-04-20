package com.brobot.brobotREST.Brobots.BDM.GameStates.Camp.Workers;

import com.brobot.brobotREST.StateObjects.ObjectMethods.AllGameObjectActions;
import com.brobot.brobotREST.Primatives.Image;
import org.springframework.stereotype.Component;

@Component
public class WorkersActivities {

    private final Workers workers;
    private final AllGameObjectActions we;

    private int anzahlArbeiter = 3;
    private Image workCompleted = new Image("workCompleted");
    private Image complete = new Image("complete");

    public WorkersActivities(Workers workers, AllGameObjectActions we) {
        this.workers = workers;
        this.we = we;
    }

    public boolean completeTasks() {
        System.out.print(" workers, complete tasks | ");
        boolean completed = we.click().RIP(workers.getAlles());
        we.clickUntil().objectDisappears(workers.getAbgeschlossen(), 5);
        we.type().string(workers.getEsc());
        return completed;
    }

}