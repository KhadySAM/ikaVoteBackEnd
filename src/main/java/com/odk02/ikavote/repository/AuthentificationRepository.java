package com.odk02.ikavote.repository;

import com.odk02.ikavote.models.Authentification;
import com.odk02.ikavote.models.Pays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthentificationRepository extends JpaRepository<Authentification, Long> {

  public Authentification findByLibelle(String libelle);

  Boolean existsAuthentificationBylibelle(String libelle);
}
