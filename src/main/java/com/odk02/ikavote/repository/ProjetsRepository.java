package com.odk02.ikavote.repository;


import com.odk02.ikavote.models.Criteres;
import com.odk02.ikavote.models.Evenements;
import com.odk02.ikavote.models.Projets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjetsRepository extends JpaRepository  <Projets, Long> {

    public Projets findByLibelle(String libelle);

  List<Projets> findProjetsByEvenements(Evenements evenements);

  List<Projets> findByEvenementsId(Long idEvents);

  //List<Projets> findByEvenements(Evenements evenements);

}
