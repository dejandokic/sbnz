package com.siit.sbnz.sbnztim14;

import com.siit.sbnz.sbnztim14.model.AdviceStorage;
import com.siit.sbnz.sbnztim14.model.AllyChampion;
import com.siit.sbnz.sbnztim14.model.Champion;
import com.siit.sbnz.sbnztim14.model.EnemyChampion;
import com.siit.sbnz.sbnztim14.service.ChampionService;
import com.siit.sbnz.sbnztim14.service.ItemService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ComplexRuleTest {

    @Autowired
    private ItemService itemService;

    static ChampionService championService = new ChampionService();

    @Autowired
    private KieContainer kContainer;

    KieSession kSession;

    @Before
    public void beforeEach() {
        kSession = kContainer.newKieSession("ksession-rules");
    }

    @Test
    public void complexRuleTest() {

        Champion champion1 = championService.getChampionByName("Darius");
        champion1.setLane("top");
        Champion champion2 = championService.getChampionByName("Renekton");
        champion2.setLane("top");
        Champion champion3 = championService.getChampionByName("Rengar");
        champion3.setLane("jungle");
        Champion champion4 = championService.getChampionByName("Aatrox");
        champion4.setLane("jungle");
        Champion champion5 = championService.getChampionByName("Annie");
        champion5.setLane("mid");
        Champion champion6 = championService.getChampionByName("Talon");
        champion6.setLane("mid");
        Champion champion7 = championService.getChampionByName("Vayne");
        champion7.setLane("adc");
        Champion champion8 = championService.getChampionByName("Varus");
        champion8.setLane("adc");
        Champion champion9 = championService.getChampionByName("Zilean");
        champion9.setLane("support");
        Champion champion10 = championService.getChampionByName("Pyke");
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

        ally1.setWantedItem(itemService.getItemByName("Iceborn Gauntlet"));
        ally2.setWantedItem(itemService.getItemByName("Infinity Edge"));
        ally3.setWantedItem(itemService.getItemByName("Rabadons Deathcap"));
        ally4.setWantedItem(itemService.getItemByName("Infinity Edge"));
        ally5.setWantedItem(itemService.getItemByName("Iron Locket"));

        ally1.getBought().add(itemService.getItemByName("Cloth Armor"));
        ally1.getBought().add(itemService.getItemByName("Short Sword"));

        ally2.getBought().add(itemService.getItemByName("Short Sword"));
        ally2.getBought().add(itemService.getItemByName("Short Sword"));

        ally3.getBought().add(itemService.getItemByName("Null-Magic Mantle"));
        ally3.getBought().add(itemService.getItemByName("Amplifying Tome"));

        ally4.getBought().add(itemService.getItemByName("Short Sword"));
        ally4.getBought().add(itemService.getItemByName("Short Sword"));

        ally5.getBought().add(itemService.getItemByName("Cloth Armor"));
        ally5.getBought().add(itemService.getItemByName("Cloth Armor"));

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

        kSession.setGlobal("adviceStorage", new AdviceStorage());

        kSession.getAgenda().getAgendaGroup("complex-rule").setFocus();
        int fired = kSession.fireAllRules();
        AdviceStorage adviceStorage = (AdviceStorage) kSession.getGlobal("adviceStorage");

        assertEquals(1, fired);
        assertEquals(1, adviceStorage.getAdvices().size());

        ally3.setWantedItem(itemService.getItemByName("Zhonyas Hourglass"));
        fired = kSession.fireAllRules();
        adviceStorage = (AdviceStorage) kSession.getGlobal("adviceStorage");

        assertEquals(0, fired);
        assertEquals(1, adviceStorage.getAdvices().size());

    }
}
