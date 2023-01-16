package com.odk02.ikavote.repository;

import com.odk02.ikavote.models.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionsRepository extends JpaRepository<Questions, Long> {
}
