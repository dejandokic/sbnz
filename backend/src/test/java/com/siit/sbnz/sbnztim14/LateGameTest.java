package com.siit.sbnz.sbnztim14;

import com.siit.sbnz.sbnztim14.model.*;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import static org.junit.Assert.*;

public class LateGameTest {

    @Test
    public void catchCompBasic() {

        KieServices ks = KieServices.Factory.get();
        KieContainer kieContainer = ks.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("ksession-rules");

        kieSession.setGlobal("adviceStorage", new AdviceStorage());

        TeamComposition teamComposition = new TeamComposition();
        teamComposition.setComposition("catch");

        LateGame lateGame = new LateGame();
        lateGame.setTeamKills(true);
        lateGame.setTeamObjectives(false);
        lateGame.setTeamTowers(false);

        kieSession.insert(new GameEvent("top",EventType.ALLY_KILLS));
        kieSession.insert(new GameEvent("top",EventType.ENEMY_TOWER_DESTROYED));
        kieSession.insert(new GameEvent("top",EventType.ENEMY_OBJECT_KILLED));

        kieSession.insert(teamComposition);
        kieSession.insert(lateGame);
        kieSession.getAgenda().getAgendaGroup("late-game").setFocus();
        kieSession.fireAllRules();

        AdviceStorage adv = (AdviceStorage) kieSession.getGlobal("adviceStorage");

        //Ally kills, Enemy towers objectives
        assertEquals("[catch, late] Even with a kill lead your objective and tower control is rather weak, which will end up losing you the game. Use the strength you have, which is your map control, and prevent their attempts to take objectives/towers by catching them off guard and killing them off separately.", adv.getAdvices().get(0));



    }


    @Test
    public void protectCompBasic() {

        KieServices ks = KieServices.Factory.get();
        KieContainer kieContainer = ks.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("ksession-rules");

        kieSession.setGlobal("adviceStorage", new AdviceStorage());

        TeamComposition teamComposition = new TeamComposition();
        teamComposition.setComposition("protect");

        LateGame lateGame = new LateGame();
        lateGame.setTeamKills(true);
        lateGame.setTeamObjectives(true);
        lateGame.setTeamTowers(false);


        kieSession.insert(new GameEvent("top",EventType.ALLY_KILLS));
        kieSession.insert(new GameEvent("top",EventType.ENEMY_TOWER_DESTROYED));
        kieSession.insert(new GameEvent("top",EventType.OBJECT_KILLED));

        kieSession.insert(teamComposition);
        kieSession.insert(lateGame);
        kieSession.getAgenda().getAgendaGroup("late-game").setFocus();
        kieSession.fireAllRules();

        AdviceStorage adv = (AdviceStorage) kieSession.getGlobal("adviceStorage");
        System.out.println(adv.getAdvices().get(0));

        //Ally kills objectives, Enemy towers
        assertEquals("[protect, late] With poor tower control you will eventually lose the game, no matter how many kills your carry racks up or how many objectives you take. In a protect comp your push ability is not the best, but you can try to take advantage of your kill lead and punish their pushes.", adv.getAdvices().get(0));



    }


    @Test
    public void siegeCompBasic() {

        KieServices ks = KieServices.Factory.get();
        KieContainer kieContainer = ks.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("ksession-rules");

        kieSession.setGlobal("adviceStorage", new AdviceStorage());

        TeamComposition teamComposition = new TeamComposition();
        teamComposition.setComposition("siege");

        LateGame lateGame = new LateGame();
        lateGame.setTeamKills(false);
        lateGame.setTeamObjectives(false);
        lateGame.setTeamTowers(true);

        kieSession.insert(new GameEvent("top",EventType.ENEMY_KILLS));
        kieSession.insert(new GameEvent("top",EventType.ALLY_TOWER_DESTROYED));
        kieSession.insert(new GameEvent("top",EventType.ENEMY_OBJECT_KILLED));

        kieSession.insert(teamComposition);
        kieSession.insert(lateGame);
        kieSession.getAgenda().getAgendaGroup("late-game").setFocus();
        kieSession.fireAllRules();

        AdviceStorage adv = (AdviceStorage) kieSession.getGlobal("adviceStorage");

        System.out.println(adv.getAdvices().get(0));
        //Ally towers, Enemy kills objectives
        assertEquals("[siege, late] With a better tower control you are in the lead, even if they have more kills and objectives, but they can turn it on you fairly easily. With your team comp, it's not hard to fight in lanes since you have a lot of waveclear, but if you lacks kills you will never be able to defend yourself properly.", adv.getAdvices().get(0));



    }


}

