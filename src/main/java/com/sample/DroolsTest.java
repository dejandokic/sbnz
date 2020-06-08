package com.sample;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.sample.model.AllyChampion;
import com.sample.model.EnemyChampion;
import com.sample.model.TeamComposition;
import com.sample.model.TeamCompositionProbability;

/**
 * This is a sample class to launch a rule.
 */
public class DroolsTest {

    public static final void main(String[] args) {
        try {
            // load up the knowledge base
	        KieServices ks = KieServices.Factory.get();
    	    KieContainer kContainer = ks.getKieClasspathContainer();
        	KieSession kSession = kContainer.newKieSession("ksession-rules");
        	
        	kSession.setGlobal("adviceStorage", new AdviceStorage());
        	kSession.setGlobal("allyTeamComposition", new TeamComposition());

        	// Poppy, Sejuani, Lissandra, Ezreal, Braum
        	AllyChampion ally1 = new AllyChampion();
        	ally1.setName("Poppy");
        	ally1.setLane("top");
        	ally1.setRole("warden");
        	ally1.setGoodEarly(true);
        	ally1.setGoodMid(true);
        	ally1.setGoodLate(false);
        	ally1.setHardCC(true);
        	ally1.setHardEngage(true);
        	ally1.setDisengage(false);
        	ally1.setPoke(false);
        	ally1.setWaveclear(false);
        	ally1.setSustain(true);
        	ally1.setUtility(false);
        	ally1.setMobility(false);
        	ally1.setAoeDamage(false);
        	ally1.setSplitPush(false);

        	AllyChampion ally2 = new AllyChampion();
        	ally2.setName("Sejuani");
        	ally2.setLane("jungle");
        	ally2.setRole("vanguard");
        	ally2.setGoodEarly(true);
        	ally2.setGoodMid(true);
        	ally2.setGoodLate(false);
        	ally2.setHardCC(true);
        	ally2.setHardEngage(true);
        	ally2.setDisengage(true);
        	ally2.setPoke(false);
        	ally2.setWaveclear(false);
        	ally2.setSustain(true);
        	ally2.setUtility(false);
        	ally2.setMobility(false);
        	ally2.setAoeDamage(false);
        	ally2.setSplitPush(false);

        	AllyChampion ally3 = new AllyChampion();
        	ally3.setName("Lissandra");
        	ally3.setLane("mid");
        	ally3.setRole("burst");
        	ally3.setGoodEarly(false);
        	ally3.setGoodMid(true);
        	ally3.setGoodLate(false);
        	ally3.setHardCC(true);
        	ally3.setHardEngage(true);
        	ally3.setDisengage(false);
        	ally3.setPoke(false);
        	ally3.setWaveclear(true);
        	ally3.setSustain(false);
        	ally3.setUtility(false);
        	ally3.setMobility(false);
        	ally3.setAoeDamage(true);
        	ally3.setSplitPush(false);

        	AllyChampion ally4 = new AllyChampion();
        	ally4.setName("Ezreal");
        	ally4.setLane("adc");
        	ally4.setRole("marksman");
        	ally4.setGoodEarly(true);
        	ally4.setGoodMid(false);
        	ally4.setGoodLate(true);
        	ally4.setHardCC(false);
        	ally4.setHardEngage(false);
        	ally4.setDisengage(false);
        	ally4.setPoke(true);
        	ally4.setWaveclear(false);
        	ally4.setSustain(false);
        	ally4.setUtility(false);
        	ally4.setMobility(false);
        	ally4.setAoeDamage(false);
        	ally4.setSplitPush(false);

        	AllyChampion ally5 = new AllyChampion();
        	ally5.setName("Braum");
        	ally5.setLane("support");
        	ally5.setRole("warden");
        	ally5.setGoodEarly(false);
        	ally5.setGoodMid(true);
        	ally5.setGoodLate(true);
        	ally5.setHardCC(true);
        	ally5.setHardEngage(true);
        	ally5.setDisengage(true);
        	ally5.setPoke(false);
        	ally5.setWaveclear(false);
        	ally5.setSustain(true);
        	ally5.setUtility(false);
        	ally5.setMobility(false);
        	ally5.setAoeDamage(false);
        	ally5.setSplitPush(false);
        	
        	// Sion, Lee Sin, Akali, Lucian, Pyke
        	EnemyChampion enemy1 = new EnemyChampion();
        	enemy1.setName("Sion");
        	enemy1.setLane("top");
        	enemy1.setRole("vanguard");
        	enemy1.setGoodEarly(true);
        	enemy1.setGoodMid(true);
        	enemy1.setGoodLate(false);
        	enemy1.setHardCC(true);
        	enemy1.setHardEngage(true);
        	enemy1.setDisengage(false);
        	enemy1.setPoke(false);
        	enemy1.setWaveclear(false);
        	enemy1.setSustain(true);
        	enemy1.setUtility(false);
        	enemy1.setMobility(false);
        	enemy1.setAoeDamage(true);
        	enemy1.setSplitPush(false);

        	EnemyChampion enemy2 = new EnemyChampion();
        	enemy2.setName("Lee Sin");
        	enemy2.setLane("jungle");
        	enemy2.setRole("diver");
        	enemy2.setGoodEarly(true);
        	enemy2.setGoodMid(false);
        	enemy2.setGoodLate(false);
        	enemy2.setHardCC(true);
        	enemy2.setHardEngage(false);
        	enemy2.setDisengage(true);
        	enemy2.setPoke(false);
        	enemy2.setWaveclear(false);
        	enemy2.setSustain(true);
        	enemy2.setUtility(false);
        	enemy2.setMobility(true);
        	enemy2.setAoeDamage(false);
        	enemy2.setSplitPush(false);

        	EnemyChampion enemy3 = new EnemyChampion();
        	enemy3.setName("Akali");
        	enemy3.setLane("mid");
        	enemy3.setRole("assassin");
        	enemy3.setGoodEarly(true);
        	enemy3.setGoodMid(false);
        	enemy3.setGoodLate(false);
        	enemy3.setHardCC(true);
        	enemy3.setHardEngage(false);
        	enemy3.setDisengage(false);
        	enemy3.setPoke(false);
        	enemy3.setWaveclear(false);
        	enemy3.setSustain(false);
        	enemy3.setUtility(false);
        	enemy3.setMobility(false);
        	enemy3.setAoeDamage(false);
        	enemy3.setSplitPush(false);

        	EnemyChampion enemy4 = new EnemyChampion();
        	enemy4.setName("Lucian");
        	enemy4.setLane("adc");
        	enemy4.setRole("marksman");
        	enemy4.setGoodEarly(false);
        	enemy4.setGoodMid(true);
        	enemy4.setGoodLate(true);
        	enemy4.setHardCC(false);
        	enemy4.setHardEngage(false);
        	enemy4.setDisengage(false);
        	enemy4.setPoke(false);
        	enemy4.setWaveclear(true);
        	enemy4.setSustain(false);
        	enemy4.setUtility(false);
        	enemy4.setMobility(false);
        	enemy4.setAoeDamage(false);
        	enemy4.setSplitPush(false);

        	EnemyChampion enemy5 = new EnemyChampion();
        	enemy5.setName("Pyke");
        	enemy5.setLane("support");
        	enemy5.setRole("assassin");
        	enemy5.setGoodEarly(true);
        	enemy5.setGoodMid(false);
        	enemy5.setGoodLate(true);
        	enemy5.setHardCC(true);
        	enemy5.setHardEngage(true);
        	enemy5.setDisengage(false);
        	enemy5.setPoke(false);
        	enemy5.setWaveclear(false);
        	enemy5.setSustain(false);
        	enemy5.setUtility(false);
        	enemy5.setMobility(true);
        	enemy5.setAoeDamage(false);
        	enemy5.setSplitPush(false);
        	
        	TeamCompositionProbability tcpAlly = new TeamCompositionProbability("ally");
        	TeamCompositionProbability tcpEnemy = new TeamCompositionProbability("enemy");
        	TeamCompositionProbability tcpFinal = new TeamCompositionProbability("final");
        	
//        	TeamComposition tc = new TeamComposition("splitPush");
        	
        	kSession.insert(ally1);
        	kSession.insert(ally2);
        	kSession.insert(ally3);
        	kSession.insert(ally4);
        	kSession.insert(ally5);
        	kSession.insert(enemy1);
        	kSession.insert(enemy2);
        	kSession.insert(enemy3);
        	kSession.insert(enemy4);
        	kSession.insert(enemy5);
        	kSession.insert(tcpAlly);
        	kSession.insert(tcpEnemy);
        	kSession.insert(tcpFinal);
//        	kSession.insert(tc);
        	
        	kSession.getAgenda().getAgendaGroup("composition-probability").setFocus();
            kSession.fireAllRules();
            System.out.println(kSession.getGlobal("allyTeamComposition"));
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public static class Message {

        public static final int HELLO = 0;
        public static final int GOODBYE = 1;

        private String message;

        private int status;

        public String getMessage() {
            return this.message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getStatus() {
            return this.status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

    }

}
