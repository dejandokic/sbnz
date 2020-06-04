package com.siit.sbnz.sbnztim14;

import com.siit.sbnz.sbnztim14.model.*;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class LateGameTest {

    @Test
    public void splitCompBasic() {

        KieServices ks = KieServices.Factory.get();
        KieContainer kieContainer = ks.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("lateGameSession");

        kieSession.setGlobal("adviceStorage", new AdviceStorage());

        TeamComposition teamComposition = new TeamComposition();
        teamComposition.setComposition("catch");

        LateGame lateGame = new LateGame();
        lateGame.setTeamKills(true);
        lateGame.setTeamObjectives(false);
        lateGame.setTeamTowers(false);

        kieSession.insert(teamComposition);
        kieSession.insert(lateGame);
        kieSession.getAgenda().getAgendaGroup("late-game").setFocus();
        kieSession.fireAllRules();

        AdviceStorage adv = (AdviceStorage) kieSession.getGlobal("adviceStorage");
        System.out.println(adv.getAdvices());



    }
}

