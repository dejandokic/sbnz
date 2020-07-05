package com.siit.sbnz.sbnztim14;

import com.siit.sbnz.sbnztim14.model.*;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class MidGameTest {

    @Test
    public void attackCompBasic() {

        KieServices ks = KieServices.Factory.get();
        KieContainer kieContainer = ks.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("ksession-rules");

        kieSession.setGlobal("adviceStorage", new AdviceStorage());

        AllyChampion ally = new AllyChampion();
        ally.setLane("top");
        ally.setPlayType("aggro");

        AllyChampion ally2 = new AllyChampion();
        ally2.setLane("mid");
        ally2.setPlayType("def");

        AllyChampion ally3 = new AllyChampion();
        ally3.setLane("jungle");

        AllyChampion ally4 = new AllyChampion();
        ally4.setLane("adc");
        ally4.setPlayType("aggro");

        TeamComposition teamComposition = new TeamComposition();
        teamComposition.setComposition("attack");

        MidGame midGame = new MidGame();
        midGame.setLane("top");
        midGame.setAllyKills(0);
        midGame.setEnemyKills(0);
        midGame.setAllyTowers(0);
        midGame.setEnemyTowers(0);

        kieSession.insert(new GameEvent("top",EventType.ALLY_KILLS));
        kieSession.insert(new GameEvent("top",EventType.ALLY_KILLS));
        kieSession.insert(new GameEvent("top",EventType.ALLY_KILLS));
        kieSession.insert(new GameEvent("top",EventType.ALLY_KILLS));
        kieSession.insert(new GameEvent("top",EventType.ALLY_KILLS));
        kieSession.insert(new GameEvent("top",EventType.ALLY_KILLS));

        kieSession.insert(new GameEvent("top",EventType.ENEMY_KILLS));

        kieSession.insert(new GameEvent("top",EventType.ENEMY_TOWER_DESTROYED));
        kieSession.insert(new GameEvent("top",EventType.ENEMY_TOWER_DESTROYED));


        MidGame midGame2 = new MidGame();
        midGame2.setLane("mid");
        midGame2.setAllyKills(0);
        midGame2.setEnemyKills(0);
        midGame2.setAllyTowers(0);
        midGame2.setEnemyTowers(0);


        kieSession.insert(new GameEvent("mid",EventType.ALLY_KILLS));
        kieSession.insert(new GameEvent("mid",EventType.ALLY_KILLS));

        kieSession.insert(new GameEvent("mid",EventType.ENEMY_KILLS));
        kieSession.insert(new GameEvent("mid",EventType.ENEMY_KILLS));

        kieSession.insert(new GameEvent("mid",EventType.ALLY_TOWER_DESTROYED));

        kieSession.insert(new GameEvent("mid",EventType.ENEMY_TOWER_DESTROYED));
        kieSession.insert(new GameEvent("mid",EventType.ENEMY_TOWER_DESTROYED));

        MidGame midGame3 = new MidGame();
        midGame3.setLane("jungle");
        midGame3.setAllyObjectives(0);
        midGame3.setEnemyObjectives(0);

        kieSession.insert(new GameEvent("jungle",EventType.OBJECT_KILLED));
        kieSession.insert(new GameEvent("jungle",EventType.OBJECT_KILLED));
        kieSession.insert(new GameEvent("jungle",EventType.OBJECT_KILLED));

        MidGame midGame4 = new MidGame();
        midGame4.setLane("adc");
        midGame4.setAllyKills(0);
        midGame4.setEnemyKills(0);
        midGame4.setAllyTowers(0);
        midGame4.setEnemyTowers(0);

        kieSession.insert(new GameEvent("adc",EventType.ALLY_KILLS));
        kieSession.insert(new GameEvent("adc",EventType.ALLY_KILLS));

        kieSession.insert(new GameEvent("adc",EventType.ENEMY_KILLS));

        kieSession.insert(new GameEvent("adc",EventType.ALLY_TOWER_DESTROYED));

        kieSession.insert(new GameEvent("adc",EventType.ENEMY_TOWER_DESTROYED));

        kieSession.insert(ally);
        kieSession.insert(ally2);
        kieSession.insert(ally3);
        kieSession.insert(ally4);
        kieSession.insert(midGame);
        kieSession.insert(midGame2);
        kieSession.insert(midGame3);
        kieSession.insert(midGame4);
        kieSession.insert(teamComposition);

        kieSession.getAgenda().getAgendaGroup("mid-game").setFocus();
        int rules = kieSession.fireAllRules();

        AdviceStorage adv = (AdviceStorage) kieSession.getGlobal("adviceStorage");

        //RULES FOR: TOP(Ally kills), JUNGLE(Ally objectives), MID(Enemy towers)
        assertEquals(7, rules);
        assertEquals(3, adv.getAdvices().size());

        System.out.println(adv.getAdvices());

        for(String str: adv.getAdvices()){
            if(str.contains("[top, mid]")){
                assertEquals("[top, mid] With a kill lead you should take this opportunity to push the enemy laner and take their lane objectives to match what they took.", str);
            }else if(str.contains("[jungle, mid]")){
                assertEquals("[jungle, mid] You should try keeping up the pressure with objectives, keeping your team close around objective spawn times, and making them regret attempting to take it from you.", str);
            }else if(str.contains("[mid, mid]")){
                assertEquals("[mid, mid] Since your towers got pushed, if they stay on the lane you should ask for help as you can't handle them alone, but if they rotate push their lane back or rotate with them.", str);
            }
        }

    }

    @Test
    public void splitCompBasic() {

        KieServices ks = KieServices.Factory.get();
        KieContainer kieContainer = ks.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("ksession-rules");

        kieSession.setGlobal("adviceStorage", new AdviceStorage());

        AllyChampion ally = new AllyChampion();
        ally.setLane("top");
        ally.setSplitPush(true);
        ally.setPlayType("aggro");

        AllyChampion ally2 = new AllyChampion();
        ally2.setLane("mid");
        ally2.setSplitPush(false);
        ally2.setPlayType("def");

        AllyChampion ally3 = new AllyChampion();
        ally3.setLane("jungle");

        AllyChampion ally4 = new AllyChampion();
        ally4.setLane("adc");
        ally4.setSplitPush(false);
        ally4.setPlayType("aggro");

        TeamComposition teamComposition = new TeamComposition();
        teamComposition.setComposition("attack");

        MidGame midGame = new MidGame();
        midGame.setLane("top");
        midGame.setAllyKills(0);
        midGame.setEnemyKills(0);
        midGame.setAllyTowers(0);
        midGame.setEnemyTowers(0);

        kieSession.insert(new GameEvent("top", EventType.ALLY_KILLS));
        kieSession.insert(new GameEvent("top", EventType.ALLY_KILLS));
        kieSession.insert(new GameEvent("top", EventType.ALLY_KILLS));
        kieSession.insert(new GameEvent("top", EventType.ALLY_KILLS));
        kieSession.insert(new GameEvent("top", EventType.ALLY_KILLS));
        kieSession.insert(new GameEvent("top", EventType.ALLY_KILLS));
        kieSession.insert(new GameEvent("top", EventType.ALLY_KILLS));
        kieSession.insert(new GameEvent("top", EventType.ALLY_KILLS));


        kieSession.insert(new GameEvent("top", EventType.ENEMY_KILLS));
        kieSession.insert(new GameEvent("top", EventType.ENEMY_KILLS));
        kieSession.insert(new GameEvent("top", EventType.ENEMY_KILLS));

        kieSession.insert(new GameEvent("top", EventType.ENEMY_TOWER_DESTROYED));
        kieSession.insert(new GameEvent("top", EventType.ENEMY_TOWER_DESTROYED));



        MidGame midGame2 = new MidGame();
        midGame2.setLane("mid");
        midGame2.setAllyKills(0);
        midGame2.setEnemyKills(0);
        midGame2.setAllyTowers(0);
        midGame2.setEnemyTowers(0);


        kieSession.insert(new GameEvent("mid", EventType.ALLY_KILLS));
        kieSession.insert(new GameEvent("mid", EventType.ALLY_KILLS));

        kieSession.insert(new GameEvent("mid", EventType.ENEMY_KILLS));
        kieSession.insert(new GameEvent("mid", EventType.ENEMY_KILLS));

        kieSession.insert(new GameEvent("mid", EventType.ALLY_TOWER_DESTROYED));

        kieSession.insert(new GameEvent("mid", EventType.ENEMY_TOWER_DESTROYED));
        kieSession.insert(new GameEvent("mid", EventType.ENEMY_TOWER_DESTROYED));
        kieSession.insert(new GameEvent("mid", EventType.ENEMY_TOWER_DESTROYED));


        MidGame midGame3 = new MidGame();
        midGame3.setLane("jungle");
        midGame3.setAllyObjectives(0);
        midGame3.setEnemyObjectives(0);


        MidGame midGame4 = new MidGame();
        midGame4.setLane("adc");
        midGame4.setAllyKills(0);
        midGame4.setEnemyKills(0);
        midGame4.setAllyTowers(0);
        midGame4.setEnemyTowers(0);


        kieSession.insert(new GameEvent("adc", EventType.ALLY_KILLS));
        kieSession.insert(new GameEvent("adc", EventType.ALLY_KILLS));
        kieSession.insert(new GameEvent("adc", EventType.ALLY_KILLS));

        kieSession.insert(new GameEvent("adc", EventType.ENEMY_KILLS));
        kieSession.insert(new GameEvent("adc", EventType.ENEMY_KILLS));

        kieSession.insert(new GameEvent("adc", EventType.ALLY_TOWER_DESTROYED));
        kieSession.insert(new GameEvent("adc", EventType.ALLY_TOWER_DESTROYED));

        kieSession.insert(new GameEvent("adc", EventType.ENEMY_TOWER_DESTROYED));


        kieSession.insert(ally);
        kieSession.insert(ally2);
        kieSession.insert(ally3);
        kieSession.insert(ally4);
        kieSession.insert(midGame);
        kieSession.insert(midGame2);
        kieSession.insert(midGame3);
        kieSession.insert(midGame4);
        kieSession.insert(teamComposition);

        kieSession.getAgenda().getAgendaGroup("mid-game").setFocus();
        int rules = kieSession.fireAllRules();

        AdviceStorage adv = (AdviceStorage) kieSession.getGlobal("adviceStorage");

        //RULES FOR: TOP(Splitpush, Ally kills), MID(Enemy towers), BOT(Ally towers)
        assertEquals(7, rules);
        assertEquals(3, adv.getAdvices().size());

        System.out.println(adv.getAdvices());

        for(String str: adv.getAdvices()){
            if(str.contains("[top, mid]")){
                assertEquals("[top, mid] With a kill lead you should take this opportunity to push the enemy laner and take their lane objectives to match what they took.", str);
            }else if(str.contains("[adc, mid]")){
                assertEquals("[adc, mid] With their tower gone, you can safely rotate to be with your team and push things like objectives and towers with your teamfight ability.", str);
            }else if(str.contains("[mid, mid]")){
                assertEquals("[mid, mid] Since your towers got pushed, if they stay on the lane you should ask for help as you can't handle them alone, but if they rotate push their lane back or rotate with them.", str);
            }
        }

    }

}