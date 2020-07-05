package com.siit.sbnz.sbnztim14;

import com.siit.sbnz.sbnztim14.model.AdviceStorage;
import com.siit.sbnz.sbnztim14.model.AllyChampion;
import com.siit.sbnz.sbnztim14.model.Champion;
import com.siit.sbnz.sbnztim14.model.EarlyGamePartTwo;
import com.siit.sbnz.sbnztim14.model.EnemyChampion;
import com.siit.sbnz.sbnztim14.model.TeamComposition;
import com.siit.sbnz.sbnztim14.service.ChampionService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import static org.junit.Assert.assertEquals;

public class ChampClassWithTeamCompositionTest {

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

        // Choose 10 champs
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

        kSession.insert(new TeamComposition("siege"));

        kSession.getAgenda().getAgendaGroup("teamcomp-role").setFocus();
        int fired = kSession.fireAllRules();

        AdviceStorage adviceStorage = (AdviceStorage) kSession.getGlobal("adviceStorage");
        assertEquals(5, fired);
        assertEquals(5, adviceStorage.getAdvices().size());

        for(String str: adviceStorage.getAdvices()){
            if (str.contains("[top, mid-late]")){
                assertEquals("[top, mid-late] As an artillery in a siege comp your one of the main parts of the team. You need to use your poke to get enemies to back away from the objectives, using waveclear to shove lanes with ease. You have hard CC in case enemies get close, so use it to keep a safe distance.", str);
            } else if(str.contains("[jungle, mid-late]")){
                assertEquals("[jungle, mid-late] As a vanguard in a siege comp your job is to be a meatshiled for your team, while they poke and siege towers. You have a lot of hard CC to stop the enemy team engages and sustain to block incoming damage for the team.", str);
            } else if(str.contains("[mid, mid-late]")){
                assertEquals("[mid, mid-late] As an artillery in a siege comp your one of the main parts of the team. You need to use your poke to get enemies to back away from the objectives, using waveclear to shove lanes with ease. You have hard CC in case enemies get close, so use it to keep a safe distance.", str);
            } else if(str.contains("[adc, mid-late]")){
                assertEquals("[adc, mid-late] As a marksman in a siege comp your main job is to prevent pushes and tear down opponents from far away. Using your poke you can do that with ease, using your waveclear to prevent pushes and siege towers.", str);
            } else {
                assertEquals("[support, mid-late] As an enchanter in a siege comp your job is to help your team avoid unnecessary fights. Use your CC to control enemy engages, combining your disengages and utility to give your team enough time to escape fights.", str);
            }
        }

        kSession.dispose();
    }
}
