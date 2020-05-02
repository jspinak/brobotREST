package com.brobot.brobotREST.brobots.BDM.GameStates.Rewards.Mail;

import com.brobot.brobotREST.database.state.StateRIPData;
import com.brobot.brobotREST.stateData.wrappers.StateObjectWrapperFactory;
import com.brobot.brobotREST.stateData.wrappers.StateRIPWrapper;
import com.brobot.brobotREST.stateObjects.objectMethods.AllStateObjectActions;
import org.springframework.stereotype.Component;

@Component
public class MailActivities {

    private final Mail mail;
    private final AllStateObjectActions we;

    private StateRIPWrapper bestatigen;

    public MailActivities(Mail mail, AllStateObjectActions we,
                          StateObjectWrapperFactory stateObjectWrapperFactory) {
        this.mail = mail;
        this.we = we;

        bestatigen = stateObjectWrapperFactory.buildRIPWrapper(mail.getBestatigen());
    }

    public boolean sammelAlle() {
        sammelBelohnungen(mail.getAllgemeinesPostfach());
        sammelBelohnungen(mail.getServerPostfach());
        return true;
    }

    public boolean sammelBelohnungen(StateRIPData postfach) {
        we.click().RIP(postfach);
        we.click().RIP(mail.getAllesErhalten());
        we.clickUntil().objectDisappears(bestatigen, 3);
        we.pause(2.0);
        return true;
    }

}