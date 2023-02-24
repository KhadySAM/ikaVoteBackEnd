package com.odk02.ikavote.controllers;

import com.odk02.ikavote.models.Evaluation;
import com.odk02.ikavote.models.Evenements;
import com.odk02.ikavote.models.Projets;
import com.odk02.ikavote.repository.EvaluationRepository;
import com.odk02.ikavote.service.EvaluationService;
import com.odk02.ikavote.service.ResultatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(value = "http://localhost:4200",maxAge = 3600,allowCredentials = "true")
@RestController
@RequestMapping("/api/auth")
public class ResultatController  {




 /* @GetMapping("/getevaluationparprojet/{idp}")
  public List<Evaluation> getEvaluationParProjet(@PathVariable Long idp) {

    return evaluationRepository.findByProjets(idp);
  }*/
}
