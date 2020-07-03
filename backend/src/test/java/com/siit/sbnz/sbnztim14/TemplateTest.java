package com.siit.sbnz.sbnztim14;

import com.siit.sbnz.sbnztim14.controller.CreateRuleController;
import com.siit.sbnz.sbnztim14.model.*;
import com.siit.sbnz.sbnztim14.service.ChampionService;
import org.drools.template.ObjectDataCompiler;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.KieServices;
//import org.kie.api.definition.rule.All;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;


public class TemplateTest {

    static ChampionService championService = new ChampionService();
    static KieContainer kContainer;

    @BeforeClass
    public static void beforeClass() {
        KieServices kieServices = KieServices.Factory.get();
        kContainer = kieServices.getKieClasspathContainer();
    }

    @Test
    public void earlyLaneRule() throws FileNotFoundException {

        InputStream template = TemplateTest.class.getResourceAsStream("/com.siit.sbnz.sbnztim14.earlyGame/early-lane.drt");

        List<EarlyGameRule> data = new ArrayList<EarlyGameRule>();

        data.add(new EarlyGameRule("battlemage","warden","hardCC + waveclear + disengage","def",true,false,false,true,true,false,false,"TEST"));

        ObjectDataCompiler converter = new ObjectDataCompiler();
        String drl = converter.compile(data, template);

        drl = "\n" + drl.substring(drl.indexOf("rule"));

        try {
            Files.write(Paths.get("src/main/resources/com.siit.sbnz.sbnztim14.earlyGame/newRulesEarlyLane.drl"), drl.getBytes(), StandardOpenOption.APPEND);
        }catch (IOException e) {
            throw new FileNotFoundException(e.toString());
        }
    }

    @Test
    public void earlyJungleRule() throws FileNotFoundException {

        InputStream template = TemplateTest.class.getResourceAsStream("/com.siit.sbnz.sbnztim14.earlyGame/early-jungle.drt");

        List<EarlyJungleRule> data = new ArrayList<EarlyJungleRule>();

        data.add(new EarlyJungleRule("battlemage","hardCC + waveclear + disengage",true,false,false,true,true,false,false,"TEST"));

        ObjectDataCompiler converter = new ObjectDataCompiler();
        String drl = converter.compile(data, template);

        drl = "\n" + drl.substring(drl.indexOf("rule"));

        try {
            Files.write(Paths.get("src/main/resources/com.siit.sbnz.sbnztim14.earlyGame/newRulesJungleEarly.drl"), drl.getBytes(), StandardOpenOption.APPEND);
        }catch (IOException e) {
            throw new FileNotFoundException(e.toString());
        }
    }

    @Test
    public void midLateTeamcompRule() throws FileNotFoundException {

        InputStream template = TemplateTest.class.getResourceAsStream("/com.siit.sbnz.sbnztim14.teamcomp/mid-late-lane.drt");

        List<MidLateGameRule> data = new ArrayList<MidLateGameRule>();

        data.add(new MidLateGameRule("battlemage","attack","hardCC + waveclear + disengage",true,false,false,true,true,false,false,false, false, "TEST"));

        ObjectDataCompiler converter = new ObjectDataCompiler();
        String drl = converter.compile(data, template);

        drl = "\n" + drl.substring(drl.indexOf("rule"));

        try {
            Files.write(Paths.get("src/main/resources/com.siit.sbnz.sbnztim14.teamcomp/newRulesMidLateTeamcomp.drl"), drl.getBytes(), StandardOpenOption.APPEND);
        }catch (IOException e) {
            throw new FileNotFoundException(e.toString());
        }
    }



    @Test
    public void earlyLaneTest(){
        KieSession kSession = kContainer.newKieSession("ksession-rules");
        kSession.setGlobal("adviceStorage", new AdviceStorage());

        Champion ally = championService.getChampionByName("Anivia");
        ally.setLane("mid");
        Champion enemy = championService.getChampionByName("Braum");
        enemy.setLane("mid");


        AllyChampion allyChampion = new AllyChampion(ally, "def");
        EnemyChampion enemyChampion = new EnemyChampion(enemy);

        kSession.insert(allyChampion);

        kSession.insert(enemyChampion);

        kSession.getAgenda().getAgendaGroup("early-game-part-1").setFocus();
        kSession.fireAllRules();

        AdviceStorage adviceStorage = (AdviceStorage) kSession.getGlobal("adviceStorage");

        kSession.dispose();
        System.out.println(adviceStorage.getAdvices());
    }

    @Test
    public void earlyJungleTest(){
        KieSession kSession = kContainer.newKieSession("ksession-rules");
        kSession.setGlobal("adviceStorage", new AdviceStorage());

        Champion ally = championService.getChampionByName("Anivia");
        ally.setLane("jungle");


        AllyChampion allyChampion = new AllyChampion(ally, "def");

        kSession.insert(allyChampion);


        kSession.getAgenda().getAgendaGroup("early-game-part-1").setFocus();
        kSession.fireAllRules();

        AdviceStorage adviceStorage = (AdviceStorage) kSession.getGlobal("adviceStorage");

        kSession.dispose();
        System.out.println(adviceStorage.getAdvices());
    }


    @Test
    public void midLateTeamcompTest(){
        KieSession kSession = kContainer.newKieSession("ksession-rules");
        kSession.setGlobal("adviceStorage", new AdviceStorage());

        Champion ally = championService.getChampionByName("Anivia");
        ally.setLane("mid");
        TeamComposition teamComposition = new TeamComposition("attack");

        AllyChampion allyChampion = new AllyChampion(ally, "def");

        kSession.insert(allyChampion);

        kSession.insert(teamComposition);

        kSession.getAgenda().getAgendaGroup("teamcomp-role").setFocus();
        kSession.fireAllRules();

        AdviceStorage adviceStorage = (AdviceStorage) kSession.getGlobal("adviceStorage");

        kSession.dispose();
        System.out.println(adviceStorage.getAdvices());
    }



}

