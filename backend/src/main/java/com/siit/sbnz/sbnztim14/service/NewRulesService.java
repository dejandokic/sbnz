package com.siit.sbnz.sbnztim14.service;

import com.siit.sbnz.sbnztim14.controller.CreateRuleController;
import com.siit.sbnz.sbnztim14.model.EarlyGameRule;
import com.siit.sbnz.sbnztim14.model.EarlyJungleRule;
import com.siit.sbnz.sbnztim14.model.MidLateGameRule;
import org.drools.template.ObjectDataCompiler;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

@Service
public class NewRulesService {

    public boolean createNewEarlyGameRule(EarlyGameRule earlyGameRule) {
        InputStream template = NewRulesService.class.getResourceAsStream("C:/Users/Dz0n1Pr0/Desktop/sbnz/drools-kjar/src/main/resources/com.siit.sbnz.sbnztim14.earlyGame/early-lane.drt");

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

        return true;
    }

    public boolean createNewJungleRule(EarlyJungleRule earlyJungleRule) {
        InputStream template = CreateRuleController.class.getResourceAsStream("C:/Users/Dz0n1Pr0/Desktop/sbnz/drools-kjar/src/main/resources/com.siit.sbnz.sbnztim14.earlyGame/early-jungle.drt");

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

        return true;
    }

    public boolean createNewTeamCompositionRule(MidLateGameRule compositionRule) {
        InputStream template = CreateRuleController.class.getResourceAsStream("C:/Users/Dz0n1Pr0/Desktop/sbnz/drools-kjar/src/main/resources/com.siit.sbnz.sbnztim14.teamcomp/mid-late-lane.drt");

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
        return true;
    }
}
