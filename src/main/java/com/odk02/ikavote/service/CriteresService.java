package com.odk02.ikavote.service;

import com.odk02.ikavote.models.Criteres;
import com.odk02.ikavote.models.Pays;

import java.util.List;

public interface CriteresService {

  Object ajouterCriteres(Criteres criteres);
  Object supprimerCriteres(Long id);
  Object ModifierCriteres(Criteres criteres, Long id);

  Object afficherUnCriteres(Long id);

  List<Criteres> afficherTousLesCriteres();
}
