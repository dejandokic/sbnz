package com.siit.sbnz.sbnztim14;

import com.siit.sbnz.sbnztim14.model.AdviceStorage;
import com.siit.sbnz.sbnztim14.model.AllyChampion;
import com.siit.sbnz.sbnztim14.model.Champion;
import com.siit.sbnz.sbnztim14.model.EarlyGamePartTwo;
import com.siit.sbnz.sbnztim14.model.EnemyChampion;
import com.siit.sbnz.sbnztim14.model.EventType;
import com.siit.sbnz.sbnztim14.model.GameEvent;
import com.siit.sbnz.sbnztim14.model.JungleEarlyPlayType;
import com.siit.sbnz.sbnztim14.service.ChampionService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class GameCase1 {

    static ChampionService championService = new ChampionService();
    static KieContainer kContainer;

    @BeforeClass
    public static void beforeClass() {
        KieServices kieServices = KieServices.Factory.get();
        kContainer = kieServices.getKieClasspathContainer();
    }

    @Test
    public void changePlayTypeTest() {
        KieSession kSession = kContainer.newKieSession("ksession-rules");
        kSession.setGlobal("adviceStorage", new AdviceStorage());

        // Good events
        ArrayList<EventType> goodEvents = new ArrayList<>();
        goodEvents.add(EventType.ALLY_KILLS);
        goodEvents.add(EventType.ENEMY_TOWER_DESTROYED);

        // Bad events
        ArrayList<EventType> badEvents = new ArrayList<>();
        badEvents.add(EventType.ENEMY_KILLS);
        badEvents.add(EventType.ALLY_TOWER_DESTROYED);

        kSession.setGlobal("goodEvents", goodEvents);
        kSession.setGlobal("badEvents", badEvents);

        // Choose 10 champs
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

        kSession.insert(new JungleEarlyPlayType());

        // Older
        kSession.insert(new GameEvent("top", ally1, enemy1, EventType.ALLY_KILLS));
        kSession.insert(new GameEvent("top", ally1, enemy1, EventType.ENEMY_KILLS));
        kSession.insert(new GameEvent("top", ally1, enemy1, EventType.ENEMY_KILLS));
        kSession.insert(new GameEvent("mid", ally3, enemy3, EventType.ENEMY_KILLS));
        kSession.insert(new GameEvent("mid", ally3, enemy3, EventType.ALLY_KILLS));
        kSession.insert(new GameEvent("bottom", ally4, enemy4, EventType.ALLY_KILLS));

        // Newer
        kSession.insert(new GameEvent("top", ally1, enemy1, EventType.ALLY_KILLS));
        kSession.insert(new GameEvent("top", ally1, enemy1, EventType.ENEMY_KILLS));
        kSession.insert(new GameEvent("top", ally1, enemy1, EventType.ENEMY_KILLS));
        kSession.insert(new GameEvent("mid", ally3, enemy3, EventType.ENEMY_KILLS));
        kSession.insert(new GameEvent("mid", ally3, enemy3, EventType.ENEMY_KILLS));
        kSession.insert(new GameEvent("bottom", ally4, enemy4, EventType.ALLY_KILLS));
        kSession.insert(new GameEvent("bottom", ally4, enemy4, EventType.ALLY_KILLS));
        kSession.insert(new GameEvent("bottom", ally4, enemy4, EventType.ALLY_KILLS));

        kSession.getAgenda().getAgendaGroup("jungler-where-to-gank-next").setFocus();
        int fired = kSession.fireAllRules();
        AdviceStorage adviceStorage = (AdviceStorage) kSession.getGlobal("adviceStorage");

        System.out.println(adviceStorage.getAdvices().get(0));
        assertEquals(1, fired);

        kSession.dispose();
    }
}
