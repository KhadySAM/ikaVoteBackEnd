package com.odk02.ikavote.service;

import com.odk02.ikavote.models.User;

import java.util.List;

public interface UsersService {

  Object supprimerUsers(Long id);

// User AfficherAdmin(Long id);

  boolean checkUserByEmail(String email);

  boolean checkUserByUsername(String username);
}
