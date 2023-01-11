package com.odk02.ikavote.repository;

import com.odk02.ikavote.models.Evenements;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvenementsRepository extends JpaRepository<Evenements , Long> {
}
