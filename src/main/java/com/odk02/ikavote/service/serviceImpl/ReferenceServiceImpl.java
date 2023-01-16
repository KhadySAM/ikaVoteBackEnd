package com.odk02.ikavote.service.serviceImpl;

import com.odk02.ikavote.models.Evenements;
import com.odk02.ikavote.models.Reference;
import com.odk02.ikavote.repository.ReferenceRepository;
import com.odk02.ikavote.service.ReferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ReferenceServiceImpl implements ReferenceService {

  @Autowired
  ReferenceRepository referenceRepository;


  @Override
  public Object generCode(Integer nbreVotant, Evenements evenements) {

    List<Reference> listreferences=new ArrayList<>();
    Random r=new Random();
    for (int i=0;i<nbreVotant;i++){
      String element="";
      Reference reference=new Reference();

      for (int j=0;j<3;j++){
        element+=r.nextInt(9);
      }
      reference.setCode(Long.valueOf(element));
      reference.setEvenements(evenements);
      listreferences.add(referenceRepository.save(reference));
    }
    return listreferences;
  }
}
