package com.odk02.ikavote.service.serviceImpl;


import com.odk02.ikavote.models.*;


import com.odk02.ikavote.repository.*;

import com.odk02.ikavote.service.EvaluationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class EvaluationServiceImpl implements EvaluationService {

  @Autowired
  private EvaluationRepository evaluationRepository;

  @Autowired
  private CodevotantRepository codevotantRepository;

  @Autowired
  private CriteresRepository criteresRepository;

  @Autowired
  private ProjetsRepository projetsRepository;

  @Autowired
  private UserRepository userRepository;


  public Object addEvaluation(Long id_codevotant, Long id_critere, Long id_projet, Long note) {

    // Vérifie si un code votant a déjà évalué ce projet
    if (evaluationRepository.existsByCodevotantAndProjetsAndCriteres(codevotantRepository.findById(id_codevotant).get(),
      projetsRepository.findById(id_projet).get(),
      criteresRepository.findById(id_critere).get())) {
      return "Vous avez déjà évalué ce projet sur ce critere";
    } else {

      Evaluation evaluation = new Evaluation();

      evaluation.setCodevotant(codevotantRepository.findById(id_codevotant).get());

      evaluation.setCriteres(criteresRepository.findById(id_critere).get());
      evaluation.setProjets(projetsRepository.findById(id_projet).get());
      evaluation.setNote(note);

      evaluationRepository.save(evaluation);
    }
    return "Note envoyer";
  }

   public Object addEvaluationJury(Long id_critere, Long id_projet, Long id_user, Long note) {

    // Vérifie si un code votant a déjà évalué ce projet
    if (evaluationRepository.existsByUserAndProjetsAndCriteres(userRepository.findById(id_user).get(),
      projetsRepository.findById(id_projet).get(),
      criteresRepository.findById(id_critere).get())) {
      return "Vous avez déjà évalué ce projet sur ce critere";
    } else {

      Evaluation evaluation = new Evaluation();


      evaluation.setCriteres(criteresRepository.findById(id_critere).get());
      evaluation.setProjets(projetsRepository.findById(id_projet).get());
      evaluation.setUser(userRepository.findById(id_user).get());
      evaluation.setNote(note);

      evaluationRepository.save(evaluation);
    }
    return "Note envoyer";
  }
}

