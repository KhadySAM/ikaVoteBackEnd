package com.odk02.ikavote.repository;

import com.odk02.ikavote.models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {

  boolean existsByCodevotantAndProjetsAndCriteres(Codevotant codevotant, Projets projets, Criteres criteres);

  boolean existsByUserAndProjetsAndCriteres(User user, Projets projets, Criteres criteres);

  List<Evaluation> findByUserAndProjets(User user,Projets projets);

 List<Evaluation> findByUser(User user);

 List<Evaluation> findByCodevotant(Codevotant codevotant);


  List<Evaluation> findByProjets(Projets projets);

  @Query(value = "SELECT COUNT(DISTINCT evaluation.id_user) FROM `evaluation`",nativeQuery = true)
  Long nbreJury();

  /*@Query(value = "SELECT * FROM Evaluation JOIN Projets ON evaluation.id_projet = projets.id JOIN Evenements  ON projets.evenements_id = evenements.id WHERE evenements.id = :eventId",nativeQuery = true)
  List<Evaluation> findByEventId( Long eventId);
*/




}
