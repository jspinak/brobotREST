package com.brobot.brobotREST.Brobots.BDM.GameStates.Rewards.Mail;

import com.brobot.brobotREST.StateObjects.ObjectMethods.AllGameObjectActions;
import com.brobot.brobotREST.StateObjects.BaseObjects.RIP.StateRIP;
import org.springframework.stereotype.Component;

@Component
public class MailActivities {

    private final Mail mail;
    private final AllGameObjectActions we;

    public MailActivities(Mail mail, AllGameObjectActions we) {
        this.mail = mail;
        this.we = we;
    }

    public boolean sammelAlle() {
        sammelBelohnungen(mail.getAllgemeinesPostfach());
        sammelBelohnungen(mail.getServerPostfach());
        return true;
    }

    public boolean sammelBelohnungen(StateRIP postfach) {
        we.click().RIP(postfach);
        we.click().RIP(mail.getAllesErhalten());
        we.clickUntil().objectDisappears(mail.getBestatigen(), 3);
        we.pause(2.0);
        return true;
    }

}