package com.siit.sbnz.sbnztim14.service;

import com.siit.sbnz.sbnztim14.model.Item;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

@Service
public class ItemService {

    private ArrayList<Item> allItems;

    public ArrayList<Item> getAllItems(){
        return allItems;
    }

    public Item getItemByName(String name) {
        for (Item item : allItems) {
            if (item.getName().equals(name))
                return item;
        }
        return null;
    }

    @PostConstruct
    private void constructItems() {
        allItems = new ArrayList<>();

        Item armorMini = new Item("Cloth Armor",0,0,0,1,new ArrayList<>(), false);
        Item mrMini = new Item("Null-Magic Mantle",0,0,1,0,new ArrayList<>(), false);
        Item adMini = new Item("Short Sword",1,0,0,0,new ArrayList<>(), false);
        Item apMini = new Item("Amplifying Tome",0,1,0,0,new ArrayList<>(), false);

        ArrayList<Item> armorMiniList = new ArrayList<>();
        armorMiniList.add(new Item(armorMini));
        armorMiniList.add(new Item(armorMini));

        ArrayList<Item> mrMiniList = new ArrayList<>();
        mrMiniList.add(new Item(mrMini));
        mrMiniList.add(new Item(mrMini));

        ArrayList<Item> adMiniList = new ArrayList<>();
        adMiniList.add(new Item(adMini));
        adMiniList.add(new Item(adMini));

        ArrayList<Item> apMiniList = new ArrayList<>();
        apMiniList.add(new Item(apMini));
        apMiniList.add(new Item(apMini));

        Item armorMiddle = new Item("Wardens Mail",0,0,0,2, armorMiniList, false);
        Item mrMiddle = new Item("Negatron Cloack",0,0,2,0, mrMiniList, false);
        Item apMiddle = new Item("Blasting Wand",0,2,0,0, apMiniList, false);
        Item adMiddle = new Item("Serrated Dirk",2,0,0,0, adMiniList, false);

        Item armorFull = new Item("Thorn Mail",0,0,0,3,new ArrayList<>(), false);
        armorFull.getItems().add(new Item(armorMiddle));
        armorFull.getItems().get(0).getItems().add(new Item(armorMini));
        armorFull.getItems().get(0).getItems().add(new Item(armorMini));
        armorFull.getItems().add(new Item(armorMini));

        Item mrFul = new Item("Abyssal Mask",0,0,3,0,new ArrayList<>(), false);
        mrFul.getItems().add(new Item(mrMiddle));
        mrFul.getItems().get(0).getItems().add(new Item(mrMini));
        mrFul.getItems().get(0).getItems().add(new Item(mrMini));
        mrFul.getItems().add(new Item(mrMini));

        Item apFul = new Item("Rabadons Deathcap",0,3,0,0,new ArrayList<>(), false);
        apFul.getItems().add(new Item(apMiddle));
        apFul.getItems().get(0).getItems().add(new Item(apMini));
        apFul.getItems().get(0).getItems().add(new Item(apMini));
        apFul.getItems().add(new Item(apMini));

        Item adFul = new Item("Infinity Edge",3,0,0,0,new ArrayList<>(), false);
        adFul.getItems().add(new Item(adMiddle));
        adFul.getItems().get(0).getItems().add(new Item(adMini));
        adFul.getItems().get(0).getItems().add(new Item(adMini));
        adFul.getItems().add(new Item(adMini));

        Item armor2adFull = new Item("Randuins Omen",1,0,0,2,new ArrayList<>(), false);
        armor2adFull.getItems().add(new Item(armorMiddle));
        armor2adFull.getItems().get(0).getItems().add(new Item(armorMini));
        armor2adFull.getItems().get(0).getItems().add(new Item(armorMini));
        armor2adFull.getItems().add(new Item(adMini));

        Item armor2apFull = new Item("Iron Locket",0,1,0,2,new ArrayList<>(), false);
        armor2apFull.getItems().add(new Item(armorMiddle));
        armor2apFull.getItems().get(0).getItems().add(new Item(armorMini));
        armor2apFull.getItems().get(0).getItems().add(new Item(armorMini));
        armor2apFull.getItems().add(new Item(apMini));

        Item mr2adFull = new Item("Maw of Malmortius",1,0,2,0,new ArrayList<>(), false);
        mr2adFull.getItems().add(new Item(mrMiddle));
        mr2adFull.getItems().get(0).getItems().add(new Item(mrMini));
        mr2adFull.getItems().get(0).getItems().add(new Item(mrMini));
        mr2adFull.getItems().add(new Item(adMini));

        Item mr2apFull = new Item("Spirit Visage",0,1,2,0,new ArrayList<>(), false);
        mr2apFull.getItems().add(new Item(mrMiddle));
        mr2apFull.getItems().get(0).getItems().add(new Item(mrMini));
        mr2apFull.getItems().get(0).getItems().add(new Item(mrMini));
        mr2apFull.getItems().add(new Item(apMini));

        Item ad2armorFull = new Item("Iceborn Gauntlet",2,0,0,1,new ArrayList<>(), false);
        ad2armorFull.getItems().add(new Item(adMiddle));
        ad2armorFull.getItems().get(0).getItems().add(new Item(adMini));
        ad2armorFull.getItems().get(0).getItems().add(new Item(adMini));
        ad2armorFull.getItems().add(new Item(armorMini));

        Item ad2mrFull = new Item("Mercurial Scimitar",2,0,1,0,new ArrayList<>(), false);
        ad2mrFull.getItems().add(new Item(adMiddle));
        ad2mrFull.getItems().get(0).getItems().add(new Item(adMini));
        ad2mrFull.getItems().get(0).getItems().add(new Item(adMini));
        ad2mrFull.getItems().add(new Item(mrMini));

        Item ap2armorFull = new Item("Zhonyas Hourglass",0,2,0,1,new ArrayList<>(), false);
        ap2armorFull.getItems().add(new Item(apMiddle));
        ap2armorFull.getItems().get(0).getItems().add(new Item(apMini));
        ap2armorFull.getItems().get(0).getItems().add(new Item(apMini));
        ap2armorFull.getItems().add(new Item(armorMini));

        Item ap2mrFull = new Item("Banshees Veil",0,2,1, 0,new ArrayList<>(), false);
        ap2mrFull.getItems().add(new Item(apMiddle));
        ap2mrFull.getItems().get(0).getItems().add(new Item(apMini));
        ap2mrFull.getItems().get(0).getItems().add(new Item(apMini));
        ap2mrFull.getItems().add(new Item(mrMini));

        allItems.add(armorMini);
        allItems.add(mrMini);
        allItems.add(adMini);
        allItems.add(apMini);

        allItems.add(armorMiddle);
        allItems.add(mrMiddle);
        allItems.add(adMiddle);
        allItems.add(apMiddle);

        allItems.add(armorFull);
        allItems.add(armor2adFull);
        allItems.add(armor2apFull);

        allItems.add(mrFul);
        allItems.add(mr2adFull);
        allItems.add(mr2apFull);

        allItems.add(adFul);
        allItems.add(ad2armorFull);
        allItems.add(ad2mrFull);

        allItems.add(apFul);
        allItems.add(ap2armorFull);
        allItems.add(ap2mrFull);
    }

}
