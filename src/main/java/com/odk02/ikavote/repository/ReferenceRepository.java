package com.odk02.ikavote.repository;

import com.odk02.ikavote.models.Projets;
import com.odk02.ikavote.models.Reference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReferenceRepository extends JpaRepository<Reference, Long> {

    // public Reference findByIdReference(Long id);
}
