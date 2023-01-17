package com.odk02.ikavote.service;

import com.odk02.ikavote.models.Criteres;
import com.odk02.ikavote.models.Evaluation;
import com.odk02.ikavote.models.Projets;
import com.odk02.ikavote.models.User;

public interface EvaluationService {

  Object noterUnprojetParCriter(Evaluation evaluation);
}
