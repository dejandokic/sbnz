package com.siit.sbnz.sbnztim14;

import com.siit.sbnz.sbnztim14.model.AdviceStorage;
import com.siit.sbnz.sbnztim14.model.AllyChampion;
import com.siit.sbnz.sbnztim14.model.MidGame;
import com.siit.sbnz.sbnztim14.model.TeamComposition;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MidGameTest {

    @Test
    public void splitCompBasic() {

        KieServices ks = KieServices.Factory.get();
        KieContainer kieContainer = ks.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("ksession-rules");

        kieSession.setGlobal("adviceStorage", new AdviceStorage());

        AllyChampion ally = new AllyChampion();
        ally.setLane("top");
        ally.setSplitPush(false);
        ally.setPlayType("agro");


        AllyChampion ally2 = new AllyChampion();
        ally2.setLane("mid");
        ally2.setSplitPush(true);
        ally2.setPlayType("def");



        AllyChampion ally3 = new AllyChampion();
        ally3.setLane("jungle");


        AllyChampion ally4 = new AllyChampion();
        ally4.setLane("support");
        ally4.setSplitPush(false);
        ally4.setPlayType("agro");


        AllyChampion ally5 = new AllyChampion();
        ally5.setLane("adc");
        ally5.setSplitPush(false);
        ally5.setPlayType("agro");


        TeamComposition teamComposition = new TeamComposition();
        teamComposition.setComposition("attack");

        MidGame midGame = new MidGame();
        midGame.setLane("top");
        midGame.setAllyKills(10);
        midGame.setEnemyKills(4);


        MidGame midGame2 = new MidGame();
        midGame2.setLane("mid");
        midGame2.setAllyKills(4);
        midGame2.setEnemyKills(4);
        midGame2.setAllyTowers(1);
        midGame2.setEnemyTowers(3);


        MidGame midGame3 = new MidGame();
        midGame3.setLane("jungle");
        midGame3.setAllyObjectives(3);
        midGame3.setEnemyObjectives(0);


        MidGame midGame4 = new MidGame();
        midGame4.setLane("support");
        midGame4.setAllyKills(0);
        midGame4.setEnemyKills(0);
        midGame4.setAllyTowers(1);
        midGame4.setEnemyTowers(1);



        MidGame midGame5 = new MidGame();
        midGame5.setLane("adc");
        midGame4.setAllyKills(3);
        midGame4.setEnemyKills(2);
        midGame4.setAllyTowers(1);
        midGame4.setEnemyTowers(1);


        kieSession.insert(ally);
        kieSession.insert(ally2);
        kieSession.insert(ally3);
        kieSession.insert(ally4);
        kieSession.insert(ally5);
        kieSession.insert(midGame);
        kieSession.insert(midGame2);
        kieSession.insert(midGame3);
        kieSession.insert(midGame4);
        kieSession.insert(midGame5);
        kieSession.insert(teamComposition);
        kieSession.getAgenda().getAgendaGroup("mid-game").setFocus();
        kieSession.fireAllRules();

        AdviceStorage adv = (AdviceStorage) kieSession.getGlobal("adviceStorage");
        System.out.println(adv.getAdvices().size());



    }
}

