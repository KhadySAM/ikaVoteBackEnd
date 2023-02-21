package com.odk02.ikavote.service.serviceImpl;


import com.odk02.ikavote.models.*;


import com.odk02.ikavote.repository.*;

import com.odk02.ikavote.service.EvaluationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;


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

  @Autowired
  private EvenementsRepository evenementsRepository;


  public Object addEvaluation(Long id_codevotant, Long id_critere, Long id_projet, Long note) {

    Criteres criteres = criteresRepository.findById(id_critere).get();
    Projets projets = projetsRepository.findById(id_projet).get();
    Codevotant codevotant = codevotantRepository.findById(id_codevotant).get();
    // Vérifie si le critere et le projet n'appartiennent à un même évenements
    if (Objects.equals(projets.getEvenements().getId(), criteres.getEvenements().getId())) {
      // Vérifie si un user a déjà évalué ce projet
      if (evaluationRepository.existsByCodevotantAndProjetsAndCriteres(codevotant, projets, criteres)) {
        return "Vous avez déjà évalué ce projet sur ce critere";
      } else {

        Evaluation evaluation = new Evaluation();

        evaluation.setCriteres(criteres);
        evaluation.setProjets(projets);
        evaluation.setCodevotant(codevotant);
        evaluation.setNote(note);

        evaluationRepository.save(evaluation);
      }
      return "Note envoyer";
    } else {
      return "Le critere et le projet n'appartiennent pas à un même évenement ! ";
    }
  }


  // ======================================================================

  public Object addEvaluationJury(Long id_critere, Long id_projet, Long id_user, Long note) {

    Criteres criteres = criteresRepository.findById(id_critere).get();
    Projets projets = projetsRepository.findById(id_projet).get();
    User user = userRepository.findById(id_user).get();


    Date d = new Date();
    LocalDate lt = LocalDate.now();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    String dateHeure = sdf.format(d);




        if (Objects.equals(projets.getEvenements().getId(), criteres.getEvenements().getId())) {
          // Vérifie si un user a déjà évalué ce projet
          if (projets.getEvenements().getDateDebut().before(d)){
            if (projets.getEvenements().getDateFin().after(d)){
              if (evaluationRepository.existsByUserAndProjetsAndCriteres(user, projets, criteres)) {
                return "Vous avez déjà évalué ce projet sur ce critere";
              } else {

                Evaluation evaluation = new Evaluation();

                evaluation.setCriteres(criteres);
                evaluation.setProjets(projets);
                evaluation.setUser(user);
                evaluation.setNote(note);
                evaluationRepository.save(evaluation);

              }
              return "Note envoyer";
            } else {
              return "Les vote sont cloturé veuillez attendre les résultats !";
            }
          } else return "Les votes ne sont pas commencé !";

        } else return "Le critere et le projet n'appartiennent pas à un même évenement";



  }


  @Override
  public Map<Long, Double> calculMoyenneGeneralProjectVotant() {


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
      DecimalFormat df = new DecimalFormat("#.##");
      df.format(moyenne);
      moyProject.put(projectId, moyenne);
   //   projets.setMoyJury(moyenne);

    }

    return moyProject;
  }


  @Override
  public Map<Long, Double> calculMoyenneGeneralProjectJury() {
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

  @Override
  public Long nbreJury() {
    return evaluationRepository.nbreJury();
  }

  @Override
  public Map<Long, Double> calculMoyenneGeneralProjectVotantKura(Long eventId) {
    //======================================= iciiiiiiiiiiiiiiiiiiiiiiiiiiii22222222222222222222 ============================



    List<Evaluation> evaluations = evaluationRepository.findByUser(null);

    Evenements evenements=evenementsRepository.findById(eventId).get();

    List<Evaluation> evaluationList=new ArrayList<>();
    for (Evaluation ev:evaluations
    ) {
      if(ev.getProjets().getEvenements().equals(evenements)){
        evaluationList.add(ev);
      }

    }



    Map<Long, List<Long>> projectNote = new HashMap<>();


    // Grouper les notes de chaque projet ensemble
    for (Evaluation evaluation : evaluationList) {
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

    moyProject.forEach((k,v)->{
      Projets p=projetsRepository.findById(k).get();
      p.setMoyParcitipant(v);
      projetsRepository.save(p);
    });

    return moyProject;
  }


  @Override
  public Map<Long, Double> calculMoyenneGeneralProjectJuryKura(Long eventId) {

    //======================================= iciiiiiiiiiiiiiiiiiiiiiiiiiiii ============================



    List<Evaluation> evaluations = evaluationRepository.findByCodevotant(null);

    Evenements evenements=evenementsRepository.findById(eventId).get();

    List<Evaluation> evaluationList=new ArrayList<>();
    for (Evaluation ev:evaluations
         ) {
      if(ev.getProjets().getEvenements().equals(evenements)){
        evaluationList.add(ev);
      }

    }



    Map<Long, List<Long>> projectNote = new HashMap<>();


    // Grouper les notes de chaque projet ensemble
    for (Evaluation evaluation : evaluationList) {
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
      DecimalFormat df = new DecimalFormat("#.##");
      df.format(moyenne);
      moyProject.put(projectId, moyenne);

    }

    moyProject.forEach((k,v)->{
        Projets p=projetsRepository.findById(k).get();
        p.setMoyJury(v);

       /* p.setMoyTotal( ( (p.getMoyJury()* p.getEvenements().getCoefficientJury()) /100) + ( (p.getMoyParcitipant()* p.getEvenements().getCoefficientUser()) /100) );

        projetsRepository.save(p);*/

        double moyTotal = ((p.getMoyJury() * p.getEvenements().getCoefficientJury()) / 100)
          + ((p.getMoyParcitipant() * p.getEvenements().getCoefficientUser()) / 100);
        p.setMoyTotal(moyTotal);


        String moyTotalFormatted = String.format("%.2f", p.getMoyTotal());
      projetsRepository.save(p);
        System.out.println("Moyenne totale : " + moyTotalFormatted);

      });

    return moyProject;
  }


}


