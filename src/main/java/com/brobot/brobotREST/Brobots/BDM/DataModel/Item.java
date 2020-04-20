package com.brobot.brobotREST.Brobots.BDM.DataModel;

import com.brobot.brobotREST.StateObjects.BaseObjects.Image.StateImage;
import com.brobot.brobotREST.StateObjects.BaseObjects.RIP.StateRIP;
import lombok.Data;

public @Data class Item {

    private Items.Name name;
    private Items.ItemType itemType;
    private StateImage weltSammelIcon;
    private StateRIP ressourcenIcon;

    public Item(Items.Name name, Items.ItemType itemType, StateImage weltSammelIcon, StateRIP ressourcenIcon) {
        this.name = name;
        this.itemType = itemType;
        this.weltSammelIcon = weltSammelIcon;
        this.ressourcenIcon = ressourcenIcon;
    }

    public Item(Items.Name name, Items.ItemType itemType, StateImage weltSammelIcon) {
        this.name = name;
        this.itemType = itemType;
        this.weltSammelIcon = weltSammelIcon;
    }

}
