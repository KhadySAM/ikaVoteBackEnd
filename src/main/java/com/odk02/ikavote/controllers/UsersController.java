package com.odk02.ikavote.controllers;

import com.odk02.ikavote.messages.request.SignupRequest;
import com.odk02.ikavote.messages.response.MessageResponse;
import com.odk02.ikavote.models.*;
import com.odk02.ikavote.repository.PaysRepository;
import com.odk02.ikavote.repository.RoleRepository;
import com.odk02.ikavote.repository.UserRepository;
import com.odk02.ikavote.security.jwt.JwtUtils;
import com.odk02.ikavote.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@CrossOrigin(value = "http://localhost:4200",maxAge = 3600,allowCredentials = "true")
@RestController
@RequestMapping("/api/auth")
public class UsersController {

  @Autowired
  UsersService usersService;

  @Autowired
  UserRepository userRepository;

  @Autowired
  PaysRepository paysRepository;
  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;


  // Supprimer user par id
  @DeleteMapping("/supprimeuser/{id}")
  public Object suppression(@PathVariable Long id) {
    return usersService.supprimerUsers(id);
  }

  @PostMapping("/inscjury/{nom}")
  public ResponseEntity<?> registerUserJury(@Valid @RequestBody SignupRequest signUpRequest, @PathVariable("nom") String nom) {

    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity
        .badRequest()
        .body(new MessageResponse("Erreur: le nom d'utilisateur est déjà pris!"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity
        .badRequest()
        .body(new MessageResponse("Erreur: l'e-mail est déjà utilisé!"));
    }



    User user = new User(signUpRequest.getUsername(),
      signUpRequest.getEmail(),
      encoder.encode(signUpRequest.getPassword()), new HashSet<>());


    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Role userRole = roleRepository.findByName(ERole.JURY)
        .orElseThrow(() -> new RuntimeException("Erreur: Role non trouvé"));
      roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
          case "admin":
            Role adminRole = roleRepository.findByName(ERole.JURY)
              .orElseThrow(() -> new RuntimeException("Erreur: Role non trouvé"));
            roles.add(adminRole);

            break;

          default:
            Role userRole = roleRepository.findByName(ERole.JURY)
              .orElseThrow(() -> new RuntimeException("Erreur: Role non trouvé"));
            roles.add(userRole);
        }
      });
    }

    user.setRoles(roles);
    Pays pays = paysRepository.findByNom(nom);
    user.setPays(pays);
    userRepository.save(user);

    return ResponseEntity.ok(new MessageResponse("Utilisateur enregistré avec succès!"));
  }

  @PostMapping("/inscadmin/{nom}")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest, @PathVariable("nom") String nom) {

    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity
        .badRequest()
        .body(new MessageResponse("Erreur: le nom d'utilisateur est déjà pris!"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity
        .badRequest()
        .body(new MessageResponse("Erreur: l'e-mail est déjà utilisé!"));
    }



    User user = new User(signUpRequest.getUsername(),
      signUpRequest.getEmail(),
      encoder.encode(signUpRequest.getPassword()), new HashSet<>());


    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Role userRole = roleRepository.findByName(ERole.ADMIN)
        .orElseThrow(() -> new RuntimeException("Erreur: Role non trouvé"));
      roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
          case "admin":
            Role adminRole = roleRepository.findByName(ERole.ADMIN)
              .orElseThrow(() -> new RuntimeException("Erreur: Role non trouvé"));
            roles.add(adminRole);

            break;

          default:
            Role userRole = roleRepository.findByName(ERole.ADMIN)
              .orElseThrow(() -> new RuntimeException("Erreur: Role non trouvé"));
            roles.add(userRole);
        }
      });
    }

    user.setRoles(roles);
    Pays pays = paysRepository.findByNom(nom);
    user.setPays(pays);
    userRepository.save(user);

    return ResponseEntity.ok(new MessageResponse("Utilisateur enregistré avec succès!"));
  }

  /*public ResponseEntity<?> registerDefaultUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity
        .badRequest()
        .body(new MessageResponse("Erreur: le nom d'utilisateur est déjà pris!"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity
        .badRequest()
        .body(new MessageResponse("Erreur: l'e-mail est déjà utilisé!"));
    }

    // Créer un nouveau compte d'utilisateur


    User user = new User(null,signUpRequest.getUsername(),
      signUpRequest.getEmail(),
      encoder.encode(signUpRequest.getPassword()),null, new HashSet<>());

    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Role userRole = roleRepository.findByName(ERole.ADMIN)
        .orElseThrow(() -> new RuntimeException("Erreur: Role non trouvé"));
      roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
          case "admin":
            Role adminRole = roleRepository.findByName(ERole.ADMIN)
              .orElseThrow(() -> new RuntimeException("Erreur: Role non trouvé"));
            roles.add(adminRole);
            break;

          default:
            Role userRole = roleRepository.findByName(ERole.ADMIN)
              .orElseThrow(() -> new RuntimeException("Erreur: Role non trouvé"));
            roles.add(userRole);
        }
      });
    }

    user.setRoles(roles);
    userRepository.save(user);

    return ResponseEntity.ok(new MessageResponse("Utilisateur enregistré avec succès!"));
  }*/




}
