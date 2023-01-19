package com.odk02.ikavote.service;

import com.odk02.ikavote.models.*;

public interface EvaluationService {

  Object addEvaluationJury(Long id_user, Long id_critere, Long id_projet, Long note);

  Object addEvaluation(Long id_codevotant, Long id_critere, Long id_projet, Long note);
}
