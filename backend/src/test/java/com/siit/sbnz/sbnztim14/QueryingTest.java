package com.siit.sbnz.sbnztim14;

import com.siit.sbnz.sbnztim14.model.Champion;
import com.siit.sbnz.sbnztim14.service.ChampionService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static org.junit.Assert.*;

public class QueryingTest {

    static ChampionService championService;
    static KieContainer kContainer;
    static Random rng;
    KieSession kSession;

    @BeforeClass
    public static void beforeClass() {
        championService = new ChampionService();
        KieServices kieServices = KieServices.Factory.get();
        kContainer = kieServices.getKieClasspathContainer();
        rng = new Random();
    }

    @Before
    public void beforeEach() {
        kSession = kContainer.newKieSession("ksession-rules");
        List<Champion> allChampions = championService.getAllChampions();
        for(Champion champ : allChampions){
            kSession.insert(champ);
        }
    }

    @Test
    public void attackCompTest() {
        QueryResults results = kSession.getQueryResults("getGoodAttackComp");
        System.out.println(results.size());
        Set<Integer> generated = new LinkedHashSet<Integer>();
        while (generated.size() < 5)
        {
            Integer next = rng.nextInt(results.size()) + 1;
            generated.add(next);
        }
        int i = 0;
        for (QueryResultsRow row : results) {
            if(!generated.contains(i)) {
                i++;
                continue;
            }
            Champion top = (Champion) row.get("$top");
            System.out.println(top);
            Champion jungle = (Champion) row.get("$jungle");
            System.out.println(jungle);
            Champion mid = (Champion) row.get("$mid");
            System.out.println(mid);
            Champion adc = (Champion) row.get("$adc");
            System.out.println(adc);
            Champion support = (Champion) row.get("$support");
            System.out.println(support);
            System.out.println();
            i++;
        }
    }

    @Test
    public void catchCompTest() {
        QueryResults results = kSession.getQueryResults("getGoodCatchComp");
        System.out.println(results.size());
        Set<Integer> generated = new LinkedHashSet<Integer>();
        while (generated.size() < 5)
        {
            Integer next = rng.nextInt(results.size()) + 1;
            generated.add(next);
        }
        int i = 0;
        for (QueryResultsRow row : results) {
            if(!generated.contains(i)) {
                i++;
                continue;
            }
            Champion top = (Champion) row.get("$top");
            System.out.println(top);
            Champion jungle = (Champion) row.get("$jungle");
            System.out.println(jungle);
            Champion mid = (Champion) row.get("$mid");
            System.out.println(mid);
            Champion adc = (Champion) row.get("$adc");
            System.out.println(adc);
            Champion support = (Champion) row.get("$support");
            System.out.println(support);
            System.out.println();
            i++;
        }
    }

    @Test
    public void protectCompTest() {
        QueryResults results = kSession.getQueryResults("getGoodProtectComp");
        System.out.println(results.size());
        Set<Integer> generated = new LinkedHashSet<Integer>();
        while (generated.size() < 5)
        {
            Integer next = rng.nextInt(results.size()) + 1;
            generated.add(next);
        }
        int i = 0;
        for (QueryResultsRow row : results) {
            if(!generated.contains(i)) {
                i++;
                continue;
            }
            Champion top = (Champion) row.get("$top");
            System.out.println(top);
            Champion jungle = (Champion) row.get("$jungle");
            System.out.println(jungle);
            Champion mid = (Champion) row.get("$mid");
            System.out.println(mid);
            Champion adc = (Champion) row.get("$adc");
            System.out.println(adc);
            Champion support = (Champion) row.get("$support");
            System.out.println(support);
            System.out.println();
            i++;
        }
    }

}
