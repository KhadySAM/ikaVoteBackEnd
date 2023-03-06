package com.odk02.ikavote.repository;

import com.odk02.ikavote.models.Pays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaysRepository extends JpaRepository<Pays, Long> {

   Pays findByNom(String nom);

   Boolean existsByNom(String nom);






}
