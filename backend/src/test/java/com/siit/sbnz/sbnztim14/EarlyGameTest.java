package com.siit.sbnz.sbnztim14;

import com.siit.sbnz.sbnztim14.model.*;
import com.siit.sbnz.sbnztim14.service.ChampionService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import static org.junit.Assert.assertEquals;

public class EarlyGameTest {

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

        // Darius - juggernaut (aggro)
        // Nasus - juggernaut

        // Annie - burst (def)
        // Ziggs - artillery

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
        Champion champion6 = championService.getChampionByName("Ziggs");
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


        kSession.getAgenda().getAgendaGroup("early-game-part-1").setFocus();
        int fired = kSession.fireAllRules();
        AdviceStorage adviceStorage = (AdviceStorage) kSession.getGlobal("adviceStorage");

        assertEquals(5, fired);
        assertEquals(5, adviceStorage.getAdvices().size());

        for(String str: adviceStorage.getAdvices()){
            if(str.contains("[top, early]")){
                assertEquals("[top, early] Against another juggernaut, with your highly potent engage and hard crowd control you can lock them down and deal devastating amounts of damage. Be careful as they have high sustain, and killing them will often prove difficult in a 1v1 situation.", str);
            }else if(str.contains("[jungle, early]")){
                assertEquals("[jungle, early] As an assassin your mobility and sneak are your strongest weapons, so focus on ganking as much as possible. You can put a lot of pressure on the enemy with your mobility, but since you lack the followup CC try to attack them when they don't have any escape tools.", str);
            }else if(str.contains("[mid, early]")){
                assertEquals("[mid, early] Against an artillery, be careful against their superior range and poke possibilities. They don't have a lot of mobility, and are fairly weak once you get in close range, so use your CC and range to punish their misspositioning and disengage from prolonged trades. Do not be afraid to trade, since you have burst potential and with your waveclear you should be safe to farm from your side of the lane.", str);
            }else if(str.contains("[adc, early]")){
                assertEquals("[adc, early] Against another marksman, you shouldn’t have problems as long as you stay at a safe distance. You can secure trades with your long range poke and control the lane to punish him with waveclear. Marksman are mobile long range damage dealers, that can punish you over a sustainable amount of time, so try to punish their misspositoning.", str);
            }else {
                assertEquals("[support, early] Against a warden, you need to find a way to penetrate their defense. They lack the mobility and damage, but can feel overwhelmingly tough to face because of their sustain. You have utility, so you should be safe in distant trades and saving your CC for ganks will definitely either keep you safe or lead to a kill from your jungler. Try to use your disengage when they get close, but be careful to not waste it.", str);
            }
        }
        kSession.dispose();
    }
}

