package com.odk02.ikavote.repository;

import com.odk02.ikavote.models.Criteres;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Critere extends JpaRepository<Criteres, Long> {
}
