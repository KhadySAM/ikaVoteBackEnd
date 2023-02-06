package com.odk02.ikavote.service;


import com.odk02.ikavote.models.Evenements;
import com.odk02.ikavote.models.Projets;

import java.util.List;

public interface ProjetsServices {

  Object ajouterProjets(Projets projets);

  Object supprimerProjets(Long id);

  Object ModifierProjets(Projets projets, Long id);

  Object afficherProjetsParId(Long id);

  List<Projets> afficherTousLesProjets();

  List<Projets> getProjectsWithEvent(Long idevents);

  Projets getProjetsById(Long id);

}
