package com.odk02.ikavote.service.serviceImpl;

import com.odk02.ikavote.models.Criteres;
import com.odk02.ikavote.models.Evenements;
import com.odk02.ikavote.models.Projets;
import com.odk02.ikavote.repository.CriteresRepository;
import com.odk02.ikavote.repository.EvenementsRepository;
import com.odk02.ikavote.service.CriteresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CriteresServiceImpl implements CriteresService {

  @Autowired
   CriteresRepository criteresRepository;

  @Autowired
  EvenementsRepository evenementsRepository;
  @Override
  public Object ajouterCriteres(Criteres criteres) {
    if(criteresRepository.findByTitre(criteres.getTitre()) == null) {

      criteresRepository.save(criteres);

      return "criteres ajouter succès";
    }else {
      return "ce criteres existe déjà";
    }
  }

  @Override
  public Object supprimerCriteres(Long id) {
    Optional<Criteres> criteres = criteresRepository.findById(id);
    if (criteres.isPresent()) {
      return "Ce critere n'existe pas !";
    }
    else {

      criteresRepository.delete(criteres.get());
      return "Critere supprimé avec succès";
    }
  }

  @Override
  public Object ModifierCriteres(Criteres criteres, Long id) {
    Optional<Criteres> paysExiste = this.criteresRepository.findById(id);
    if (paysExiste.isEmpty()) {
      return "Critere non trouvé !";

    }
    else {
      Criteres criteresMod = criteresRepository.findById(id).get();
      criteresMod.setTitre(criteres.getTitre());
      criteresMod.setContenu(criteres.getContenu());
      criteresMod.setNote(criteres.getNote());
      criteresRepository.saveAndFlush(criteresMod);

      return "Critere modifier avec succès";
    }
  }

  @Override
  public Object afficherUnCriteres(Long id) {
    Optional<Criteres> criteres = criteresRepository.findById(id);

    if (criteres.isEmpty()) {

      return  "Cet critere n'est pas trouvée !";
    }
    else {
      return criteresRepository.findById(id).get();
    }
  }

  @Override
  public List<Criteres> afficherTousLesCriteres() {
    return criteresRepository.findAll();
  }

  @Override
  public List<Criteres> getCritersWithEvent(Long idevents) {
    Optional<Evenements> evenements = evenementsRepository.findById(idevents);
    return criteresRepository.findCritersByEvenements(evenements.get());
  }

}
