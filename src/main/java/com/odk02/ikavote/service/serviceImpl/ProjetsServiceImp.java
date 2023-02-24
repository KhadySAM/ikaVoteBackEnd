package com.odk02.ikavote.service.serviceImpl;

import com.odk02.ikavote.models.Evaluation;
import com.odk02.ikavote.models.Evenements;
import com.odk02.ikavote.models.Pays;
import com.odk02.ikavote.models.Projets;
import com.odk02.ikavote.repository.EvenementsRepository;
import com.odk02.ikavote.repository.ProjetsRepository;
import com.odk02.ikavote.service.ProjetsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjetsServiceImp implements ProjetsServices {

  @Autowired
  private ProjetsRepository projetsRepository;

  @Autowired
  private EvenementsRepository evenementsRepository;

  @Override
  public Object ajouterProjets(Projets projets) {

    if(projetsRepository.findByLibelle(projets.getLibelle()) != null) {

      projetsRepository.save(projets);

      return "Projet ajouter succès";
    }else {
      return "Cet projets existe déjà";
    }
  }

  @Override
  public Object supprimerProjets(Long id) {

    Optional<Projets> projets = projetsRepository.findById(id);
    if (projets.isPresent()) {
      return "Ce projet n'existe pas !";
    }
    else {

      projetsRepository.delete(projets.get());
      return "Projet supprimé avec succès";
    }
  }

  @Override
  public Object ModifierProjets(Projets projets, Long id) {

    Optional<Projets> projetsExiste = this.projetsRepository.findById(id);
    if (projetsExiste.isEmpty()) {
      return "Projet non trouvé !";
    }
    else {
      Projets projetsMod = projetsRepository.findById(id).get();
      projetsMod.setLibelle(projets.getLibelle());
      projetsMod.setDescription(projets.getDescription());
      projetsMod.setImages(projets.getImages());
      projetsRepository.saveAndFlush(projetsMod);

      return "Projet modifier avec succès";
    }
  }

  @Override
  public Object afficherProjetsParId(Long id) {

    Optional<Projets> projets = projetsRepository.findById(id);

    if (projets.isEmpty()) {

      return  "Cet projets n'est pas trouvée !";
    }
    else {
      return projetsRepository.findById(id).get();
    }
  }

  @Override
  public List<Projets> afficherTousLesProjets() {

    return projetsRepository.findAll();
  }

  @Override
  public List<Projets> getProjectsWithEvent(Long idevents) {
    Optional<Evenements> evenements = evenementsRepository.findById(idevents);
    return projetsRepository.findProjetsByEvenements(evenements.get());
  }

  @Override
  public Projets getProjetsById(Long id) {
    return projetsRepository.findById(id).orElse(null);
  }




}
