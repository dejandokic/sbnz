package com.siit.sbnz.sbnztim14;

import com.siit.sbnz.sbnztim14.model.AdviceStorage;
import com.siit.sbnz.sbnztim14.model.AllyChampion;
import com.siit.sbnz.sbnztim14.model.Champion;
import com.siit.sbnz.sbnztim14.model.EnemyChampion;
import com.siit.sbnz.sbnztim14.model.Item;
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
import static org.junit.Assert.assertNull;

public class PurchaseItemsTest {

    static ChampionService championService = new ChampionService();
    static KieContainer kContainer;
    static ItemService itemService = new ItemService();

    @BeforeClass
    public static void beforeClass() {
        KieServices kieServices = KieServices.Factory.get();
        kContainer = kieServices.getKieClasspathContainer();
    }

    @Test
    public void changePlayTypeTest() {
        KieSession kSession = kContainer.newKieSession("ksession-rules");
        kSession.setGlobal("adviceStorage", new AdviceStorage());

        // Choose 10 champs
        Champion champion1 = championService.getChampionByName("Darius");
        champion1.setLane("top");

        // Ally team
        AllyChampion ally1 = new AllyChampion(champion1, "aggro");

        ArrayList<Item> allItems = itemService.getAllItems();
        ArrayList<Item> toBuy = new ArrayList<>();
        Item wantedItem = null;

        for (Item item : allItems) {
            if (item.getName().equals("Zhonyas Hourglass")) {
                wantedItem = item;
                System.out.println(item.getName() + " " + item.getGold());
            }
            if (item.getName().equals("Amplifying Tome")) {
                toBuy.add(item);
                toBuy.add(item);
                toBuy.add(item);
                System.out.println(item.getName() + " " + item.getGold());
            }
            if (item.getName().equals("Blasting Wand")) {
                toBuy.add(item);
                System.out.println(item.getName() + " " + item.getGold());
            }
        }

        ally1.setWantedItem(wantedItem);
        ally1.setToBuy(toBuy);
        ally1.setGolds(900);

        kSession.getAgenda().getAgendaGroup("purchase-item").setFocus();

        // Insert ally
        FactHandle allyHandle = kSession.insert(ally1);

        assertEquals(4, ally1.getToBuy().size());

        int fired = kSession.fireAllRules();
        assertEquals(2, fired);
        assertEquals(2, ally1.getToBuy().size());
        assertEquals(2, ally1.getBought().size());

        ally1.setGolds(ally1.getGolds() + 1200);
        kSession.update(allyHandle, ally1);

        kSession.getAgenda().getAgendaGroup("purchase-item").setFocus();
        fired = kSession.fireAllRules();
        assertEquals(2, fired);
        assertEquals(0, ally1.getToBuy().size());
        assertNull(ally1.getWantedItem());
        assertEquals(1, ally1.getBought().size());

        kSession.dispose();
    }
}
