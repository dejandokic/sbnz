package com.siit.sbnz.sbnztim14;


import com.siit.sbnz.sbnztim14.model.AdviceStorage;
import com.siit.sbnz.sbnztim14.model.AllyChampion;
import com.siit.sbnz.sbnztim14.model.Champion;
import com.siit.sbnz.sbnztim14.model.EnemyChampion;
import com.siit.sbnz.sbnztim14.service.ChampionService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import static org.junit.Assert.*;


public class PlayTypeTest {

    static ChampionService championService = new ChampionService();
    static KieContainer kContainer;

    @BeforeClass
    public static void beforeClass() {
        KieServices kieServices = KieServices.Factory.get();
        kContainer = kieServices.getKieClasspathContainer();
    }

    // Top and mid will be countered, so they change preferred aggro play type to def
    @Test
    public void enemyCounterAlly() {
        KieSession kSession = kContainer.newKieSession("ksession-rules");
        kSession.setGlobal("adviceStorage", new AdviceStorage());

        // Darius countered by Kennen
        // Annie countered by Morgana

        // Ally 5 champs
        Champion champion1 = championService.getChampionByName("Darius");
        champion1.setLane("top");
        Champion champion2 = championService.getChampionByName("Rengar");
        champion2.setLane("jungle");
        Champion champion3 = championService.getChampionByName("Annie");
        champion3.setLane("mid");
        Champion champion4 = championService.getChampionByName("Vayne");
        champion4.setLane("adc");
        Champion champion5 = championService.getChampionByName("Zilean");
        champion5.setLane("support");

        // Enemy 5 champs
        Champion champion6 = championService.getChampionByName("Kennen");
        champion6.setLane("top");
        Champion champion7 = championService.getChampionByName("Sejuani");
        champion7.setLane("jungle");
        Champion champion8 = championService.getChampionByName("Morgana");
        champion8.setLane("mid");
        Champion champion9 = championService.getChampionByName("Varus");
        champion9.setLane("adc");
        Champion champion10 = championService.getChampionByName("Braum");
        champion10.setLane("support");

        // Ally team
        AllyChampion allyTop = new AllyChampion(champion1, "aggro");
        AllyChampion allyJungle = new AllyChampion(champion2, "aggro");
        AllyChampion allyMid = new AllyChampion(champion3, "aggro");
        AllyChampion allyADC = new AllyChampion(champion4, "aggro");
        AllyChampion allySupport = new AllyChampion(champion5, "aggro");

        // Enemy team
        EnemyChampion enemyTop = new EnemyChampion(champion6);
        EnemyChampion enemyJungle = new EnemyChampion(champion7);
        EnemyChampion enemyMid = new EnemyChampion(champion8);
        EnemyChampion enemyADC = new EnemyChampion(champion9);
        EnemyChampion enemySupport = new EnemyChampion(champion10);

        // Insert ally
        kSession.insert(allyTop);
        kSession.insert(allyJungle);
        kSession.insert(allyMid);
        kSession.insert(allyADC);
        kSession.insert(allySupport);

        // Insert enemy
        kSession.insert(enemyTop);
        kSession.insert(enemyJungle);
        kSession.insert(enemyMid);
        kSession.insert(enemyADC);
        kSession.insert(enemySupport);

        assertEquals("aggro", allyTop.getPlayType());
        assertEquals("aggro", allyMid.getPlayType());

        kSession.getAgenda().getAgendaGroup("play-type").setFocus();
        int fired = kSession.fireAllRules();
        AdviceStorage adviceStorage = (AdviceStorage) kSession.getGlobal("adviceStorage");

        assertEquals(4, adviceStorage.getAdvices().size());

        assertEquals("def", allyTop.getPlayType());
        assertEquals("def", allyMid.getPlayType());

        kSession.dispose();
    }



    // Top ally strong in early game, enemy top weak (no counters)
    // Mid ally weak in early game, enemy mid strong (no counters)
    @Test
    public void earlyGameStrongVSWeak() {
        KieSession kSession = kContainer.newKieSession("ksession-rules");
        kSession.setGlobal("adviceStorage", new AdviceStorage());

        // Ally 5 champs
        Champion champion1 = championService.getChampionByName("Darius");
        champion1.setLane("top");
        Champion champion2 = championService.getChampionByName("Rengar");
        champion2.setLane("jungle");
        Champion champion3 = championService.getChampionByName("Annie");
        champion3.setLane("mid");
        Champion champion4 = championService.getChampionByName("Vayne");
        champion4.setLane("adc");
        Champion champion5 = championService.getChampionByName("Zilean");
        champion5.setLane("support");

        // Enemy 5 champs
        Champion champion6 = championService.getChampionByName("Dr. Mundo");
        champion6.setLane("top");
        Champion champion7 = championService.getChampionByName("Sejuani");
        champion7.setLane("jungle");
        Champion champion8 = championService.getChampionByName("Ekko");
        champion8.setLane("mid");
        Champion champion9 = championService.getChampionByName("Varus");
        champion9.setLane("adc");
        Champion champion10 = championService.getChampionByName("Braum");
        champion10.setLane("support");

        // Ally team
        AllyChampion allyTop = new AllyChampion(champion1, "def");
        AllyChampion allyJungle = new AllyChampion(champion2, "def");
        AllyChampion allyMid = new AllyChampion(champion3, "aggro");
        AllyChampion allyADC = new AllyChampion(champion4, "aggro");
        AllyChampion allySupport = new AllyChampion(champion5, "aggro");

        // Enemy team
        EnemyChampion enemyTop = new EnemyChampion(champion6);
        EnemyChampion enemyJungle = new EnemyChampion(champion7);
        EnemyChampion enemyMid = new EnemyChampion(champion8);
        EnemyChampion enemyADC = new EnemyChampion(champion9);
        EnemyChampion enemySupport = new EnemyChampion(champion10);

        // Insert ally
        kSession.insert(allyTop);
        kSession.insert(allyJungle);
        kSession.insert(allyMid);
        kSession.insert(allyADC);
        kSession.insert(allySupport);

        // Insert enemy
        kSession.insert(enemyTop);
        kSession.insert(enemyJungle);
        kSession.insert(enemyMid);
        kSession.insert(enemyADC);
        kSession.insert(enemySupport);

        assertEquals("def", allyTop.getPlayType());
        assertEquals("aggro", allyMid.getPlayType());

        kSession.getAgenda().getAgendaGroup("play-type").setFocus();
        int fired = kSession.fireAllRules();
        AdviceStorage adviceStorage = (AdviceStorage) kSession.getGlobal("adviceStorage");

        assertEquals(4, adviceStorage.getAdvices().size());

        assertEquals("aggro", allyTop.getPlayType());
        assertEquals("def", allyMid.getPlayType());

        kSession.dispose();
    }


    // Top ally strong in early game, enemy top also strong (no counters)
    // Mid ally weak in early game, enemy mid also weak (no counters)
    @Test
    public void earlyGameBothStrongOrWeak() {
        KieSession kSession = kContainer.newKieSession("ksession-rules");
        kSession.setGlobal("adviceStorage", new AdviceStorage());

        // Fiora and Dr. Mundo both top, both weak in early
        // Fizz and Ekko, both mid, both strong in early
        // Leave preferred play types

        // Ally 5 champs
        Champion champion1 = championService.getChampionByName("Fiora");
        champion1.setLane("top");
        Champion champion2 = championService.getChampionByName("Rengar");
        champion2.setLane("jungle");
        Champion champion3 = championService.getChampionByName("Fizz");
        champion3.setLane("mid");
        Champion champion4 = championService.getChampionByName("Vayne");
        champion4.setLane("adc");
        Champion champion5 = championService.getChampionByName("Zilean");
        champion5.setLane("support");

        // Enemy 5 champs
        Champion champion6 = championService.getChampionByName("Dr. Mundo");
        champion6.setLane("top");
        Champion champion7 = championService.getChampionByName("Sejuani");
        champion7.setLane("jungle");
        Champion champion8 = championService.getChampionByName("Ekko");
        champion8.setLane("mid");
        Champion champion9 = championService.getChampionByName("Varus");
        champion9.setLane("adc");
        Champion champion10 = championService.getChampionByName("Braum");
        champion10.setLane("support");

        // Ally team
        AllyChampion allyTop = new AllyChampion(champion1, "aggro");
        AllyChampion allyJungle = new AllyChampion(champion2, "def");
        AllyChampion allyMid = new AllyChampion(champion3, "def");
        AllyChampion allyADC = new AllyChampion(champion4, "aggro");
        AllyChampion allySupport = new AllyChampion(champion5, "aggro");

        // Enemy team
        EnemyChampion enemyTop = new EnemyChampion(champion6);
        EnemyChampion enemyJungle = new EnemyChampion(champion7);
        EnemyChampion enemyMid = new EnemyChampion(champion8);
        EnemyChampion enemyADC = new EnemyChampion(champion9);
        EnemyChampion enemySupport = new EnemyChampion(champion10);

        // Insert ally
        kSession.insert(allyTop);
        kSession.insert(allyJungle);
        kSession.insert(allyMid);
        kSession.insert(allyADC);
        kSession.insert(allySupport);

        // Insert enemy
        kSession.insert(enemyTop);
        kSession.insert(enemyJungle);
        kSession.insert(enemyMid);
        kSession.insert(enemyADC);
        kSession.insert(enemySupport);

        assertEquals("aggro", allyTop.getPlayType());
        assertEquals("def", allyMid.getPlayType());

        kSession.getAgenda().getAgendaGroup("play-type").setFocus();
        int fired = kSession.fireAllRules();
        AdviceStorage adviceStorage = (AdviceStorage) kSession.getGlobal("adviceStorage");

        assertEquals(4, adviceStorage.getAdvices().size());

        assertEquals("aggro", allyTop.getPlayType());
        assertEquals("def", allyMid.getPlayType());

        kSession.dispose();
    }
}
