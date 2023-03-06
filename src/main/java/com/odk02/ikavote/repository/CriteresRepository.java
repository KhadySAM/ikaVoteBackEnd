package com.odk02.ikavote.repository;

import com.odk02.ikavote.models.Criteres;
import com.odk02.ikavote.models.Evenements;
import com.odk02.ikavote.models.Projets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CriteresRepository extends JpaRepository<Criteres, Long> {
 List<Criteres> findCritersByEvenements(Evenements evenements);


}
