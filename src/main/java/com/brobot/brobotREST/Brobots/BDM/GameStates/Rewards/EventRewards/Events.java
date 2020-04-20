package com.brobot.brobotREST.Brobots.BDM.GameStates.Rewards.EventRewards;

import com.brobot.brobotREST.Primatives.Region;
import com.brobot.brobotREST.Brobots.BDM.GameStates.GameStateEnumsBDM;
import com.brobot.brobotREST.StateObjects.State.State;
import com.brobot.brobotREST.Brobots.BDM.StateBuilders.GameStateDirectorBDM;
import com.brobot.brobotREST.StateObjects.BaseObjects.Image.StateImage;
import com.brobot.brobotREST.StateObjects.BaseObjects.RIP.StateRIP;
import com.brobot.brobotREST.Primatives.Image;
import com.brobot.brobotREST.Primatives.RegionImagePairs;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
public @Data class Events {

    private State state;
    private StateRIP eventText;
    private StateRIP spaterErhalten;
    private StateImage login;

    private Region tabsRegion = new Region();

    private RegionImagePairs calendarIconTearOffPage = new RegionImagePairs("dailyLoginEvent");
    private Image spiralIconTab = new Image("eventBelohnungTab");
    private Image calendarIconWireBinder = new Image("dailyPackages");
    private Image eventTabCalendarPen = new Image("eventTabCalendarPen");//mediah missions event
    private Image eventTabBuchFragezeichen = new Image("eventTabBuchFragezeichen");
    private Image fragezeichenTab = new Image("fragezeichenTab");
    private Image belohnungVerfugbarIcon = new Image("belohnungVerfugbarIcon");
    private Image belohnungHier = new Image("belohnungHier");
    private Image loginBelohnungErhalten = new Image("loginBelohnungErhalten","loginBelohnungErhalten2");
    private Image loginMoglich = new Image("loginMoglich");
    private Image tag = new Image("tag1");//,"tag2","tag3","belohnungVerfugbarIcon","belohnungVerfugbarIcon2");
    private Image taglicheBelohnungAnDiesemTagMoglich = new Image("taglicheBelohnungAnDiesemTagMoglich","vierAufgabeProTag");

    Region belohnungRegion = new Region();

    public Events(GameStateDirectorBDM gameStateDirectorBDM) {

        state = gameStateDirectorBDM
                .init(GameStateEnumsBDM.EVENT_REWARDS)
                .addStateText("Event","Belohnungsinfo")
                .build();
        eventText = gameStateDirectorBDM
                .addStateRIP("eventText")
                .build();
        spaterErhalten = gameStateDirectorBDM
                .addStateRIP("spaterErhalten","spater")
                .build();
        login = gameStateDirectorBDM
                .addStateImage("login")
                .build();
    }


}

