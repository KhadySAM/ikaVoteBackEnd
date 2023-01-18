package com.odk02.ikavote.service;

import com.odk02.ikavote.models.*;

public interface EvaluationService {

  Object noterUnprojetParCriter(Long id_projets, Long id_criteres, Long id_reference);

  Object noterUnprojetParCriter(Long id_projets, Long id_criteres, Reference reference);
}
