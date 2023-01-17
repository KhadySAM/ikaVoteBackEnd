package com.odk02.ikavote.service.serviceImpl;


import com.odk02.ikavote.models.Criteres;
import com.odk02.ikavote.models.Evaluation;


import com.odk02.ikavote.repository.EvaluationRepository;

import com.odk02.ikavote.service.EvaluationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class EvaluationServiceImpl implements EvaluationService {


  @Override
  public Object noterUnprojetParCriter(Evaluation evaluation) {
    return null;
  }
}
