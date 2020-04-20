package com.brobot.brobotREST.Brobots.BDM.Steuerung;

import com.brobot.brobotREST.StateObjects.ErrorManagement.GameError;
import com.brobot.brobotREST.StateObjects.ObjectMethods.Click.ClickUntilStateImage;
import org.springframework.stereotype.Component;

@Component
public class BDMErrors implements GameError {
    private DetachedErrorStateBDM detachedErrorStateBDM;
    private BDMGameRegionDefined bdoGameRegion;
    private ClickUntilStateImage clickUntilStateImage;

    public BDMErrors(DetachedErrorStateBDM detachedErrorStateBDM,
                     BDMGameRegionDefined bdoGameRegion,
                     ClickUntilStateImage clickUntilStateImage) {
        this.detachedErrorStateBDM = detachedErrorStateBDM;
        this.bdoGameRegion = bdoGameRegion;
        this.clickUntilStateImage = clickUntilStateImage;
    }

    public boolean makeOpenStatePossible() {
        System.out.println(" make open state possible | ");
        if (clickUntilStateImage.objectDisappears(detachedErrorStateBDM.getExitState(), 3) ||
            clickUntilStateImage.objectDisappears(detachedErrorStateBDM.getExitMenu(), 3) ||
            clickUntilStateImage.objectDisappears(detachedErrorStateBDM.getX(), 3)) {
            return true;
        }
        return false;
    }

    public boolean quitGameAndRerun() {
        return false;
    }

    public boolean fehlerBeheben() {
        System.out.println(" Black Desert GameError: fehler beheben | ");
        boolean errorFound = false;
        for (int i=0; i<5; i++) {
            if (!einFehlerGefundenUndBehoben()) break;
            errorFound = true;
            bdoGameRegion.wait(2.0);
        }
        return errorFound;
    }

    public boolean einFehlerGefundenUndBehoben() {
        return makeOpenStatePossible();
    }

}
