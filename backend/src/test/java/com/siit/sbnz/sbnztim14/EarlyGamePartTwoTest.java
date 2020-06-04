package com.siit.sbnz.sbnztim14;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.siit.sbnz.sbnztim14.model.AdviceStorage;
import com.siit.sbnz.sbnztim14.model.JSONChampList;
import com.siit.sbnz.sbnztim14.model.JSONChampion;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.io.File;
import java.io.IOException;

public class EarlyGamePartTwoTest {

    static KieSession kSession = null;
    static KieContainer kieContainer;

    @BeforeClass
    public static void beforeClass() {
//        KieServices ks = KieServices.Factory.get();
//        kieContainer = ks.getKieClasspathContainer();
//        kSession = kieContainer.newKieSession("ksession-rules");
//        kSession.setGlobal("adviceStorage", new AdviceStorage());
        ObjectMapper objectMapper = new ObjectMapper();

        //read json file and convert to customer object
        try {
            JSONChampList champList = objectMapper.readValue(new File("/Users/dejandokic/Desktop/Projects/Faks/sbnz/backend/src/main/resources/data.json"), JSONChampList.class);
            System.out.println(champList.getChampions().size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void firstTest() {
    }
}

