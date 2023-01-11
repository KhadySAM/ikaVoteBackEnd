package com.odk02.ikavote.service;

import com.odk02.ikavote.models.Authentification;


import java.util.List;

public interface AuthentificationService {

  Object ajouterTypesAuth(Authentification authentification);

  Object supprimerTypesAuth(Long id);

  Object ModifierTypesAuth(Authentification auth, Long id);

  Object afficherUnTypesAuth(Long id);

  List<Authentification> afficherTousLesTypesAuth();
}
