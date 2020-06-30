package com.siit.sbnz.sbnztim14;

import com.siit.sbnz.sbnztim14.model.*;
import com.siit.sbnz.sbnztim14.service.ChampionService;
import com.siit.sbnz.sbnztim14.service.ItemService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

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

        // Choose 10 champs
        Champion champion1 = championService.getChampionByName("Annie");
        champion1.setLane("top");
        Champion champion2 = championService.getChampionByName("Akali");
        champion2.setLane("top");

        AllyChampion ally1 = new AllyChampion(champion1, "aggro");

        EnemyChampion enemy1 = new EnemyChampion(champion2);


        ItemBuy itemBuy1 = new ItemBuy();
        itemBuy1.setAllyChampion(ally1);
        itemBuy1.setEnemyChampion(enemy1);
        itemBuy1.setLane("top");
        itemBuy1.setValue(0);


        FactHandle fact = kSession.insert(ally1);
        kSession.insert(enemy1);
        kSession.insert(itemBuy1);

        for(Item it: itemService.getAllItems()){
            kSession.insert(it);
        }


        kSession.insert(new GameEvent("top", ally1, enemy1, EventType.ALLY_KILLS));
        kSession.insert(new GameEvent("top", ally1, enemy1, EventType.ALLY_KILLS));
        kSession.insert(new GameEvent("top", ally1, enemy1, EventType.ALLY_KILLS));
        kSession.insert(new GameEvent("top", ally1, enemy1, EventType.ALLY_KILLS));

        kSession.getAgenda().getAgendaGroup("items").setFocus();
        int valu = kSession.fireAllRules();

//        ArrayList<Item> i = new ArrayList<>();
//
//
//        for(Item it: itemService.getAllItems()){
//            if(it.getName().equals("Rabadons Deatchap")){
//                i.add(it);
//            }
//        }
//
//        ally1.setBought(i);
//
//        kSession.update(fact, ally1);

        int valu2 = kSession.fireAllRules();

        System.out.println(valu + " - " + valu2);

    }
}

