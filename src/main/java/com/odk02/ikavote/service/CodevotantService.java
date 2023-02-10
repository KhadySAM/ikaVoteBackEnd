package com.odk02.ikavote.service;

import com.odk02.ikavote.models.Codevotant;
import com.odk02.ikavote.models.Criteres;
import com.odk02.ikavote.models.Evaluation;
import com.odk02.ikavote.models.Evenements;

import java.util.List;

public interface CodevotantService {

  Object genererCode(Integer nbreVotant, Evenements evenements);

  List<Codevotant> getCodeVotantByIdEvent(Long idevents);

  // Object faireUneEvaluation(Evaluation evaluation, Codevotant codevotant);

}
