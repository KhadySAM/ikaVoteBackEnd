package com.odk02.ikavote.service.serviceImpl;

import com.odk02.ikavote.models.User;
import com.odk02.ikavote.repository.AuthentificationRepository;
import com.odk02.ikavote.repository.UserRepository;
import com.odk02.ikavote.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public Object supprimerUsers(Long id) {
    Optional<User> user = userRepository.findById(id);
    if (!user.isPresent()) {
      return "Ce user n'existe pa !";
    }
    else {
      userRepository.delete(user.get());
      return "utilisateur supprimer avec succès !";
    }
  }

/* @Override
  public User AfficherAdmin(Long id) {
    return userRepository.findByAdminRole(id);
  }*/


}
