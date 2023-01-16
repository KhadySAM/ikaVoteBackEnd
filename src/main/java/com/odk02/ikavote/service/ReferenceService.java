package com.odk02.ikavote.service;

import com.odk02.ikavote.models.Evenements;
import com.odk02.ikavote.models.Projets;
import com.odk02.ikavote.models.Reference;

import java.util.List;

public interface ReferenceService {

  Object generCode(Integer nbreVotant, Evenements evenements);


}
