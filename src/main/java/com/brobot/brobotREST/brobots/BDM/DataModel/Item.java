package com.brobot.brobotREST.brobots.BDM.DataModel;

import com.brobot.brobotREST.database.primatives.Image;
import com.brobot.brobotREST.database.primatives.RegionImagePairs;
import lombok.Data;

public @Data class Item {

    private Items.Name name;
    private Items.ItemType itemType;
    private Image weltSammelIcon;
    private RegionImagePairs ressourcenIcon;

    public Item(Items.Name name, Items.ItemType itemType, Image weltSammelIcon, RegionImagePairs ressourcenIcon) {
        this.name = name;
        this.itemType = itemType;
        this.weltSammelIcon = weltSammelIcon;
        this.ressourcenIcon = ressourcenIcon;
    }

    public Item(Items.Name name, Items.ItemType itemType, Image weltSammelIcon) {
        this.name = name;
        this.itemType = itemType;
        this.weltSammelIcon = weltSammelIcon;
    }

}
