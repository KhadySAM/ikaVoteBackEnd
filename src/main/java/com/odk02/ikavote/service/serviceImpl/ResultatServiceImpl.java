package com.odk02.ikavote.service.serviceImpl;


import com.odk02.ikavote.models.Evaluation;
import com.odk02.ikavote.models.Evenements;
import com.odk02.ikavote.models.Projets;
import com.odk02.ikavote.models.Resultat;
import com.odk02.ikavote.repository.EvaluationRepository;
import com.odk02.ikavote.repository.ProjetsRepository;
import com.odk02.ikavote.repository.ResultatRepository;
import com.odk02.ikavote.service.ResultatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ResultatServiceImpl implements ResultatService {

  @Autowired
  private ProjetsRepository projetsRepository;
  @Autowired
  private ResultatRepository resultatRepository;
  @Override
  public List<Resultat> getResultatsParEvenement(Long idEvent) {
    Evenements evenements = new Evenements();
    evenements.setId(idEvent);

    List<Projets> allprojets = projetsRepository.findProjetsByEvenements(evenements);
    List<Resultat> resultats = new ArrayList<>();

    for (Projets projets : allprojets) {
      List<Resultat> resultatsDuProjet = resultatRepository.findByProjets(projets);
      resultats.addAll(resultatsDuProjet);
    }

    return resultats;
  }
}
