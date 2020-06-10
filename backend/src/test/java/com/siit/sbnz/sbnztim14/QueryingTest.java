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
        Set<Integer> generated = new LinkedHashSet<>();
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
            assertTrue(top.isHardEngage());
            assertTrue(top.isHardCC());
            assertTrue(top.isAoeDamage());

            Champion jungle = (Champion) row.get("$jungle");
            assertTrue(jungle.isHardEngage());
            assertTrue(jungle.isHardCC());
            assertTrue(jungle.isAoeDamage());

            Champion mid = (Champion) row.get("$mid");
            assertTrue(mid.isHardCC());
            assertTrue(mid.isAoeDamage());

            Champion adc = (Champion) row.get("$adc");
            assertTrue(adc.isHardCC());

            Champion support = (Champion) row.get("$support");
            assertTrue(support.isHardEngage());
            assertTrue(support.isHardCC());
            i++;
        }
    }

    @Test
    public void catchCompTest() {
        QueryResults results = kSession.getQueryResults("getGoodCatchComp");
        System.out.println(results.size());
        Set<Integer> generated = new LinkedHashSet<>();
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
            assertTrue(top.getRole().equals("assassin") || top.getRole().equals("diver"));
            assertTrue(top.isHardCC());

            Champion jungle = (Champion) row.get("$jungle");
            assertTrue(jungle.getRole().equals("assassin") || jungle.getRole().equals("diver") ||
                    jungle.getRole().equals("catcher") || jungle.getRole().equals("burst"));
            assertTrue(jungle.isHardCC());
            assertTrue(jungle.isMobility());

            Champion mid = (Champion) row.get("$mid");
            assertTrue(mid.getRole().equals("assassin") || mid.getRole().equals("burst"));
            assertTrue(mid.isHardCC());
            assertTrue(mid.isMobility());

            Champion adc = (Champion) row.get("$adc");
            assertTrue(adc.isHardCC() || adc.isMobility());

            Champion support = (Champion) row.get("$support");
            assertTrue(support.getRole().equals("catcher") || support.getRole().equals("diver"));
            assertTrue(support.isMobility());
            assertTrue(support.isHardCC());
            i++;
        }
    }

    @Test
    public void protectCompTest() {
        QueryResults results = kSession.getQueryResults("getGoodProtectComp");
        System.out.println(results.size());
        Set<Integer> generated = new LinkedHashSet<>();
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
            assertTrue(top.getRole().equals("warden") || top.getRole().equals("vanguard"));
            assertTrue(top.isHardCC());

            Champion jungle = (Champion) row.get("$jungle");
            assertTrue((jungle.getRole().equals("warden") || jungle.getRole().equals("vanguard")) ||
                    (jungle.isHardCC() || jungle.isDisengage()));

            Champion mid = (Champion) row.get("$mid");
            assertTrue(mid.getRole().equals("artillery") || mid.getRole().equals("burst") || mid.getRole().equals("battlemage"));
            assertTrue(mid.isGoodLate());

            Champion adc = (Champion) row.get("$adc");
            assertTrue(adc.getRole().equals("artillery") || adc.getRole().equals("marksman"));
            assertTrue(adc.isGoodLate());

            Champion support = (Champion) row.get("$support");
            assertTrue(support.isHardCC());
            assertTrue(support.isDisengage() || support.isUtility());
            i++;
        }
    }

    @Test
    public void siegeCompTest() {
        QueryResults results = kSession.getQueryResults("getGoodSiegeComp");
        System.out.println(results.size());
        Set<Integer> generated = new LinkedHashSet<>();
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
            assertTrue(top.isPoke());

            Champion jungle = (Champion) row.get("$jungle");
            assertTrue((jungle.getRole().equals("warden") || jungle.getRole().equals("vanguard")) ||
                    (jungle.isHardCC() || jungle.isDisengage()));

            Champion mid = (Champion) row.get("$mid");
            assertEquals("artillery", mid.getRole());
            assertTrue(mid.isWaveclear());

            Champion adc = (Champion) row.get("$adc");
            assertTrue(adc.isPoke());

            Champion support = (Champion) row.get("$support");
            assertTrue(support.isHardCC());
            assertTrue(support.isDisengage());
            i++;
        }
    }

    @Test
    public void splitCompTest() {
        QueryResults results = kSession.getQueryResults("getGoodSplitComp");
        System.out.println(results.size());
        Set<Integer> generated = new LinkedHashSet<>();
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
            assertTrue(top.getRole().equals("skirmisher") || top.getRole().equals("juggernaut"));
            assertTrue(top.isSplitPush());

            Champion jungle = (Champion) row.get("$jungle");
            assertTrue(jungle.getRole().equals("skirmisher") || jungle.getRole().equals("juggernaut"));
            assertTrue(jungle.isSplitPush());

            Champion mid = (Champion) row.get("$mid");
            assertTrue(mid.isDisengage());
            assertTrue(mid.isWaveclear());

            Champion adc = (Champion) row.get("$adc");
            assertTrue(adc.isWaveclear());

            Champion support = (Champion) row.get("$support");
            assertTrue(support.isHardCC());
            assertTrue(support.isDisengage());
            i++;
        }
    }

}
