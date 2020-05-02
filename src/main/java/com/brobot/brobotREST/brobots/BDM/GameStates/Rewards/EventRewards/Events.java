package com.brobot.brobotREST.brobots.BDM.GameStates.Rewards.EventRewards;

import com.brobot.brobotREST.database.primatives.Region;
import com.brobot.brobotREST.brobots.BDM.GameStates.StateEnumsBDM;
import com.brobot.brobotREST.database.state.StateData;
import com.brobot.brobotREST.database.state.StateRIPData;
import com.brobot.brobotREST.database.state.StateImageData;
import com.brobot.brobotREST.database.primatives.Image;
import com.brobot.brobotREST.stateObjects.builders.StateDirector;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
public @Data class Events {

    private StateData state;
    private StateRIPData eventText;
    private StateRIPData spaterErhalten;
    private StateImageData login;

    private Region tabsRegion = new Region();

    //private RegionImagePairs calendarIconTearOffPage = new RegionImagePairs("dailyLoginEvent");
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

    public Events(StateDirector stateDirector) {

        state = stateDirector
                .initState(StateEnumsBDM.EVENT_REWARDS)
                .addStateText("Event","Belohnungsinfo")
                .build();
        eventText = stateDirector
                .addStateRIP("eventText")
                .build();
        spaterErhalten = stateDirector
                .addStateRIP("spaterErhalten","spater")
                .build();
        login = stateDirector
                .addStateImage("login")
                .build();
        stateDirector.save();
    }


}

