package com.siit.sbnz.sbnztim14;

import com.siit.sbnz.sbnztim14.model.*;
import com.siit.sbnz.sbnztim14.service.ChampionService;
import com.siit.sbnz.sbnztim14.service.ItemService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import org.kie.api.runtime.rule.FactHandle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemsTest {

    @Autowired
    private ItemService itemService;

    static ChampionService championService;
    static KieContainer kContainer;
    KieSession kSession;

    @BeforeClass
    public static void beforeClass() {
        KieServices kieServices = KieServices.Factory.get();
        kContainer = kieServices.getKieClasspathContainer();
        championService = new ChampionService();
    }

    @Before
    public void beforeEach() {
        kSession = kContainer.newKieSession("ksession-rules");
    }

    @Test
    public void getItemFactTreeTest() {
        Item toBuy = itemService.getItemByName("Infinity Edge");
        List<Item> boughtItems = new ArrayList<>();
        boughtItems.add(new Item(itemService.getItemByName("Short Sword")));
        boughtItems.add(new Item(itemService.getItemByName("Serrated Dirk")));
        boughtItems.add(new Item(itemService.getItemByName("Amplifying Tome")));
        boughtItems.add(new Item(itemService.getItemByName("Abyssal Mask")));
        boughtItems.add(new Item(itemService.getItemByName("Short Sword")));
        List<Item> retList = itemService.getRemainingItemsToBuy(toBuy, boughtItems);

        assertEquals(1, retList.size());
        assertEquals("Infinity Edge", retList.get(0).getName());

        toBuy = itemService.getItemByName("Spirit Visage");
        for (Item iterItem: boughtItems) {
            iterItem.setHasBeenInBackwards(false);
        }
        retList = itemService.getRemainingItemsToBuy(toBuy, boughtItems);

        assertEquals(4, retList.size());

        int numSV = 0, numNC = 0, numNMM = 0;
        for (Item iterItem: retList) {
            if (iterItem.getName().equals("Null-Magic Mantle")) {
                numNMM++;
            }
            if (iterItem.getName().equals("Negatron Cloack")) {
                numNC++;
            }
            if (iterItem.getName().equals("Spirit Visage")) {
                numSV++;
            }
        }

        assertEquals(1, numSV);
        assertEquals(1, numNC);
        assertEquals(2, numNMM);
    }

    @Test
    public void itemBasic() {

        KieSession kSession = kContainer.newKieSession("ksession-rules");

        Champion champion1 = championService.getChampionByName("Darius");
        champion1.setLane("top");
        Champion champion2 = championService.getChampionByName("Nasus");
        champion2.setLane("top");
        Champion champion3 = championService.getChampionByName("Rengar");
        champion3.setLane("jungle");
        Champion champion4 = championService.getChampionByName("Sejuani");
        champion4.setLane("jungle");
        Champion champion5 = championService.getChampionByName("Annie");
        champion5.setLane("mid");
        Champion champion6 = championService.getChampionByName("Lux");
        champion6.setLane("mid");
        Champion champion7 = championService.getChampionByName("Vayne");
        champion7.setLane("adc");
        Champion champion8 = championService.getChampionByName("Varus");
        champion8.setLane("adc");
        Champion champion9 = championService.getChampionByName("Zilean");
        champion9.setLane("support");
        Champion champion10 = championService.getChampionByName("Braum");
        champion10.setLane("support");

        // Ally team
        AllyChampion ally1 = new AllyChampion(champion1, "aggro");
        AllyChampion ally2 = new AllyChampion(champion3, "aggro");
        AllyChampion ally3 = new AllyChampion(champion5, "def");
        AllyChampion ally4 = new AllyChampion(champion7, "aggro");
        AllyChampion ally5 = new AllyChampion(champion9, "def");

        // Enemy team
        EnemyChampion enemy1 = new EnemyChampion(champion2);
        EnemyChampion enemy2 = new EnemyChampion(champion4);
        EnemyChampion enemy3 = new EnemyChampion(champion6);
        EnemyChampion enemy4 = new EnemyChampion(champion8);
        EnemyChampion enemy5 = new EnemyChampion(champion10);

        ItemBuy itemBuy1 = new ItemBuy();
        itemBuy1.setLane("top");
        itemBuy1.setValue(0);
        ItemBuy itemBuy2 = new ItemBuy();
        itemBuy2.setLane("jungle");
        itemBuy2.setValue(0);
        ItemBuy itemBuy3 = new ItemBuy();
        itemBuy3.setLane("mid");
        itemBuy3.setValue(0);
        ItemBuy itemBuy4 = new ItemBuy();
        itemBuy4.setLane("adc");
        itemBuy4.setValue(0);
        ItemBuy itemBuy5 = new ItemBuy();
        itemBuy5.setLane("support");
        itemBuy5.setValue(0);




        for(Item it: itemService.getAllItems()){
            //Wanted item already set
            if(it.getName().equals("Mercurial Scimitar")){
                ally2.setWantedItem(it);
            }else if(it.getName().equals("Spirit Visage")){
                ally5.setWantedItem(it);
            }
            //Item already bought for top
            if(it.getName().equals("Infinity Edge")){
                ArrayList<Item> list = new ArrayList<>();
                list.add(it);
                ally1.setBought(list);
            }
        }


        // Insert ally
        kSession.insert(ally1);
        kSession.insert(ally2);
        kSession.insert(ally3);
        kSession.insert(ally4);
        kSession.insert(ally5);

        // Insert enemy
        kSession.insert(enemy1);
        kSession.insert(enemy2);
        kSession.insert(enemy3);
        kSession.insert(enemy4);
        kSession.insert(enemy5);

        // Insert item points
        kSession.insert(itemBuy1);
        kSession.insert(itemBuy2);
        kSession.insert(itemBuy3);
        kSession.insert(itemBuy4);
        kSession.insert(itemBuy5);

        // Insert items
        for(Item it: itemService.getAllItems()){
            kSession.insert(it);
        }


        kSession.insert(new GameEvent("top", EventType.ALLY_KILLS));
        kSession.insert(new GameEvent("top", EventType.ALLY_KILLS));
        kSession.insert(new GameEvent("top", EventType.ALLY_KILLS));

        kSession.insert(new GameEvent("jungle", EventType.ALLY_KILLS));
        kSession.insert(new GameEvent("jungle", EventType.ALLY_KILLS));

        kSession.insert(new GameEvent("mid", EventType.ALLY_KILLS));
        kSession.insert(new GameEvent("mid", EventType.ALLY_KILLS));

        kSession.insert(new GameEvent("adc", EventType.ENEMY_KILLS));
        kSession.insert(new GameEvent("adc", EventType.ENEMY_KILLS));
        kSession.insert(new GameEvent("adc", EventType.ALLY_KILLS));

        kSession.insert(new GameEvent("support", EventType.ENEMY_KILLS));


        kSession.getAgenda().getAgendaGroup("items").setFocus();
        int valu = kSession.fireAllRules();

        assertEquals(9, valu);

        assertEquals("Infinity Edge",ally1.getWantedItem().getName());

        assertEquals(0,ally2.getBought().size());

        assertEquals("Banshees Veil",ally3.getWantedItem().getName());

        assertEquals("Randuins Omen",ally4.getWantedItem().getName());


        assertEquals(0,ally5.getBought().size());

//        Collection objects = kSession.getObjects();
//        for (Object fact : objects )
//        {
//            FactHandle factHandle = kSession.getFactHandle(fact);
//            kSession.update(factHandle, fact);
//        }


    }
}

