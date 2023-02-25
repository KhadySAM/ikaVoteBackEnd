package com.odk02.ikavote.controllers;

import com.odk02.ikavote.models.Evaluation;
import com.odk02.ikavote.models.Evenements;
import com.odk02.ikavote.models.Projets;
import com.odk02.ikavote.models.Resultat;
import com.odk02.ikavote.repository.EvaluationRepository;
import com.odk02.ikavote.repository.EvenementsRepository;
import com.odk02.ikavote.repository.ProjetsRepository;
import com.odk02.ikavote.repository.ResultatRepository;
import com.odk02.ikavote.service.EvaluationService;
import com.odk02.ikavote.service.ResultatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins ={ "http://localhost:4200/", "http://localhost:8100/" }, maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/api/auth")
public class ResultatController {

  @Autowired
  private ResultatService resultatService;

  @GetMapping("/getResultatParId/{idEvent}")
  public List<Resultat> getResultatsParEvenement(@PathVariable Long idEvent) {
    return resultatService.getResultatsParEvenement(idEvent);
  }

}
