package com.siit.sbnz.sbnztim14.controller;

import com.siit.sbnz.sbnztim14.model.EarlyGameRule;
import com.siit.sbnz.sbnztim14.model.EarlyJungleRule;
import com.siit.sbnz.sbnztim14.model.MidLateGameRule;
import lombok.RequiredArgsConstructor;
import org.apache.http.protocol.HTTP;
import org.drools.template.ObjectDataCompiler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/create")
public class CreateRuleController {

    @PostMapping
    public ResponseEntity createRule(@RequestBody EarlyGameRule ruleDTO) throws FileNotFoundException {

        InputStream template = CreateRuleController.class.getResourceAsStream("/com.siit.sbnz.sbnztim14.earlyGame/early-lane.drt");

        List<EarlyGameRule> data = new ArrayList<EarlyGameRule>();

        data.add(ruleDTO);

        ObjectDataCompiler converter = new ObjectDataCompiler();
        String drl = converter.compile(data, template);

        drl = drl.substring(drl.indexOf("rule"));

        try {
            Files.write(Paths.get("C:/Users/Dz0n1Pr0/Desktop/sbnz/backend/src/main/resources/com.siit.sbnz.sbnztim14.earlyGame/newRulesEarlyLane.drl"), drl.getBytes(), StandardOpenOption.APPEND);
        }catch (IOException e) {
            throw new FileNotFoundException(e.toString());
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(value = "jungle")
    public ResponseEntity createJungleRule(@RequestBody EarlyJungleRule ruleDTO) throws FileNotFoundException {

        InputStream template = CreateRuleController.class.getResourceAsStream("/com.siit.sbnz.sbnztim14.earlyGame/early-jungle.drt");

        List<EarlyJungleRule> data = new ArrayList<EarlyJungleRule>();

        data.add(ruleDTO);

        ObjectDataCompiler converter = new ObjectDataCompiler();
        String drl = converter.compile(data, template);

        drl = drl.substring(drl.indexOf("rule"));

        try {
            Files.write(Paths.get("C:/Users/Dz0n1Pr0/Desktop/sbnz/backend/src/main/resources/com.siit.sbnz.sbnztim14.earlyGame/newRulesJungleEarly.drl"), drl.getBytes(), StandardOpenOption.APPEND);
        }catch (IOException e) {
            throw new FileNotFoundException(e.toString());
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(value = "teamcomp")
    public ResponseEntity createMidLateRule(@RequestBody MidLateGameRule ruleDTO) throws FileNotFoundException {

        InputStream template = CreateRuleController.class.getResourceAsStream("/com.siit.sbnz.sbnztim14.teamcomp/mid-late-lane.drt");

        List<MidLateGameRule> data = new ArrayList<MidLateGameRule>();

        data.add(ruleDTO);

        ObjectDataCompiler converter = new ObjectDataCompiler();
        String drl = converter.compile(data, template);

        drl = drl.substring(drl.indexOf("rule"));

        try {
            Files.write(Paths.get("C:/Users/Dz0n1Pr0/Desktop/sbnz/backend/src/main/resources/com.siit.sbnz.sbnztim14.teamcomp/newRulesMidLateTeamcomp.drl"), drl.getBytes(), StandardOpenOption.APPEND);
        }catch (IOException e) {
            throw new FileNotFoundException(e.toString());
        }
        return new ResponseEntity(HttpStatus.OK);
    }



}
