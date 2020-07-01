package com.siit.sbnz.sbnztim14.service;

import com.siit.sbnz.sbnztim14.model.Item;

import java.util.ArrayList;

public class ItemService {

    public ArrayList<Item> getAllItems(){

        ArrayList<Item> items = new ArrayList<>();

        Item armorMini = new Item("Cloth Armor",0,0,0,1,new ArrayList<>(),300);
        Item mrMini = new Item("Null-Magic Mantle",0,0,1,0,new ArrayList<>(),450);
        Item adMini = new Item("Short Sword",1,0,0,0,new ArrayList<>(),360);
        Item apMini = new Item("Amplifying Tome",0,1,0,0,new ArrayList<>(),435);

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


        Item armorMiddle = new Item("Wardens Mail",0,0,0,2, armorMiniList,300);
        Item mrMiddle = new Item("Negatron Cloack",0,0,2,0, mrMiniList,450);
        Item apMiddle = new Item("Blasting Wand",0,2,0,0, apMiniList,500);
        Item adMiddle = new Item("Serrated Dirk",2,0,0,0, adMiniList,450);



        Item armorFull = new Item("Thorn Mail",0,0,0,3,new ArrayList<>(),3150);
        armorFull.getItems().add(new Item(armorMiddle));
        armorFull.getItems().get(0).getItems().add(new Item(armorMini));
        armorFull.getItems().get(0).getItems().add(new Item(armorMini));
        armorFull.getItems().add(new Item(armorMini));

        Item mrFul = new Item("Abyssal Mask",0,0,3,0,new ArrayList<>(),3350);
        mrFul.getItems().add(new Item(mrMiddle));
        mrFul.getItems().get(0).getItems().add(new Item(mrMini));
        mrFul.getItems().get(0).getItems().add(new Item(mrMini));
        mrFul.getItems().add(new Item(mrMini));

        Item apFul = new Item("Rabadons Deathcap",0,3,0,0,new ArrayList<>(),3600);
        apFul.getItems().add(new Item(apMiddle));
        apFul.getItems().get(0).getItems().add(new Item(apMini));
        apFul.getItems().get(0).getItems().add(new Item(apMini));
        apFul.getItems().add(new Item(apMini));

        Item adFul = new Item("Infinity Edge",3,0,0,0,new ArrayList<>(),3600);
        adFul.getItems().add(new Item(adMiddle));
        adFul.getItems().get(0).getItems().add(new Item(adMini));
        adFul.getItems().get(0).getItems().add(new Item(adMini));
        adFul.getItems().add(new Item(adMini));


        Item armor2adFull = new Item("Randuins Omen",1,0,0,2,new ArrayList<>(),2900);
        armor2adFull.getItems().add(new Item(armorMiddle));
        armor2adFull.getItems().get(0).getItems().add(new Item(armorMini));
        armor2adFull.getItems().get(0).getItems().add(new Item(armorMini));
        armor2adFull.getItems().add(new Item(adMini));


        Item armor2apFull = new Item("Iron Locket",0,1,0,2,new ArrayList<>(),3250);
        armor2apFull.getItems().add(new Item(armorMiddle));
        armor2apFull.getItems().get(0).getItems().add(new Item(armorMini));
        armor2apFull.getItems().get(0).getItems().add(new Item(armorMini));
        armor2apFull.getItems().add(new Item(apMini));



        Item mr2adFull = new Item("Maw of Malmortius",1,0,2,0,new ArrayList<>(),3500);
        mr2adFull.getItems().add(new Item(mrMiddle));
        mr2adFull.getItems().get(0).getItems().add(new Item(mrMini));
        mr2adFull.getItems().get(0).getItems().add(new Item(mrMini));
        mr2adFull.getItems().add(new Item(adMini));


        Item mr2apFull = new Item("Spirit Visage",0,1,2,0,new ArrayList<>(),2800);
        mr2apFull.getItems().add(new Item(mrMiddle));
        mr2apFull.getItems().get(0).getItems().add(new Item(mrMini));
        mr2apFull.getItems().get(0).getItems().add(new Item(mrMini));
        mr2apFull.getItems().add(new Item(apMini));



        Item ad2armorFull = new Item("Iceborn Gauntlet",2,0,0,1,new ArrayList<>(),2900);
        ad2armorFull.getItems().add(new Item(adMiddle));
        ad2armorFull.getItems().get(0).getItems().add(new Item(adMini));
        ad2armorFull.getItems().get(0).getItems().add(new Item(adMini));
        ad2armorFull.getItems().add(new Item(armorMini));


        Item ad2mrFull = new Item("Mercurial Scimitar",2,0,1,0,new ArrayList<>(),3400);
        ad2mrFull.getItems().add(new Item(adMiddle));
        ad2mrFull.getItems().get(0).getItems().add(new Item(adMini));
        ad2mrFull.getItems().get(0).getItems().add(new Item(adMini));
        ad2mrFull.getItems().add(new Item(mrMini));



        Item ap2armorFull = new Item("Zhonyas Hourglass",0,2,0,1,new ArrayList<>(),3200);
        ap2armorFull.getItems().add(new Item(apMiddle));
        ap2armorFull.getItems().get(0).getItems().add(new Item(apMini));
        ap2armorFull.getItems().get(0).getItems().add(new Item(apMini));
        ap2armorFull.getItems().add(new Item(armorMini));


        Item ap2mrFull = new Item("Banshees Veil",0,2,1, 0,new ArrayList<>(),2850);
        ap2mrFull.getItems().add(new Item(apMiddle));
        ap2mrFull.getItems().get(0).getItems().add(new Item(apMini));
        ap2mrFull.getItems().get(0).getItems().add(new Item(apMini));
        ap2mrFull.getItems().add(new Item(mrMini));

        items.add(armorMini);
        items.add(mrMini);
        items.add(adMini);
        items.add(apMini);


        items.add(armorMiddle);
        items.add(mrMiddle);
        items.add(adMiddle);
        items.add(apMiddle);


        items.add(armorFull);
        items.add(armor2adFull);
        items.add(armor2apFull);


        items.add(mrFul);
        items.add(mr2adFull);
        items.add(mr2apFull);



        items.add(adFul);
        items.add(ad2armorFull);
        items.add(ad2mrFull);



        items.add(apFul);
        items.add(ap2armorFull);
        items.add(ap2mrFull);

        return items;
    }

}
