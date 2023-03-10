package com.odk02.ikavote.repository;

import com.odk02.ikavote.models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {

  boolean existsByCodevotantAndProjetsAndCriteres(Codevotant codevotant, Projets projets, Criteres criteres);

  boolean existsByUserAndProjetsAndCriteres(User user, Projets projets, Criteres criteres);

  boolean existsByCodevotantAndProjets(Codevotant codevotant, Projets projets);

  boolean existsByUserAndProjets(User user, Projets projets);



    List<Evaluation> findByUser(User user);

   List<Evaluation> findByCodevotant(Codevotant codevotant);

  List<Evaluation> findByProjetsId(Long idProjets);

 // List<Evaluation> findByCodevotantAndProjets(Codevotant codevotant, Projets projets);




}
