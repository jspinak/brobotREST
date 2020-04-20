package com.brobot.brobotREST.Brobots.BDM.GameStates.Camp.WeltSammeln;

import com.brobot.brobotREST.Primatives.Region;
import com.brobot.brobotREST.Brobots.BDM.Steuerung.BDMGameRegionDefined;
import com.brobot.brobotREST.Brobots.BDM.DataModel.Item;
import com.brobot.brobotREST.Brobots.BDM.DataModel.Items;
import com.brobot.brobotREST.StateObjects.ObjectMethods.AllGameObjectActions;
import com.brobot.brobotREST.StateObjects.BaseObjects.Image.StateImage;
import com.brobot.brobotREST.StateObjects.BaseObjects.RIP.StateRIP;
import org.springframework.stereotype.Component;

@Component
public class WeltSammelnActivities {

    private Items items;
    private BDMGameRegionDefined bdoGameRegion;
    private WeltSammeln weltSammeln;
    private AllGameObjectActions we;

    private Region mapRegion = new Region();
    private Region leftSideIconsRegion = new Region();

    public WeltSammelnActivities(WeltSammeln weltSammeln, AllGameObjectActions we,
                                 Items items, BDMGameRegionDefined bdoGameRegion) {
        this.weltSammeln = weltSammeln;
        this.we = we;
        this.items = items;
        this.bdoGameRegion = bdoGameRegion;
    }

    private boolean defineMapRegion() {
        if (mapRegion.defined()) return true;
        Region stateText = weltSammeln.getWeltSammelnStateImage().getSearchRegion();
        if (stateText == null) return false;
        mapRegion.x = stateText.x + stateText.w;
        mapRegion.y = stateText.y + stateText.h;
        mapRegion.w = bdoGameRegion.w - (mapRegion.x - bdoGameRegion.x);
        mapRegion.y = bdoGameRegion.y - (mapRegion.y - bdoGameRegion.y);
        leftSideIconsRegion.x = mapRegion.x;
        leftSideIconsRegion.y = mapRegion.y;
        leftSideIconsRegion.w = 300;
        leftSideIconsRegion.h = mapRegion.h;
        return true;
    }

    private boolean selectItemType(Item item) {
        defineMapRegion();
        Items.ItemType type = item.getItemType();
        System.out.print(" item type: "+type+" | ");
        StateRIP itemTypeImage = weltSammeln.getSammelTabs().get(type);
        return we.click().RIP(itemTypeImage);
    }

    private boolean selectSammelLocation(Item item) {
        StateImage itemIcon = item.getWeltSammelIcon();
        return we.click().image(leftSideIconsRegion, itemIcon);
    }

    private boolean aufgabeBeginnen() {
        we.click().RIP(weltSammeln.getWeltSammelnAlles());
        we.waitForImage().pause(.5);
        return we.click().RIP(weltSammeln.getAufgabeBeginnen());
    }

    public boolean weltSammeln(Items.Name... names) {
        for (Items.Name name : names) {
            sammelItem(name);
            we.waitForImage().pause(1.0);
        }
        return true;
    }

    private boolean sammelItem(Items.Name name) {
        Item item = items.getResource(name);
        System.out.print(" welt sammeln. ");
        if (selectItemType(item)) {
            selectSammelLocation(item);
            aufgabeBeginnen();
        }
        return true;
    }

}