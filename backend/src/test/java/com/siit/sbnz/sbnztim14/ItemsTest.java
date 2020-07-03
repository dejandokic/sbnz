package com.siit.sbnz.sbnztim14;

import com.siit.sbnz.sbnztim14.model.*;
import com.siit.sbnz.sbnztim14.service.ChampionService;
import com.siit.sbnz.sbnztim14.service.ItemService;
import org.drools.core.factmodel.Fact;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

public class ItemsTest {

    static ItemService itemService = new ItemService();

    static ChampionService championService = new ChampionService();
    static KieContainer kContainer;

    @BeforeClass
    public static void beforeClass() {
        KieServices kieServices = KieServices.Factory.get();
        kContainer = kieServices.getKieClasspathContainer();
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
            kSession.insert(it);
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

        assertEquals("Infinity Edge2",ally1.getWantedItem().getName());

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

    @Test
    public void testic(){


        KieSession kSession = kContainer.newKieSession("ksession-rules");

        Champion champion1 = championService.getChampionByName("Darius");
        champion1.setLane("top");
        Champion champion2 = championService.getChampionByName("Nasus");
        champion2.setLane("top");

         AllyChampion ally1 = new AllyChampion(champion1, "aggro");

        EnemyChampion enemy1 = new EnemyChampion(champion2);

        ItemBuy itemBuy1 = new ItemBuy();
        itemBuy1.setLane("top");
        itemBuy1.setValue(0);



        FactHandle fact = kSession.insert(ally1);
        kSession.insert(enemy1);
        kSession.insert(itemBuy1);

        for(Item it: itemService.getAllItems()){
            kSession.insert(it);
            if(it.getName().equals("Infinity Edge")){
                ArrayList<Item> list = new ArrayList<>();
                list.add(it);
                ally1.setBought(list);
                kSession.update(fact, ally1);
            }
        }

        kSession.insert(new GameEvent("top", EventType.ALLY_KILLS));
        kSession.insert(new GameEvent("top", EventType.ALLY_KILLS));
        kSession.insert(new GameEvent("top", EventType.ALLY_KILLS));

        kSession.getAgenda().getAgendaGroup("items").setFocus();
        int valu = kSession.fireAllRules();

        assertEquals("Infinity Edge2",ally1.getWantedItem().getName());


    }

}

