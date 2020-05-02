package com.brobot.brobotREST.brobots.BDM.GameStates.Camp.Workers;

import com.brobot.brobotREST.stateData.wrappers.StateImageWrapper;
import com.brobot.brobotREST.stateData.wrappers.StateObjectWrapperFactory;
import com.brobot.brobotREST.stateObjects.objectMethods.AllStateObjectActions;
import com.brobot.brobotREST.database.primatives.Image;
import org.springframework.stereotype.Component;

@Component
public class WorkersActivities {

    private final Workers workers;
    private final AllStateObjectActions we;

    private StateImageWrapper abgeschlossen;

    private int anzahlArbeiter = 3;
    private Image workCompleted = new Image("workCompleted");
    private Image complete = new Image("complete");

    public WorkersActivities(Workers workers, AllStateObjectActions we,
                             StateObjectWrapperFactory stateObjectWrapperFactory) {
        this.workers = workers;
        this.we = we;

        abgeschlossen = stateObjectWrapperFactory.buildImageWrapper(workers.getAbgeschlossen());
    }

    public boolean completeTasks() {
        System.out.print(" workers, complete tasks | ");
        boolean completed = we.click().RIP(workers.getAlles());
        we.clickUntil().objectDisappears(abgeschlossen, 5);
        we.type().string(workers.getEsc());
        return completed;
    }

}