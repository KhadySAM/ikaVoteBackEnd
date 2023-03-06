package com.odk02.ikavote.service;

import com.odk02.ikavote.models.Pays;
import com.odk02.ikavote.models.Role;
import com.odk02.ikavote.models.User;

import java.util.List;


public interface PaysService {

  Object ajouterPays(Pays pays);
  Object supprimerPays(Long id);
  Object ModifierPays(Pays pays, Long id);

  Object afficherUn(Long id);

  List<Pays> afficherTousLesPays();

  Boolean existsByNomPays(String nom);

}
