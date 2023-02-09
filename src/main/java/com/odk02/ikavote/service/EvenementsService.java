package com.odk02.ikavote.service;

import com.odk02.ikavote.models.Evenements;


import java.util.List;
import java.util.Map;

public interface EvenementsService {

  Object ajouterEvenements(Evenements evenements);

  Object supprimerEvenements(Long id);

  Object ModifierEvenements(Evenements evenements, Long id);

  Object afficherEvenementsParId(Long id);

  List<Evenements> afficherTousLesEvenements();

  List<Evenements> getEventWithProjects(Long idprojet);

  Evenements getEvenementsById(Long id);

 // Map<Long, Double> calculateAverageTotalForProjects(Long eventId);

  double calculerMoyenneTotaleDeTousLesProjets();
}
