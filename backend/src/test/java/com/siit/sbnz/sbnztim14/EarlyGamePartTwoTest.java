package com.siit.sbnz.sbnztim14;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.siit.sbnz.sbnztim14.model.AdviceStorage;
import com.siit.sbnz.sbnztim14.model.Champion;
import com.siit.sbnz.sbnztim14.model.JSONChampList;
import com.siit.sbnz.sbnztim14.model.JSONChampion;
import com.siit.sbnz.sbnztim14.service.ChampionService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class EarlyGamePartTwoTest {

    static KieSession kSession = null;
    static KieContainer kieContainer;
    static ChampionService championService = new ChampionService();

    @BeforeClass
    public static void beforeClass() {
        KieServices ks = KieServices.Factory.get();
        kieContainer = ks.getKieClasspathContainer();
        kSession = kieContainer.newKieSession("ksession-rules");
        kSession.setGlobal("adviceStorage", new AdviceStorage());
    }

    @Test
    public void firstTest() {
    }
}

