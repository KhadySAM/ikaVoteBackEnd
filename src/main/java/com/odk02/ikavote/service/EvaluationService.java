package com.odk02.ikavote.service;

import com.odk02.ikavote.models.*;
import com.odk02.ikavote.repository.EvaluationRepository;

import java.util.List;
import java.util.Map;

public interface EvaluationService {

  Object addEvaluationJury(Long id_user, Long id_critere, Long id_projet, Long note);

  Object addEvaluationVotant(Long id_codevotant, Long id_critere, Long id_projet, Long note);

//  Object addEvaluationVotantK(Long id_codevotant, Long id_critere, Long id_projet, Long note);

  boolean checkEvaluation(Long idProjets, Long idCodevotant);

  boolean checkEvaluationUser(Long idProjets, Long idUser);


  Map<Long, Double> calculMoyenneGeneralProjectVotant(Long eventId);

  Map<Long, Double> calculMoyenneGeneralProjectJury(Long eventId);


  void calculerResultatPourEvenement(Long idEvents);

}
