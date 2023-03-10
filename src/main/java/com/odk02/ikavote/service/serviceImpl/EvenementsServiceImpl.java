package com.odk02.ikavote.service.serviceImpl;

import com.odk02.ikavote.models.Evenements;
import com.odk02.ikavote.models.Projets;
import com.odk02.ikavote.repository.EvaluationRepository;
import com.odk02.ikavote.repository.EvenementsRepository;
import com.odk02.ikavote.repository.ProjetsRepository;
import com.odk02.ikavote.service.CodevotantService;
import com.odk02.ikavote.service.EvaluationService;
import com.odk02.ikavote.service.EvenementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EvenementsServiceImpl implements EvenementsService {

  @Autowired
  private EvenementsRepository evenementsRepository;
  @Autowired
  private ProjetsRepository projetsRepository;

  @Autowired
  private EvaluationRepository evaluationRepository;

  @Autowired
  private EvaluationService evaluationService;

  @Autowired
  private CodevotantService codevotantService;

  @Override
  public Object ajouterEvenements(Evenements evenements) {

    if (evenementsRepository.findByLibelle(evenements.getLibelle()) == null) {


      codevotantService.genererCode(evenements.getNbreVotant(), evenementsRepository.save(evenements));


      return "Evenements ajouter avec succès";
    } else {
      return "Cet evenements existe déjà";
    }
  }

  @Override
  public Object supprimerEvenements(Long id) {

    Optional<Evenements> evenements = evenementsRepository.findById(id);
    if (!evenements.isPresent()) {
      return "Ce Evenements n'existe pas !";
    } else {

      evenementsRepository.delete(evenements.get());
      return "Evenements supprimé avec succès";
    }
  }

  @Override
  public Object ModifierEvenements(Evenements evenements, Long id) {
    Optional<Evenements> eventsExiste = this.evenementsRepository.findById(id);
    if (eventsExiste.isEmpty()) {
      return "Evenements non trouvé !";
    } else {
      Evenements eventsMod = evenementsRepository.findById(id).get();
      eventsMod.setLibelle(evenements.getLibelle());
      eventsMod.setDateDebut(evenements.getDateDebut());
      eventsMod.setDateFin(evenements.getDateFin());
      eventsMod.setNbreVotant(evenements.getNbreVotant());
      eventsMod.setBareme(evenements.getBareme());
      eventsMod.setCoefficientJury(evenements.getCoefficientJury());
      eventsMod.setCoefficientUser(evenements.getCoefficientJury());


      eventsMod.setImages(evenements.getImages());

      evenementsRepository.saveAndFlush(eventsMod);

      return "Evenements modifier avec succès";
    }
  }

  @Override
  public Evenements modifier(Evenements evenements, Long id) {
    Evenements evenements1 = evenementsRepository.findById(id).get();
    evenements1.setLibelle(evenements.getLibelle());
    evenements1.setDateDebut(evenements.getDateDebut());
    evenements1.setDateFin(evenements.getDateFin());
    evenements1.setNbreVotant(evenements.getNbreVotant());
    evenements1.setBareme(evenements.getBareme());
    evenements1.setCoefficientJury(evenements.getCoefficientJury());
    evenements1.setCoefficientUser(evenements.getCoefficientJury());
    return evenementsRepository.saveAndFlush(evenements1);
  }

  @Override
  public Object afficherEvenementsParId(Long id) {

    Optional<Evenements> evenements = evenementsRepository.findById(id);

    if (evenements.isEmpty()) {

      return "Cet evenements n'est pas trouvée !";
    } else {
      return evenementsRepository.findById(id).get();
    }
  }

  @Override
  public List<Evenements> afficherTousLesEvenements() {

    return evenementsRepository.findAll();
  }

  @Override
  public List<Evenements> getEventWithProjects(Long idprojet) {
    Optional<Projets> projets = projetsRepository.findById(idprojet);
    return evenementsRepository.findEvenementByProjets(projets.get());
  }

  @Override
  public Evenements getEvenementsById(Long id) {
    return evenementsRepository.findById(id).orElse(null);

  }

  @Override
  public double calculerMoyenneTotaleDeTousLesProjets() {
    return 0;
  }

  @Override
  public Boolean existsByNom(String libelle) {
    return evenementsRepository.existsByLibelle(libelle);
  }



/*  @Override
  public Evenements avoirEventParCodeVotant(Long code) {
    return evenementsRepository.findEvenementByCodevotant(code);
  }*/

}
