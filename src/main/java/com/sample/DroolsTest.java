package com.sample;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.sample.model.AllyChampion;
import com.sample.model.EnemyChampion;

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

            // go !
            /*Message message = new Message();
            message.setMessage("Hello World");
            message.setStatus(Message.HELLO);
            kSession.insert(message);*/
        	
        	AllyChampion ally = new AllyChampion();
        	ally.setLane("top");
        	ally.setRole("juggernaut");
        	ally.setGoodEarly(true);
        	ally.setHardCC(true);
        	ally.setHardEngage(true);
        	
        	EnemyChampion enemy = new EnemyChampion();
        	enemy.setLane("top");
        	enemy.setRole("juggernaut");
        	enemy.setGoodEarly(false);
        	
        	kSession.insert(ally);
        	kSession.insert(enemy);
        	
            kSession.fireAllRules();
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
