package com.odk02.ikavote.service;

import com.odk02.ikavote.models.Evenements;


import java.util.List;

public interface EvenementsService {

  Object ajouterEvenements(Evenements evenements);

  Object supprimerEvenements(Long id);

  Object ModifierEvenements(Evenements evenements, Long id);

  Object afficherEvenementsParId(Long id);

  List<Evenements> afficherTousLesEvenements();

//  Evenements getEventWithProjects(Long eventsid);
}
