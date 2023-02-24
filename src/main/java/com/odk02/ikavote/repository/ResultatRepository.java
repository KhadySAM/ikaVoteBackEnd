package com.odk02.ikavote.repository;

import com.odk02.ikavote.models.Projets;
import com.odk02.ikavote.models.Questions;
import com.odk02.ikavote.models.Resultat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultatRepository extends JpaRepository<Resultat, Long> {

    public Resultat findByProjets(Long idP);
}
