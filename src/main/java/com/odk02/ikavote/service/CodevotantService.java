package com.odk02.ikavote.service;

import com.odk02.ikavote.models.Codevotant;
import com.odk02.ikavote.models.Evaluation;
import com.odk02.ikavote.models.Evenements;

public interface CodevotantService {

  Object genererCode(Integer nbreVotant, Evenements evenements);

  // Object faireUneEvaluation(Evaluation evaluation, Codevotant codevotant);

}
