package com.odk02.ikavote.service;

import org.springframework.stereotype.Service;

import java.util.Map;


public interface ResultatService {

    Map<Long, Double> calculResultatGeneralProjectVotantKura(Long eventId);

    Map<Long, Double> calculResultatGeneralProjectJuryKura(Long eventId);
}
