package com.odk02.ikavote.repository;

import com.odk02.ikavote.models.Codevotant;
import com.odk02.ikavote.models.Evenements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodevotantRepository extends JpaRepository<Codevotant, Long> {

  public Evenements findByCode(Long code);
}
