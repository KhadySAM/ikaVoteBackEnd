package com.odk02.ikavote.service;

import com.odk02.ikavote.models.Evaluation;
import com.odk02.ikavote.models.Projets;
import com.odk02.ikavote.models.Resultat;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


public interface ResultatService {

  public List<Resultat> getResultatsParEvenement(Long idEvent);
}
