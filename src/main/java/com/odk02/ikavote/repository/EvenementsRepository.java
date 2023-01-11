package com.odk02.ikavote.repository;

import com.odk02.ikavote.models.Evenements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvenementsRepository extends JpaRepository <Evenements , Long> {

    public Evenements findByLibelle(String libelle);
}
