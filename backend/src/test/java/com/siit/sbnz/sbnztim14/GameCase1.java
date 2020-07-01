package com.siit.sbnz.sbnztim14;

import com.siit.sbnz.sbnztim14.model.AdviceStorage;
import com.siit.sbnz.sbnztim14.model.AllyChampion;
import com.siit.sbnz.sbnztim14.model.Champion;
import com.siit.sbnz.sbnztim14.model.EnemyChampion;
import com.siit.sbnz.sbnztim14.model.EventType;
import com.siit.sbnz.sbnztim14.model.GameEvent;
import com.siit.sbnz.sbnztim14.model.JunglerConclusion;
import com.siit.sbnz.sbnztim14.service.ChampionService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;

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

    private void eventOccurred(KieSession session, GameEvent event) {
        session.getAgenda().getAgendaGroup("after-event").setFocus();
        session.insert(event);
        int fired = session.fireAllRules();
        if (event.getEventType() == EventType.ALLY_KILLS || event.getEventType() == EventType.ENEMY_KILLS) {
            assertEquals(1, fired);
        } else {
            assertEquals(5, fired);
        }
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


        // Rewards after events (agenda = after-event)

        // Assert initial gold state
        assertEquals(0, ally1.getGolds());
        assertEquals(300, ally1.getGoldsForKill());
        assertEquals(0, enemy1.getGolds());
        assertEquals(300, enemy1.getGoldsForKill());

        eventOccurred(kSession, new GameEvent("top", EventType.ENEMY_KILLS));
        assertEquals(0, ally1.getGolds());
        assertEquals(300, ally1.getGoldsForKill());
        assertEquals(300, enemy1.getGolds());
        assertEquals(400, enemy1.getGoldsForKill());

        eventOccurred(kSession, new GameEvent("top", EventType.ENEMY_KILLS));
        assertEquals(0, ally1.getGolds());
        assertEquals(300, ally1.getGoldsForKill());
        assertEquals(600, enemy1.getGolds());
        assertEquals(500, enemy1.getGoldsForKill());

        eventOccurred(kSession, new GameEvent("mid", EventType.ENEMY_TOWER_DESTROYED));
        assertEquals(200, ally1.getGolds());
        assertEquals(300, ally1.getGoldsForKill());
        assertEquals(200, ally2.getGolds());
        assertEquals(300, ally2.getGoldsForKill());
        assertEquals(200, ally3.getGolds());
        assertEquals(300, ally3.getGoldsForKill());
        assertEquals(200, ally4.getGolds());
        assertEquals(300, ally4.getGoldsForKill());
        assertEquals(200, ally5.getGolds());
        assertEquals(300, ally5.getGoldsForKill());

        eventOccurred(kSession, new GameEvent("top", EventType.ALLY_KILLS));
        assertEquals(700, ally1.getGolds());
        assertEquals(400, ally1.getGoldsForKill());
        assertEquals(600, enemy1.getGolds());
        assertEquals(300, enemy1.getGoldsForKill());

        eventOccurred(kSession, new GameEvent("mid", EventType.ALLY_KILLS));
        assertEquals(500, ally3.getGolds());
        assertEquals(400, ally3.getGoldsForKill());
        assertEquals(0, enemy3.getGolds());
        assertEquals(300, enemy3.getGoldsForKill());

        eventOccurred(kSession, new GameEvent("mid", EventType.ALLY_TOWER_DESTROYED));
        assertEquals(800, enemy1.getGolds());
        assertEquals(300, enemy1.getGoldsForKill());
        assertEquals(200, enemy2.getGolds());
        assertEquals(300, enemy2.getGoldsForKill());
        assertEquals(200, enemy3.getGolds());
        assertEquals(300, enemy3.getGoldsForKill());
        assertEquals(200, enemy4.getGolds());
        assertEquals(300, enemy4.getGoldsForKill());
        assertEquals(200, enemy5.getGolds());
        assertEquals(300, enemy5.getGoldsForKill());

        eventOccurred(kSession, new GameEvent("jungle", EventType.OBJECT_KILLED));
        assertEquals(850, ally1.getGolds());
        assertEquals(400, ally1.getGoldsForKill());
        assertEquals(350, ally2.getGolds());
        assertEquals(300, ally2.getGoldsForKill());
        assertEquals(650, ally3.getGolds());
        assertEquals(400, ally3.getGoldsForKill());
        assertEquals(350, ally4.getGolds());
        assertEquals(300, ally4.getGoldsForKill());
        assertEquals(350, ally5.getGolds());
        assertEquals(300, ally5.getGoldsForKill());

        eventOccurred(kSession, new GameEvent("adc", EventType.ALLY_KILLS));
        assertEquals(650, ally4.getGolds());
        assertEquals(400, ally4.getGoldsForKill());
        assertEquals(200, enemy4.getGolds());
        assertEquals(300, enemy4.getGoldsForKill());


        // Where should jungler gank rules

        // Older
        eventOccurred(kSession, new GameEvent("top", EventType.ALLY_KILLS));
        eventOccurred(kSession, new GameEvent("top", EventType.ENEMY_KILLS));
        eventOccurred(kSession, new GameEvent("top", EventType.ENEMY_KILLS));
        eventOccurred(kSession, new GameEvent("mid", EventType.ENEMY_KILLS));
        eventOccurred(kSession, new GameEvent("mid", EventType.ALLY_KILLS));
        eventOccurred(kSession, new GameEvent("adc", EventType.ALLY_KILLS));

        // Newer
        eventOccurred(kSession, new GameEvent("top", EventType.ALLY_KILLS));
        eventOccurred(kSession, new GameEvent("top", EventType.ALLY_KILLS));
        eventOccurred(kSession, new GameEvent("top", EventType.ENEMY_KILLS));
        eventOccurred(kSession, new GameEvent("mid", EventType.ENEMY_KILLS));
        eventOccurred(kSession, new GameEvent("mid",  EventType.ENEMY_KILLS));
        eventOccurred(kSession, new GameEvent("adc", EventType.ALLY_KILLS));
        eventOccurred(kSession, new GameEvent("support", EventType.ALLY_KILLS));
        eventOccurred(kSession, new GameEvent("adc", EventType.ALLY_KILLS));

        JunglerConclusion junglerConclusion = new JunglerConclusion("");
        FactHandle jungleConclusionFH = kSession.insert(junglerConclusion);

        int fired = kSession.fireAllRules();
        assertEquals(1, fired);
        assertEquals("mid", junglerConclusion.getNextGank());
        kSession.delete(jungleConclusionFH);

        eventOccurred(kSession, new GameEvent("mid", EventType.ALLY_KILLS));
        eventOccurred(kSession, new GameEvent("mid", EventType.ALLY_KILLS));
        eventOccurred(kSession, new GameEvent("mid", EventType.ALLY_KILLS));

        JunglerConclusion junglerConclusion2 = new JunglerConclusion("");
        FactHandle jungleConclusionFH2 = kSession.insert(junglerConclusion2);
        fired = kSession.fireAllRules();
        assertEquals(1, fired);
        assertEquals("top", junglerConclusion2.getNextGank());
        kSession.delete(jungleConclusionFH2);

        kSession.dispose();
    }
}
