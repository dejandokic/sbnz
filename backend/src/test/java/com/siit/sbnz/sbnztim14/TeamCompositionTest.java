package com.siit.sbnz.sbnztim14;

import com.siit.sbnz.sbnztim14.model.*;
import com.siit.sbnz.sbnztim14.service.ChampionService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class TeamCompositionTest {

    static ChampionService championService;
    static KieContainer kContainer;
    KieSession kSession;

    @BeforeClass
    public static void beforeClass() {
        championService = new ChampionService();
        KieServices kieServices = KieServices.Factory.get();
        kContainer = kieServices.getKieClasspathContainer();
    }

    @Before
    public void beforeEach() {
        kSession = kContainer.newKieSession("ksession-rules");
        kSession.getAgenda().getAgendaGroup("composition-probability").setFocus();
    }

    @Test
    public void attackCompTest() {
        Champion champion1 = championService.getChampionByName("Kennen");
        champion1.setLane("top");
        Champion champion2 = championService.getChampionByName("Maokai");
        champion2.setLane("top");
        Champion champion3 = championService.getChampionByName("Wukong");
        champion3.setLane("jungle");
        Champion champion4 = championService.getChampionByName("Sejuani");
        champion4.setLane("jungle");
        Champion champion5 = championService.getChampionByName("Syndra");
        champion5.setLane("mid");
        Champion champion6 = championService.getChampionByName("Ziggs");
        champion6.setLane("mid");
        Champion champion7 = championService.getChampionByName("Ashe");
        champion7.setLane("adc");
        Champion champion8 = championService.getChampionByName("Ezreal");
        champion8.setLane("adc");
        Champion champion9 = championService.getChampionByName("Braum");
        champion9.setLane("support");
        Champion champion10 = championService.getChampionByName("Karma");
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

        TeamCompositionProbability tcpAlly = new TeamCompositionProbability("ally");
        TeamCompositionProbability tcpEnemy = new TeamCompositionProbability("enemy");
        TeamCompositionProbability tcpFinal = new TeamCompositionProbability("final");

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

        kSession.insert(tcpAlly);
        kSession.insert(tcpEnemy);
        kSession.insert(tcpFinal);
        kSession.setGlobal("allyTeamComposition", new TeamComposition());

        kSession.fireAllRules();
        TeamComposition comp = (TeamComposition)kSession.getGlobal("allyTeamComposition");

        assertEquals("attack", comp.getComposition());

        kSession.dispose();
    }

    @Test
    public void catchCompTest() {
        Champion champion1 = championService.getChampionByName("Renekton");
        champion1.setLane("top");
        Champion champion2 = championService.getChampionByName("Riven");
        champion2.setLane("top");
        Champion champion3 = championService.getChampionByName("Lee Sin");
        champion3.setLane("jungle");
        Champion champion4 = championService.getChampionByName("Sejuani");
        champion4.setLane("jungle");
        Champion champion5 = championService.getChampionByName("Fizz");
        champion5.setLane("mid");
        Champion champion6 = championService.getChampionByName("Anivia");
        champion6.setLane("mid");
        Champion champion7 = championService.getChampionByName("Draven");
        champion7.setLane("adc");
        Champion champion8 = championService.getChampionByName("Ezreal");
        champion8.setLane("adc");
        Champion champion9 = championService.getChampionByName("Thresh");
        champion9.setLane("support");
        Champion champion10 = championService.getChampionByName("Karma");
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

        TeamCompositionProbability tcpAlly = new TeamCompositionProbability("ally");
        TeamCompositionProbability tcpEnemy = new TeamCompositionProbability("enemy");
        TeamCompositionProbability tcpFinal = new TeamCompositionProbability("final");

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

        kSession.insert(tcpAlly);
        kSession.insert(tcpEnemy);
        kSession.insert(tcpFinal);
        kSession.setGlobal("allyTeamComposition", new TeamComposition());

        kSession.fireAllRules();
        TeamComposition comp = (TeamComposition)kSession.getGlobal("allyTeamComposition");

        assertEquals("catch", comp.getComposition());

        kSession.dispose();
    }

    @Test
    public void protectCompTest() {
        Champion champion1 = championService.getChampionByName("Malphite");
        champion1.setLane("top");
        Champion champion2 = championService.getChampionByName("Riven");
        champion2.setLane("top");
        Champion champion3 = championService.getChampionByName("Nunu");
        champion3.setLane("jungle");
        Champion champion4 = championService.getChampionByName("Sejuani");
        champion4.setLane("jungle");
        Champion champion5 = championService.getChampionByName("Cassiopeia");
        champion5.setLane("mid");
        Champion champion6 = championService.getChampionByName("Anivia");
        champion6.setLane("mid");
        Champion champion7 = championService.getChampionByName("Vayne");
        champion7.setLane("adc");
        Champion champion8 = championService.getChampionByName("Ezreal");
        champion8.setLane("adc");
        Champion champion9 = championService.getChampionByName("Janna");
        champion9.setLane("support");
        Champion champion10 = championService.getChampionByName("Karma");
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

        TeamCompositionProbability tcpAlly = new TeamCompositionProbability("ally");
        TeamCompositionProbability tcpEnemy = new TeamCompositionProbability("enemy");
        TeamCompositionProbability tcpFinal = new TeamCompositionProbability("final");

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

        kSession.insert(tcpAlly);
        kSession.insert(tcpEnemy);
        kSession.insert(tcpFinal);
        kSession.setGlobal("allyTeamComposition", new TeamComposition());

        kSession.fireAllRules();
        TeamComposition comp = (TeamComposition)kSession.getGlobal("allyTeamComposition");

        assertEquals("protect", comp.getComposition());

        kSession.dispose();
    }

    @Test
    public void siegeCompTest() {
        Champion champion1 = championService.getChampionByName("Jayce");
        champion1.setLane("top");
        Champion champion2 = championService.getChampionByName("Akali");
        champion2.setLane("top");
        Champion champion3 = championService.getChampionByName("Sejuani");
        champion3.setLane("jungle");
        Champion champion4 = championService.getChampionByName("Lee Sin");
        champion4.setLane("jungle");
        Champion champion5 = championService.getChampionByName("Ziggs");
        champion5.setLane("mid");
        Champion champion6 = championService.getChampionByName("Fizz");
        champion6.setLane("mid");
        Champion champion7 = championService.getChampionByName("Ezreal");
        champion7.setLane("adc");
        Champion champion8 = championService.getChampionByName("Draven");
        champion8.setLane("adc");
        Champion champion9 = championService.getChampionByName("Janna");
        champion9.setLane("support");
        Champion champion10 = championService.getChampionByName("Thresh");
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

        TeamCompositionProbability tcpAlly = new TeamCompositionProbability("ally");
        TeamCompositionProbability tcpEnemy = new TeamCompositionProbability("enemy");
        TeamCompositionProbability tcpFinal = new TeamCompositionProbability("final");

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

        kSession.insert(tcpAlly);
        kSession.insert(tcpEnemy);
        kSession.insert(tcpFinal);
        kSession.setGlobal("allyTeamComposition", new TeamComposition());

        kSession.fireAllRules();
        TeamComposition comp = (TeamComposition)kSession.getGlobal("allyTeamComposition");

        assertEquals("siege", comp.getComposition());

        kSession.dispose();
    }

    @Test
    public void splitCompTest() {
        Champion champion1 = championService.getChampionByName("Tryndamere");
        champion1.setLane("top");
        Champion champion2 = championService.getChampionByName("Jayce");
        champion2.setLane("top");
        Champion champion3 = championService.getChampionByName("Jax");
        champion3.setLane("jungle");
        Champion champion4 = championService.getChampionByName("Sejuani");
        champion4.setLane("jungle");
        Champion champion5 = championService.getChampionByName("Ziggs");
        champion5.setLane("mid");
        Champion champion6 = championService.getChampionByName("Xerath");
        champion6.setLane("mid");
        Champion champion7 = championService.getChampionByName("Corki");
        champion7.setLane("adc");
        Champion champion8 = championService.getChampionByName("Ezreal");
        champion8.setLane("adc");
        Champion champion9 = championService.getChampionByName("Thresh");
        champion9.setLane("support");
        Champion champion10 = championService.getChampionByName("Janna");
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

        TeamCompositionProbability tcpAlly = new TeamCompositionProbability("ally");
        TeamCompositionProbability tcpEnemy = new TeamCompositionProbability("enemy");
        TeamCompositionProbability tcpFinal = new TeamCompositionProbability("final");

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

        kSession.insert(tcpAlly);
        kSession.insert(tcpEnemy);
        kSession.insert(tcpFinal);
        kSession.setGlobal("allyTeamComposition", new TeamComposition());

        kSession.fireAllRules();
        TeamComposition comp = (TeamComposition)kSession.getGlobal("allyTeamComposition");

        assertEquals("split", comp.getComposition());

        kSession.dispose();
    }

}
