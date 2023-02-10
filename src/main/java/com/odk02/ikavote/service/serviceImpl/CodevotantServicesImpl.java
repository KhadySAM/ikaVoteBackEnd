package com.odk02.ikavote.service.serviceImpl;

import com.odk02.ikavote.models.Codevotant;
import com.odk02.ikavote.models.Evaluation;
import com.odk02.ikavote.models.Evenements;
import com.odk02.ikavote.repository.CodevotantRepository;
import com.odk02.ikavote.repository.EvaluationRepository;
import com.odk02.ikavote.repository.EvenementsRepository;
import com.odk02.ikavote.service.CodevotantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class CodevotantServicesImpl implements CodevotantService {

  @Autowired
  CodevotantRepository codevotantRepository;

  @Autowired
  EvenementsRepository evenementsRepository;



  @Override
  public Object genererCode(Integer nbreVotant, Evenements evenements) {
    List<Codevotant> listcodevotant=new ArrayList<>();
    Random r=new Random();
    for (int i=0;i<nbreVotant;i++){
      String element="";
      Codevotant codevotant=new Codevotant();

      for (int j=0;j<3;j++){
        element+=r.nextInt(9);
      }
      codevotant.setCode(Long.valueOf(element));
      codevotant.setEvenements(evenements);
      listcodevotant.add(codevotantRepository.save(codevotant));
    }
    return listcodevotant;
  }

  @Override
  public List<Codevotant> getCodeVotantByIdEvent(Long idevents) {
    Optional<Evenements> evenements = evenementsRepository.findById(idevents);
    return codevotantRepository.findCodevotantByEvenements(evenements.get());
  }


}
