package com.odk02.ikavote.service.serviceImpl;


import com.odk02.ikavote.models.*;


import com.odk02.ikavote.repository.*;

import com.odk02.ikavote.service.EvaluationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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



/*
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
  }*/




  /*public Object addEvaluationJury(Long id_critere, Long id_projet, Long id_user, Long note) {

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
*/

  public Object addEvaluation(Long id_codevotant, Long id_critere, Long id_projet, Long note) {

    Criteres criteres = criteresRepository.findById(id_critere).get();
    Projets projets = projetsRepository.findById(id_projet).get();
    Codevotant codevotant = codevotantRepository.findById(id_codevotant).get();
    // Vérifie si un user a déjà évalué ce projet
    if (evaluationRepository.existsByCodevotantAndProjetsAndCriteres(codevotant,projets,criteres)){
      return "Vous avez déjà évalué ce projet sur ce critere";
    } else {

      Evaluation evaluation = new Evaluation();

      if(projets.getMoyParcitipant()==null){
        projets.setMoyParcitipant(0L);
      }

      evaluation.setCriteres(criteres);
      projets.setMoyParcitipant(projets.getMoyParcitipant()+note);
      evaluation.setProjets(projets);
      evaluation.setCodevotant(codevotant);
      evaluation.setNote(note);

      evaluationRepository.save(evaluation);
    }
    return "Note envoyer";
  }

  // ======================================================================

  public Object addEvaluationJury(Long id_critere, Long id_projet, Long id_user, Long note) {
      Criteres criteres = criteresRepository.findById(id_critere).get();
      Projets projets = projetsRepository.findById(id_projet).get();
      User user = userRepository.findById(id_user).get();
    // Vérifie si un user a déjà évalué ce projet
    if (evaluationRepository.existsByUserAndProjetsAndCriteres(user,projets,criteres)){
      return "Vous avez déjà évalué ce projet sur ce critere";
    } else {

      Evaluation evaluation = new Evaluation();

      if(projets.getMoyJury()==null){
        projets.setMoyJury(0L);
      }
      evaluation.setCriteres(criteres);
      projets.setMoyJury(projets.getMoyJury()+note);
      evaluation.setProjets(projets);
      evaluation.setUser(user);
      evaluation.setNote(note);

      evaluationRepository.save(evaluation);
    }
    return "Note envoyer";
  }





/*
 @Override
  public Map<Long, Double> calculMoyenneGeneralProject() {
    List<Evaluation> evaluations = evaluationRepository.findAll();
    Map<Long, List<Long>> projectNote = new HashMap<>();


    // Grouper les notes de chaque projet ensemble
    for (Evaluation evaluation : evaluations) {
      Long projectId = evaluation.getProjets().getId();
      Long note = evaluation.getNote();
      if ( !projectNote.containsKey(projectId)) {
        projectNote.put(projectId, new ArrayList<>());
      }
      projectNote.get(projectId).add(note);
    }

    // Calculer la moyenne de chaque groupe de notes de projet
    Map<Long, Double> moyProject = new HashMap<>();
    for (Map.Entry<Long, List<Long>> entry : projectNote.entrySet()) {
      Long projectId = entry.getKey();
      List<Long> notes = entry.getValue();
      Double moyenne = notes.stream().mapToDouble(val -> val).average().orElse(0.0);
      moyProject.put(projectId, moyenne);
    }

    return moyProject;
  }*/

  @Override
  public Map<Long, Double> calculMoyenneGeneralProjectJury() {


    List<Evaluation> evaluations = evaluationRepository.findByUser(null);
    Map<Long, List<Long>> projectNote = new HashMap<>();

  //  Projets projets = new Projets();


    // Grouper les notes de chaque projet ensemble
    for (Evaluation evaluation : evaluations) {
      Long projectId = evaluation.getProjets().getId();
      Long note = evaluation.getNote();
      if ( !projectNote.containsKey(projectId)) {
        projectNote.put(projectId, new ArrayList<>());
      }
      projectNote.get(projectId).add(note);
    }

    // Calculer la moyenne de chaque groupe de notes de projet
    Map<Long, Double> moyProject = new HashMap<>();
    for (Map.Entry<Long, List<Long>> entry : projectNote.entrySet()) {
      Long projectId = entry.getKey();
      List<Long> notes = entry.getValue();
      Double moyenne = notes.stream().mapToDouble(val -> val).average().orElse(0.0);
      moyProject.put(projectId, moyenne);
   //   projets.setMoyJury(moyenne);

    }

    return moyProject;
  }


  /* @Override
  public Map<Long, Double> calculMoyenneGeneralProjectJury() {


    List<Evaluation> evaluations = evaluationRepository.findByUser(null);
    Map<Long, List<Long>> projectNote = new HashMap<>();


    // Grouper les notes de chaque projet ensemble
    for (Evaluation evaluation : evaluations) {
      Long projectId = evaluation.getProjets().getId();
      Long note = evaluation.getNote();
      if ( !projectNote.containsKey(projectId)) {
        projectNote.put(projectId, new ArrayList<>());
      }
      projectNote.get(projectId).add(note);
    }

    // Calculer la moyenne de chaque groupe de notes de projet
    Map<Long, Double> moyProject = new HashMap<>();
    for (Map.Entry<Long, List<Long>> entry : projectNote.entrySet()) {
      Long projectId = entry.getKey();
      List<Long> notes = entry.getValue();
      Double moyenne = notes.stream().mapToDouble(val -> val).average().orElse(0.0);
      moyProject.put(projectId, moyenne);

    }

    return moyProject;
  }
*/
  @Override
  public Map<Long, Double> calculMoyenneGeneralProjectParticipant() {
    List<Evaluation> evaluations = evaluationRepository.findByCodevotant(null);
    Map<Long, List<Long>> projectNote = new HashMap<>();


    // Grouper les notes de chaque projet ensemble
    for (Evaluation evaluation : evaluations) {
      Long projectId = evaluation.getProjets().getId();
      Long note = evaluation.getNote();
      if ( !projectNote.containsKey(projectId)) {
        projectNote.put(projectId, new ArrayList<>());
      }
      projectNote.get(projectId).add(note);
    }

    // Calculer la moyenne de chaque groupe de notes de projet
    Map<Long, Double> moyProject = new HashMap<>();
    for (Map.Entry<Long, List<Long>> entry : projectNote.entrySet()) {
      Long projectId = entry.getKey();
      List<Long> notes = entry.getValue();
      Double moyenne = notes.stream().mapToDouble(val -> val).average().orElse(0.0);
      moyProject.put(projectId, moyenne);
    }

    return moyProject;
  }


}


