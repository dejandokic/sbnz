package com.siit.sbnz.sbnztim14;

import com.siit.sbnz.sbnztim14.model.*;
import com.siit.sbnz.sbnztim14.service.ChampionService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import static org.junit.Assert.assertEquals;

public class TeamcompTest {

    static ChampionService championService = new ChampionService();
    static KieContainer kContainer;

    @BeforeClass
    public static void beforeClass() {
        KieServices kieServices = KieServices.Factory.get();
        kContainer = kieServices.getKieClasspathContainer();
    }

    @Test
    public void splitCompBasic() {

        KieSession kSession = kContainer.newKieSession("ksession-rules");
        kSession.setGlobal("adviceStorage", new AdviceStorage());
        kSession.setGlobal("allyTeamComposition", new TeamComposition());


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

        TeamCompositionProbability allyTeam = new TeamCompositionProbability("ally");

        TeamCompositionProbability enemyTeam = new TeamCompositionProbability("enemy");

        TeamCompositionProbability finalTeam = new TeamCompositionProbability("final");



        kSession.getAgenda().getAgendaGroup("composition-probability").setFocus();
        kSession.fireAllRules();

        AdviceStorage adviceStorage = (AdviceStorage) kSession.getGlobal("adviceStorage");

        System.out.println(adviceStorage.getAdvices());

        assertEquals("TeamComposition [composition=attack]", kSession.getGlobal("allyTeamComposition").toString());

        kSession.dispose();


    }

}

