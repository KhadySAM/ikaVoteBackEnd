package com.odk02.ikavote.service.serviceImpl;


import com.odk02.ikavote.models.*;


import com.odk02.ikavote.repository.CriteresRepository;

import com.odk02.ikavote.repository.ProjetsRepository;
import com.odk02.ikavote.repository.ReferenceRepository;
import com.odk02.ikavote.service.EvaluationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class EvaluationServiceImpl implements EvaluationService {

  @Autowired
  ProjetsRepository projetsRepository;

  @Autowired
  CriteresRepository criteresRepository;

  @Autowired
  ReferenceRepository referenceRepository;

  @Override
  public Object noterUnprojetParCriter(Long id_projets, Long id_criteres, Long id_reference) {
    Evaluation evaluation = new Evaluation();

    Optional<Projets> projets = projetsRepository.findById(id_projets);

    Optional<Criteres> criteres = criteresRepository.findById(id_criteres);

    Optional<Reference> reference = referenceRepository.findById(id_criteres);

    if (reference.isEmpty()){
      return "Projets non trouvé !";
    }
    else {
      evaluation.setCriteres(evaluation.getCriteres());
      evaluation.setProjets(evaluation.getProjets());
      evaluation.setNote(evaluation.getNote());
    }
    return  "hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh";
  }

  @Override
  public Object noterUnprojetParCriter(Long id_projets, Long id_criteres, Reference reference) {
    return null;
  }


}
