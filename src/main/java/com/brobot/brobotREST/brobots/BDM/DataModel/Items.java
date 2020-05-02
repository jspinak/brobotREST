package com.brobot.brobotREST.brobots.BDM.DataModel;

import com.brobot.brobotREST.database.primatives.Image;
import com.brobot.brobotREST.database.primatives.RegionImagePairs;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Items {

    public enum ItemType {
        PFLANZE, ERZ, HOLZ, SAMEN
    }

    public enum Name {
        WILDKRAUT, RAMIE, BAUMWOLLE, SEIDENKOKON,
        HOLZSCHEIT, WEICHES_HOLZ, LEICHTES_HOLZ, STABILES_HOLZ,
        GROBER_STEIN, KUPFERERZ, EISENERZ, ZINNERZ,
        TOMATENSAMEN
    }

    private Map<Name, Item> ressources = new HashMap<>();
    private Map<Name, Item> items = new HashMap<>();

    public Items() {
        initRessources();
        initItems();
    }
    
    private void initRessources() {
        addRessource(Name.BAUMWOLLE, ItemType.PFLANZE, "", "");
        addRessource(Name.EISENERZ, ItemType.ERZ, "eisenerzWeltSammeln", "");
        addRessource(Name.GROBER_STEIN, ItemType.ERZ, "groberSteinWeltSammeln", "");
        addRessource(Name.HOLZSCHEIT, ItemType.HOLZ, "holzscheitIconWeltSammeln", "");
        addRessource(Name.KUPFERERZ, ItemType.ERZ, "kupfererzWeltSammeln", "");
        addRessource(Name.LEICHTES_HOLZ, ItemType.HOLZ, "leichtesHolzIconWeltSammeln", "");
        addRessource(Name.RAMIE, ItemType.PFLANZE, "", "");
        addRessource(Name.SEIDENKOKON, ItemType.PFLANZE, "", "");
        addRessource(Name.STABILES_HOLZ, ItemType.HOLZ, "stabilesHolzIconWeltSammeln", "");
        addRessource(Name.WEICHES_HOLZ, ItemType.HOLZ, "weichesHolzIconWeltSammeln", "");
        addRessource(Name.WILDKRAUT, ItemType.PFLANZE, "", "");
        addRessource(Name.ZINNERZ, ItemType.ERZ, "zinnerzWeltSammeln", "");
    }
    
    private void initItems() {
        addItem(Name.TOMATENSAMEN, ItemType.SAMEN, "tomatensamen");
    }

    private void addRessource(Name name, ItemType itemType, String weltSammelIcon, String ressourcenIcon) {
        Item newItem = new Item(name, itemType, new Image(weltSammelIcon), new RegionImagePairs(ressourcenIcon));
        ressources.put(name, newItem);
    }

    private void addItem(Name name, ItemType itemType, String weltSammelIcon) {
        Item newItem = new Item(name, itemType, new Image(weltSammelIcon));
        items.put(name, newItem);
    }

    public Item getResource(Name name) {
        return ressources.get(name);
    }

    public Item getItem(Name name) { return items.get(name); }
}
