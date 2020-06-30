package com.siit.sbnz.sbnztim14.controller;

import com.siit.sbnz.sbnztim14.exceptions.BadRequestException;
import com.siit.sbnz.sbnztim14.model.EarlyGameRule;
import com.siit.sbnz.sbnztim14.model.EarlyJungleRule;
import com.siit.sbnz.sbnztim14.model.MidLateGameRule;
import com.siit.sbnz.sbnztim14.service.NewRulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/new-rule")
public class CreateRuleController {

    private final NewRulesService newRulesService;

    @Autowired
    public CreateRuleController(NewRulesService newRulesService) {
        this.newRulesService = newRulesService;
    }

    @PostMapping(value="/early-game")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> createEarlyGameRule(@RequestBody EarlyGameRule ruleDTO) {

        System.out.println(ruleDTO);

        boolean check = newRulesService.createNewEarlyGameRule(ruleDTO);

        if (check) {
            return new ResponseEntity<>("New early game rule added successfully", HttpStatus.OK);
        } else {
            throw new BadRequestException("Error while creating new early game rule");
        }
    }

    @PostMapping(value = "/jungle")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity createJungleRule(@RequestBody EarlyJungleRule earlyJungleRule) {

        boolean check = newRulesService.createNewJungleRule(earlyJungleRule);

        if (check) {
            return new ResponseEntity<>("New early jungle rule added successfully", HttpStatus.OK);
        } else {
            throw new BadRequestException("Error while creating new early jungle rule");
        }
    }

    @PostMapping(value = "/team-composition")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity createTeamCompositionRule(@RequestBody MidLateGameRule teamCompositionRule) {

        boolean check = newRulesService.createNewTeamCompositionRule(teamCompositionRule);

        if (check) {
            return new ResponseEntity<>("New team composition rule added successfully", HttpStatus.OK);
        } else {
            throw new BadRequestException("Error while creating new team composition rule");
        }
    }



}
