package com.odk02.ikavote.service;

import com.odk02.ikavote.models.Codevotant;
import com.odk02.ikavote.models.Criteres;
import com.odk02.ikavote.models.Evaluation;
import com.odk02.ikavote.models.Evenements;

import java.util.List;

public interface CodevotantService {

  Object genererCode(Integer nbreVotant, Evenements evenements);

  List<Codevotant> getCodeVotantByIdEvent(Long idevents);

  Codevotant getCodevotantById(Long id);

 // Codevotant getCodevotantByCode(Long codevotant);

  Object getCodevotantByCode(Long code);

  List<Codevotant> afficherTousLesCodeVotant();



  // Object faireUneEvaluation(Evaluation evaluation, Codevotant codevotant);

}
