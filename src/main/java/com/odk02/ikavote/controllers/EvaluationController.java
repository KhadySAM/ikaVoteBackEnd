package com.odk02.ikavote.controllers;

import com.odk02.ikavote.models.*;
import com.odk02.ikavote.repository.CriteresRepository;
import com.odk02.ikavote.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins ={ "http://localhost:4200/", "http://localhost:8100/" }, maxAge = 3600, allowCredentials="true")

@RestController
@RequestMapping("/api/auth")
public class EvaluationController {


    @Autowired
    EvaluationService evaluationService;

    @Autowired
    CriteresRepository criteresRepository;

    @PostMapping("/noterprojetvotant")
  //  @PostAuthorize("hasAuthority('SUPERADMIN')")
    Object addEvaluationVotant(@RequestBody Evaluation evaluation) {

      if (criteresRepository.findById(evaluation.getCriteres().getId()).get().getEvenements().getBareme() >= evaluation.getNote()) {
        return evaluationService.addEvaluationVotant(
          evaluation.getCodevotant().getId(),
          evaluation.getCriteres().getId(),
          evaluation.getProjets().getId(),
          evaluation.getNote());
      }
      else {
        return "Votre note superieur a la bareme";
      }
    }

    @PostMapping("/noterprojetjury")
   // @PostAuthorize("hasAuthority('JURY')")
    Object  addEvaluationJury(@RequestBody Evaluation evaluation) {

      if (criteresRepository.findById(evaluation.getCriteres().getId()).get().getEvenements().getBareme() >= evaluation.getNote()) {
            return evaluationService.addEvaluationJury(
              evaluation.getCriteres().getId(),
              evaluation.getProjets().getId(),
              evaluation.getUser().getId(),
              evaluation.getNote());
          } else {
            return "Votre note superieur a la bareme";
          }

    }


  @GetMapping("/moyenneJuryParEvent/{eventId}")
  public Map<Long, Double> getMoyJuryParProjetKura(@PathVariable Long eventId) {

    return evaluationService.calculMoyenneGeneralProjectJury(eventId);

  }

  @GetMapping("/moyenneVotant/{eventId}")
  public Map<Long, Double> getMoyCodevotantarProjetKura(@PathVariable Long eventId) {

    return evaluationService.calculMoyenneGeneralProjectVotant(eventId);
  }




}
