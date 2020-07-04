package com.siit.sbnz.sbnztim14.service;

import com.siit.sbnz.sbnztim14.controller.CreateRuleController;
import com.siit.sbnz.sbnztim14.model.EarlyGameRule;
import com.siit.sbnz.sbnztim14.model.EarlyJungleRule;
import com.siit.sbnz.sbnztim14.model.MidLateGameRule;
import org.apache.maven.shared.invoker.*;
import org.drools.template.ObjectDataCompiler;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class NewRulesService {

    @Autowired
    private Environment env;

    public boolean createNewEarlyGameRule(EarlyGameRule earlyGameRule) {
        InputStream template = NewRulesService.class.getResourceAsStream("/templates/early-lane.drt");

        List<EarlyGameRule> data = new ArrayList<>();

        data.add(earlyGameRule);

        ObjectDataCompiler converter = new ObjectDataCompiler();
        String drl = converter.compile(data, template);

        drl = drl.substring(drl.indexOf("rule"));

        try {
            Files.write(Paths.get("C:/Users/Dz0n1Pr0/Desktop/sbnz/drools-kjar/src/main/resources/com.siit.sbnz.sbnztim14.earlyGame/newRulesEarlyLane.drl"), drl.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            return false;
        }

        triggerMvnInstall();
        return true;
    }

    public boolean createNewJungleRule(EarlyJungleRule earlyJungleRule) {
        InputStream template = CreateRuleController.class.getResourceAsStream("/templates/early-jungle.drt");

        List<EarlyJungleRule> data = new ArrayList<>();

        data.add(earlyJungleRule);

        ObjectDataCompiler converter = new ObjectDataCompiler();
        String drl = converter.compile(data, template);

        drl = drl.substring(drl.indexOf("rule"));

        try {
            Files.write(Paths.get("C:/Users/Dz0n1Pr0/Desktop/sbnz/drools-kjar/src/main/resources/com.siit.sbnz.sbnztim14.earlyGame/newRulesJungleEarly.drl"), drl.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            return false;
        }

        triggerMvnInstall();
        return true;
    }

    public boolean createNewTeamCompositionRule(MidLateGameRule compositionRule) {
        InputStream template = CreateRuleController.class.getResourceAsStream("/templates/mid-late-lane.drt");

        List<MidLateGameRule> data = new ArrayList<>();

        data.add(compositionRule);

        ObjectDataCompiler converter = new ObjectDataCompiler();
        String drl = converter.compile(data, template);

        drl = drl.substring(drl.indexOf("rule"));

        try {
            Files.write(Paths.get("C:/Users/Dz0n1Pr0/Desktop/sbnz/drools-kjar/src/main/resources/com.siit.sbnz.sbnztim14.teamcomp/newRulesMidLateTeamcomp.drl"), drl.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            return false;
        }
        triggerMvnInstall();
        return true;
    }

    public void triggerMvnInstall() {

        String projectPath = System.getProperty("user.dir") + "/../drools-kjar/pom.xml";

        InvocationRequest request = new DefaultInvocationRequest();
        request.setPomFile( new File( projectPath ) );
        request.setGoals( Arrays.asList( "clean", "install" ) );

        Invoker invoker = new DefaultInvoker();
        String var = env.getProperty("config.mvn.path");
        invoker.setMavenHome(new File(var));
        try {
            invoker.execute( request );
        } catch (MavenInvocationException e) {
            e.printStackTrace();
        }
     }
}
