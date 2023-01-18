package com.odk02.ikavote.repository;


import com.odk02.ikavote.models.Criteres;
import com.odk02.ikavote.models.Projets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjetsRepository extends JpaRepository  <Projets, Long> {

    public Projets findByLibelle(String libelle);

    //   public Projets findByIdProjets(Long id);
}
