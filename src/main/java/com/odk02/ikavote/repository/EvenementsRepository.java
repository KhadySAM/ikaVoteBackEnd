package com.odk02.ikavote.repository;

import com.odk02.ikavote.models.Evenements;
import com.odk02.ikavote.models.Projets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvenementsRepository extends JpaRepository <Evenements , Long> {

    public Evenements findByLibelle(String libelle);

  List<Evenements> findEvenementByProjets(Projets projets);

  // Evenements findByCodevotant(Long code);


}
