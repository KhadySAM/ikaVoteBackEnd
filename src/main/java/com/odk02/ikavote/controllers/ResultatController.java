package com.odk02.ikavote.controllers;

import com.odk02.ikavote.service.EvaluationService;
import com.odk02.ikavote.service.ResultatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(value = "http://localhost:4200",maxAge = 3600,allowCredentials = "true")
@RestController
@RequestMapping("/api/auth")
public class ResultatController  {

    @Autowired
    ResultatService resultatService;

    @GetMapping("/kadi/{eventId}")
    public Map<Long, Double> getMoyCodevotantarProjetKura(@PathVariable Long eventId) {

        return resultatService.calculResultatGeneralProjectVotantKura(eventId);
    }

    @GetMapping("/kadi1/{eventId}")
    public Map<Long, Double> getMoyJuryarProjetKura(@PathVariable Long eventId) {

        return resultatService.calculResultatGeneralProjectJuryKura(eventId);
    }
}
