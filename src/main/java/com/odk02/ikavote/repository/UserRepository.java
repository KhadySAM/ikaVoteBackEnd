package com.odk02.ikavote.repository;


import com.odk02.ikavote.models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);




  @Query(value = "SELECT users.* FROM user_roles,users,roles,pays WHERE users.id=user_roles.user_id AND users.id_pays=pays.id AND roles.id=user_roles.role_id AND roles.id=:id",nativeQuery = true)
  List<User> findByAdminRole(@Param("id")Long id);

}
