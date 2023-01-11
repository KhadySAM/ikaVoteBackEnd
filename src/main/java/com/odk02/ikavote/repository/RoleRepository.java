package com.odk02.ikavote.repository;


import com.odk02.ikavote.models.ERole;
import com.odk02.ikavote.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
