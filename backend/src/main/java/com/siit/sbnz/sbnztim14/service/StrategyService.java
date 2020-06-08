package com.siit.sbnz.sbnztim14.service;

import com.siit.sbnz.sbnztim14.dto.FirstInteraction;
import com.siit.sbnz.sbnztim14.model.AdviceStorage;
import com.siit.sbnz.sbnztim14.model.AllyChampion;
import com.siit.sbnz.sbnztim14.model.Champion;
import com.siit.sbnz.sbnztim14.model.EnemyChampion;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StrategyService {

    private final KieContainer kieContainer;
    private KieSession kSession = null;
    private ChampionService championService = new ChampionService();

    @Autowired
    public StrategyService(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    public AdviceStorage getStrategyEarlyGamePartOne(FirstInteraction firstInteraction) {
        Champion champion1 = championService.getChampionByName(firstInteraction.getAllyTop());
        champion1.setLane("top");
        Champion champion2 = championService.getChampionByName(firstInteraction.getAllyJungle());
        champion2.setLane("jungle");
        Champion champion3 = championService.getChampionByName(firstInteraction.getAllyMid());
        champion3.setLane("mid");
        Champion champion4 = championService.getChampionByName(firstInteraction.getAllyADC());
        champion4.setLane("adc");
        Champion champion5 = championService.getChampionByName(firstInteraction.getAllySupport());
        champion5.setLane("support");

        Champion champion6 = championService.getChampionByName(firstInteraction.getEnemyTop());
        champion6.setLane("top");
        Champion champion7 = championService.getChampionByName(firstInteraction.getEnemyJungle());
        champion7.setLane("jungle");
        Champion champion8 = championService.getChampionByName(firstInteraction.getEnemyMid());
        champion8.setLane("mid");
        Champion champion9 = championService.getChampionByName(firstInteraction.getEnemyADC());
        champion9.setLane("adc");
        Champion champion10 = championService.getChampionByName(firstInteraction.getEnemySupport());
        champion10.setLane("support");

        // Ally team
        AllyChampion ally1 = new AllyChampion(champion1, firstInteraction.getAllyTopPrefer());
        AllyChampion ally2 = new AllyChampion(champion2, firstInteraction.getAllyJunglePrefer());
        AllyChampion ally3 = new AllyChampion(champion3, firstInteraction.getAllyMidPrefer());
        AllyChampion ally4 = new AllyChampion(champion4, firstInteraction.getAllyADCPrefer());
        AllyChampion ally5 = new AllyChampion(champion5, firstInteraction.getAllySupportPrefer());

        // Enemy team
        EnemyChampion enemy1 = new EnemyChampion(champion6);
        EnemyChampion enemy2 = new EnemyChampion(champion7);
        EnemyChampion enemy3 = new EnemyChampion(champion8);
        EnemyChampion enemy4 = new EnemyChampion(champion9);
        EnemyChampion enemy5 = new EnemyChampion(champion10);

        kSession = kieContainer.newKieSession("ksession-rules");
        kSession.setGlobal("adviceStorage", new AdviceStorage());

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

        kSession.getAgenda().getAgendaGroup("early-game-part-1").setFocus();
        kSession.fireAllRules();
        AdviceStorage adviceStorage = (AdviceStorage) kSession.getGlobal("adviceStorage");
        kSession.setGlobal("adviceStorage", new AdviceStorage());

        return  adviceStorage;
    }

}
