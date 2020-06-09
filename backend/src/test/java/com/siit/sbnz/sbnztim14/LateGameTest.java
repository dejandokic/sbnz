package com.siit.sbnz.sbnztim14;

import com.siit.sbnz.sbnztim14.model.*;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import static org.junit.Assert.*;

public class LateGameTest {

    @Test
    public void splitCompBasic() {

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

        kieSession.insert(teamComposition);
        kieSession.insert(lateGame);
        kieSession.getAgenda().getAgendaGroup("late-game").setFocus();
        kieSession.fireAllRules();

        AdviceStorage adv = (AdviceStorage) kieSession.getGlobal("adviceStorage");

        assertEquals("[catch, late] Even with a kill lead your objective and tower control is rather weak, which will end up losing you the game. Use the strength you have, which is your map control, and prevent their attempts to take objectives/towers by catching them off guard and killing them off separately.", adv.getAdvices().get(0));



    }
}

