package com.odk02.ikavote.controllers;

import com.odk02.ikavote.models.Evaluation;
import com.odk02.ikavote.repository.CriteresRepository;
import com.odk02.ikavote.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class EvaluationController {


    @Autowired
    EvaluationService evaluationService;

  @Autowired
  CriteresRepository criteresRepository;

    @PostMapping("/noterprojet")
    @PostAuthorize("hasAuthority('SUPERADMIN')")
    Object addEvaluation(@RequestBody Evaluation evaluation) {

      if (criteresRepository.findById(evaluation.getCriteres().getId()).get().getEvenements().getBareme() >= evaluation.getNote()) {
        return evaluationService.addEvaluation(evaluation.getCodevotant().getId(),
          evaluation.getCriteres().getId(),
          evaluation.getProjets().getId(),
          evaluation.getNote());
      }
      else {
        return "Votre note superieur a la bareme";
      }
    }

    @PostMapping("/noterprojetjury")
    @PostAuthorize("hasAuthority('JURY')")
    Object addEvaluationJury(@RequestBody Evaluation evaluation) {
      if (criteresRepository.findById(evaluation.getCriteres().getId()).get().getEvenements().getBareme() >= evaluation.getNote()) {
        return evaluationService.addEvaluationJury(
          evaluation.getCriteres().getId(),
          evaluation.getProjets().getId(),
          evaluation.getUser().getId(),
          evaluation.getNote());
      }
      return "Votre note superieur a la bareme";
    }

  @GetMapping("/moyenne")
  public Map<Long, Double> getProjectAverages() {
    return evaluationService.calculMoyenneGeneralProject();
  }

  @GetMapping("/moyennejury")
  public Map<Long, Double> getProjectMoyJury() {
    return evaluationService.calculMoyenneGeneralProjectJury();
  }

  @GetMapping("/moyenneparti")
  public Map<Long, Double> getProjectMoyParticipant() {
    return evaluationService.calculMoyenneGeneralProjectParticipant();
  }





}
