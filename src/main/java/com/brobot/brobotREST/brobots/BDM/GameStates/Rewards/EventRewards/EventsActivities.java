package com.brobot.brobotREST.brobots.BDM.GameStates.Rewards.EventRewards;

import com.brobot.brobotREST.stateObjects.objectMethods.AllStateObjectActions;
import org.springframework.stereotype.Component;

@Component
public class EventsActivities {

    private final Events events;
    private final AllStateObjectActions we;

    public EventsActivities(Events events, AllStateObjectActions we) {
        this.events = events;
        this.we = we;
    }
    /*

    public boolean clickLoginMoglich() {
        return bdoGameRegion.clickMultipleImagesLastImageDisappears(spiralIconTab, loginMoglich, loginBelohnungErhalten);
    }

    private boolean defineBelohnungRegion() {
        if (belohnungRegion.defined()) return true;
        Match tagMatch = bdoGameRegion.matchImages(tag);
        if (tagMatch != null) {
            //tagMatch.highlight(1);
            belohnungRegion.y = tagMatch.y - 20;
        } else {
            belohnungRegion.y = 600;
        }
        belohnungRegion.x = bdoGameRegion.x + 50;
        belohnungRegion.h = bdoGameRegion.y + bdoGameRegion.h - belohnungRegion.y - 20;
        belohnungRegion.w = bdoGameRegion.w - 200;
        //belohnungRegion.highlight(2);
        return true;
    }

    public boolean furJedenTagBelohnungErhalten(Image tab) {
        System.out.print(" get rewards available for multiple days. ");
        boolean abgeholt = false;
        if (bdoGameRegion.clickMultipleImagesLastImageDisappears(tab, loginMoglich, loginBelohnungErhalten)) {
            defineBelohnungRegion();
            List<DefineRegion> tage = belohnungRegion.findAllDefineRegions(taglicheBelohnungAnDiesemTagMoglich);
            for (DefineRegion tag : tage) {
                //tag.highlight(1);
                tag.clickSicher();
                if (belohnungRegion.clickSicherImageUntilGone(bdoButtons.belohnungErhalten,
                        5, 1.0)) abgeholt = true;
            }
        }
        return abgeholt;
    }

    public boolean alleBelohnungenAbholen() {
        System.out.print(" event/daily belohnung abholen | ");
        boolean abgeholt = false;
        if (belohnungenAbholen()) abgeholt = true;
        scrollDown();
        if (belohnungenAbholen()) abgeholt = true;
        return abgeholt;
    }

    public boolean belohnungenAbholen() {
        boolean abgeholt = false;
        if (getCalendarPenEventRewards()) abgeholt = true;
        if (getWireCalendarIconRewards()) abgeholt = true;
        if (getSpiralEventRewards()) abgeholt = true;
        if (getDiamondFragezeichenRewards()) abgeholt = true;
        return abgeholt;
    }

    public boolean getCalendarPenEventRewards() {
        System.out.print(" get calendar pen event rewards. ");
        return furJedenTagBelohnungErhalten(eventTabCalendarPen);
    }

    public boolean getWireCalendarIconRewards() {
        System.out.print(" get wire calendar rewards. ");
        boolean abgeholt = false;
        List<DefineRegion> wireCalendarIcons = bdoGameRegion.findAllDefineRegions(calendarIconWireBinder);
        if (wireCalendarIcons == null) return false;
        for (DefineRegion tabRegion : wireCalendarIcons) {
            tabRegion.clickSicher();
            if (bdoGameRegion.clickIfExists(login)) {
                abgeholt = true;
                bdoGameRegion.wait(1.0);
            }
        }
        return abgeholt;
    }

    public boolean getSpiralEventRewards() {
        System.out.print(" get spiral event rewards. ");
        clickLoginMoglich();
        furJedenTagBelohnungErhalten(spiralIconTab);
        bdoGameRegion.clickSicherImageUntilGone(bdoButtons.erhalten, 5, 1.0);
        return true;
    }

    private boolean getDiamondFragezeichenRewards() {
        System.out.print(" get fragezeichen event rewards. ");
        if (!bdoGameRegion.clickIfExists(fragezeichenTab)) return false;
        return bdoGameRegion.clickSicherImageUntilGone(bdoButtons.erhalten, 2, .5);
    }

    private boolean defineTabsRegion() {
        if (tabsRegion.defined()) return true;
        Image topLeftCorner = new Image("tabsRegionTopLeft");
        Image bottomRightCorner = new Image("tabsRegionBottomRight");
        tabsRegion.defineByTopleftBottomright(topLeftCorner, bottomRightCorner, 0);
        //int wAdjust = 500;
        //int hAdjust = 150;
        //tabsRegion.x = bdoGameRegion.x + bdoGameRegion.w - wAdjust;
        //tabsRegion.w = wAdjust;
        //tabsRegion.y = bdoGameRegion.y + 150;
        //tabsRegion.h = bdoGameRegion.h - 150;
        //tabsRegion.highlight(4);
        //System.out.println(tabsRegion.x+" "+tabsRegion.y+" "+tabsRegion.w+" "+tabsRegion.h);
        return true;
    }

    //to check all events you need to drag the screen up
    private void scrollDown() {
        defineTabsRegion();
        tabsRegion.dragInsideRegion(Position.MIDDLE, Position.DOWN, Position.MIDDLE, Position.UP, 50);
    }

    private void scrollUp() {
        defineTabsRegion();
        tabsRegion.dragInsideRegion(Position.MIDDLE, Position.UP, Position.MIDDLE, Position.DOWN, 50);
    }

     */

}

