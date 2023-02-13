package com.odk02.ikavote.service;

import com.odk02.ikavote.models.*;
import com.odk02.ikavote.repository.EvaluationRepository;

import java.util.Map;

public interface EvaluationService {

  Object addEvaluationJury(Long id_user, Long id_critere, Long id_projet, Long note);

  Object addEvaluation(Long id_codevotant, Long id_critere, Long id_projet, Long note);

 // Map<Long, Double> calculMoyenneGeneralProject();

  Map<Long, Double> calculMoyenneGeneralProjectVotant();

  Map<Long, Double> calculMoyenneGeneralProjectJury();


  Long nbreJury();

 // Map<Long, Double> calculMoyenneGeneralProjectJurykura(Long idEvents);

  Map<Long, Double> calculMoyenneGeneralProjectVotantKura(Long eventId);

  Map<Long, Double> calculMoyenneGeneralProjectJuryKura(Long eventId);

}
