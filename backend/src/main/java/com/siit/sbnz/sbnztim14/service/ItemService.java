package com.siit.sbnz.sbnztim14.service;

import com.siit.sbnz.sbnztim14.model.FactTreeItem;
import com.siit.sbnz.sbnztim14.model.Item;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService {

    private ArrayList<Item> allItems;
    private final KieContainer kieContainer;

    @Autowired
    public ItemService(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

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

    public List<Item> getRemainingItemsToBuy(Item wantedItem, List<Item> boughtItems) {
        KieSession kSession = kieContainer.newKieSession("ksession-rules");

        ArrayList<FactTreeItem> treeItems = FactTreeItem.getFactTree(wantedItem);
        for (FactTreeItem fti: treeItems) {
//            System.out.println(fti);
            kSession.insert(fti);
        }
        System.out.println("\n");
        for (Item iterItem: boughtItems) {
//            System.out.println(iterItem);
            kSession.insert(iterItem);
        }
//        System.out.println("\n");

        kSession.setGlobal("toLookFor", wantedItem.getName());
        List<FactTreeItem> remainingItems = new ArrayList<>();
        kSession.setGlobal("remainingItems", remainingItems);
        kSession.getAgenda().getAgendaGroup("backward-chaining").setFocus();
        kSession.fireAllRules();
//        System.out.println("\n");
//        System.out.println(remainingItems);

        List<Item> remainingItemsRet = new ArrayList<>();
        for (FactTreeItem fti: remainingItems) {
            remainingItemsRet.add(new Item(getItemByName(fti.getSelf())));
        }
        return remainingItemsRet;
    }

    @PostConstruct
    private void constructItems() {
        allItems = new ArrayList<>();

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

        Item armorFull2 = new Item("Thorn Mail2",0,0,0,3,new ArrayList<>(),3150);
        armorFull2.getItems().add(new Item(armorMiddle));
        armorFull2.getItems().get(0).getItems().add(new Item(armorMini));
        armorFull2.getItems().get(0).getItems().add(new Item(armorMini));
        armorFull2.getItems().add(new Item(armorMini));



        Item mrFul = new Item("Abyssal Mask",0,0,3,0,new ArrayList<>(),3350);
        mrFul.getItems().add(new Item(mrMiddle));
        mrFul.getItems().add(new Item(mrMini));


        Item mrFul2 = new Item("Abyssal Mask2",0,0,3,0,new ArrayList<>(),3350);
        mrFul2.getItems().add(new Item(mrMiddle));
        mrFul2.getItems().add(new Item(mrMini));



        Item apFul = new Item("Rabadons Deathcap",0,3,0,0,new ArrayList<>(),3600);
        apFul.getItems().add(new Item(apMiddle));
        apFul.getItems().add(new Item(apMini));


        Item apFul2 = new Item("Rabadons Deathcap2",0,3,0,0,new ArrayList<>(),3600);
        apFul2.getItems().add(new Item(apMiddle));
        apFul2.getItems().add(new Item(apMini));



        Item adFul = new Item("Infinity Edge",3,0,0,0,new ArrayList<>(),3600);
        adFul.getItems().add(new Item(adMiddle));
        adFul.getItems().add(new Item(adMini));


        Item adFul2 = new Item("Infinity Edge2",3,0,0,0,new ArrayList<>(),3600);
        adFul2.getItems().add(new Item(adMiddle));
        adFul2.getItems().add(new Item(adMini));



        Item armor2adFull = new Item("Randuins Omen",1,0,0,2,new ArrayList<>(),2900);
        armor2adFull.getItems().add(new Item(armorMiddle));
        armor2adFull.getItems().add(new Item(adMini));



        Item armor2adFull2 = new Item("Randuins Omen2",1,0,0,2,new ArrayList<>(),2900);
        armor2adFull2.getItems().add(new Item(armorMiddle));
        armor2adFull2.getItems().add(new Item(adMini));


        Item armor2apFull = new Item("Iron Locket",0,1,0,2,new ArrayList<>(),3250);
        armor2apFull.getItems().add(new Item(armorMiddle));
        armor2apFull.getItems().add(new Item(apMini));


        Item armor2apFull2 = new Item("Iron Locket2",0,1,0,2,new ArrayList<>(),3250);
        armor2apFull2.getItems().add(new Item(armorMiddle));
        armor2apFull2.getItems().add(new Item(apMini));




        Item mr2adFull = new Item("Maw of Malmortius",1,0,2,0,new ArrayList<>(),3500);
        mr2adFull.getItems().add(new Item(mrMiddle));
        mr2adFull.getItems().add(new Item(adMini));


        Item mr2adFull2 = new Item("Maw of Malmortius2",1,0,2,0,new ArrayList<>(),3500);
        mr2adFull2.getItems().add(new Item(mrMiddle));
        mr2adFull2.getItems().add(new Item(adMini));



        Item mr2apFull = new Item("Spirit Visage",0,1,2,0,new ArrayList<>(),2800);
        mr2apFull.getItems().add(new Item(mrMiddle));
        mr2apFull.getItems().add(new Item(apMini));


        Item mr2apFull2 = new Item("Spirit Visage2",0,1,2,0,new ArrayList<>(),2800);
        mr2apFull2.getItems().add(new Item(mrMiddle));
        mr2apFull2.getItems().add(new Item(apMini));




        Item ad2armorFull = new Item("Iceborn Gauntlet",2,0,0,1,new ArrayList<>(),2900);
        ad2armorFull.getItems().add(new Item(adMiddle));
        ad2armorFull.getItems().add(new Item(armorMini));


        Item ad2armorFull2 = new Item("Iceborn Gauntlet2",2,0,0,1,new ArrayList<>(),2900);
        ad2armorFull2.getItems().add(new Item(adMiddle));
        ad2armorFull2.getItems().add(new Item(armorMini));


        Item ad2mrFull = new Item("Mercurial Scimitar",2,0,1,0,new ArrayList<>(),3400);
        ad2mrFull.getItems().add(new Item(adMiddle));
        ad2mrFull.getItems().add(new Item(mrMini));


        Item ad2mrFull2 = new Item("Mercurial Scimitar2",2,0,1,0,new ArrayList<>(),3400);
        ad2mrFull2.getItems().add(new Item(adMiddle));
        ad2mrFull2.getItems().add(new Item(mrMini));




        Item ap2armorFull = new Item("Zhonyas Hourglass",0,2,0,1,new ArrayList<>(),3200);
        ap2armorFull.getItems().add(new Item(apMiddle));
        ap2armorFull.getItems().add(new Item(armorMini));


        Item ap2armorFull2 = new Item("Zhonyas Hourglass2",0,2,0,1,new ArrayList<>(),3200);
        ap2armorFull2.getItems().add(new Item(apMiddle));
        ap2armorFull2.getItems().add(new Item(armorMini));



        Item ap2mrFull = new Item("Banshees Veil",0,2,1, 0,new ArrayList<>(),2850);
        ap2mrFull.getItems().add(new Item(apMiddle));
        ap2mrFull.getItems().add(new Item(mrMini));


        Item ap2mrFull2 = new Item("Banshees Veil2",0,2,1, 0,new ArrayList<>(),2850);
        ap2mrFull2.getItems().add(new Item(apMiddle));
        ap2mrFull2.getItems().add(new Item(mrMini));


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

        allItems.add(armorFull2);
        allItems.add(armor2adFull2);
        allItems.add(armor2apFull2);



        allItems.add(mrFul);
        allItems.add(mr2adFull);
        allItems.add(mr2apFull);


        allItems.add(mrFul2);
        allItems.add(mr2adFull2);
        allItems.add(mr2apFull2);

        allItems.add(adFul2);
        allItems.add(ad2armorFull2);
        allItems.add(ad2mrFull2);



        allItems.add(adFul);
        allItems.add(ad2armorFull);
        allItems.add(ad2mrFull);

        allItems.add(apFul);
        allItems.add(ap2armorFull);
        allItems.add(ap2mrFull);

        allItems.add(apFul2);
        allItems.add(ap2armorFull2);
        allItems.add(ap2mrFull2);
    }

}
