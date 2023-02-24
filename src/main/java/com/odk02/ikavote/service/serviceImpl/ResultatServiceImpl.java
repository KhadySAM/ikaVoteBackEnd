package com.odk02.ikavote.service.serviceImpl;

import com.odk02.ikavote.models.*;
import com.odk02.ikavote.repository.*;
import com.odk02.ikavote.service.ResultatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ResultatServiceImpl implements ResultatService {


    @Autowired
    private EvaluationRepository evaluationRepository;
    @Autowired
    private ProjetsRepository projetsRepository;
    @Autowired
    private EvenementsRepository evenementsRepository;
    @Autowired
    ResultatRepository resultatRepository;


    @Override
    public Map<Long, Double> calculResultatGeneralProjectVotantKura(Long eventId) {

        List<Evaluation> evaluations = evaluationRepository.findByUser(null);

        Evenements evenements=evenementsRepository.findById(eventId).get();


        List<Evaluation> evaluationList=new ArrayList<>();
        for (Evaluation ev:evaluations
        ) {
            if(ev.getProjets().getEvenements().equals(evenements)){
                evaluationList.add(ev);
            }

        }

        Map<Long, List<Long>> projectNote = new HashMap<>();

        // Grouper les notes de chaque projet ensemble
        for (Evaluation evaluation : evaluationList) {
            Long projectId = evaluation.getProjets().getId();
            Long note = evaluation.getNote();
            if ( !projectNote.containsKey(projectId)) {
                projectNote.put(projectId, new ArrayList<>());
            }
            projectNote.get(projectId).add(note);
        }

        // Calculer la moyenne de chaque groupe de notes de projet
        Map<Long, Double> moyProject = new HashMap<>();
        for (Map.Entry<Long, List<Long>> entry : projectNote.entrySet()) {
            Long projectId = entry.getKey();
            List<Long> notes = entry.getValue();
            Double moyenne = notes.stream().mapToDouble(val -> val).average().orElse(0.0);
            moyProject.put(projectId, moyenne);

        }


        moyProject.forEach((k,v)->{
            Projets p=projetsRepository.findById(k).get();
            if (resultatRepository.findByProjets(k) == null){
                Resultat resultat = new Resultat();

                resultat.setNote((v*p.getEvenements().getCoefficientUser())/100);
                resultat.setProjets(p);

                resultatRepository.save(resultat);
            }


            p.setMoyParcitipant(v);
            projetsRepository.save(p);




        });


        return moyProject;
    }

    @Override
    public Map<Long, Double> calculResultatGeneralProjectJuryKura(Long eventId) {
        return null;
    }
}
