package com.siit.sbnz.sbnztim14.controller;

import com.siit.sbnz.sbnztim14.dto.FirstInteraction;
import com.siit.sbnz.sbnztim14.dto.FourthInteraction;
import com.siit.sbnz.sbnztim14.dto.SecondInteraction;
import com.siit.sbnz.sbnztim14.dto.ThirdInteraction;
import com.siit.sbnz.sbnztim14.model.AdviceStorage;
import com.siit.sbnz.sbnztim14.service.StrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/strategy")
public class StrategyController {

    private final StrategyService strategyService;

    @Autowired
    public StrategyController(StrategyService service) {
        this.strategyService = service;
    }


    @PostMapping(value = "/interaction1")
    @PreAuthorize("hasAuthority('REGISTERED_USER') or hasAuthority('ADMIN')")
    public ResponseEntity<List<String>> firstInteraction(@RequestBody FirstInteraction firstInteraction) {
        AdviceStorage adviceStorage = strategyService.getStrategyEarlyGamePartOne(firstInteraction);
        return new ResponseEntity<>(adviceStorage.getAdvices(), HttpStatus.OK);
    }

    @PostMapping(value = "/interaction2")
    @PreAuthorize("hasAuthority('REGISTERED_USER') or hasAuthority('ADMIN')")
    public ResponseEntity<List<String>> secondInteraction(@RequestBody SecondInteraction secondInteraction) {
        AdviceStorage adviceStorage = strategyService.getStrategyEarlyGamePartTwo(secondInteraction);
        return new ResponseEntity<>(adviceStorage.getAdvices(), HttpStatus.OK);
    }

    @PostMapping(value = "/interaction3")
    @PreAuthorize("hasAuthority('REGISTERED_USER') or hasAuthority('ADMIN')")
    public ResponseEntity<List<String>> thirdInteraction(@RequestBody ThirdInteraction thirdInteraction) {
        AdviceStorage adviceStorage = strategyService.getStrategyMidGame(thirdInteraction);
        return new ResponseEntity<>(adviceStorage.getAdvices(), HttpStatus.OK);
    }

    @PostMapping(value = "/interaction4")
    @PreAuthorize("hasAuthority('REGISTERED_USER') or hasAuthority('ADMIN')")
    public ResponseEntity<List<String>> fourthInteraction(@RequestBody FourthInteraction fourthInteraction) {
        AdviceStorage adviceStorage = strategyService.getStrategyLateGame(fourthInteraction);
        return new ResponseEntity<>(adviceStorage.getAdvices(), HttpStatus.OK);
    }

}
