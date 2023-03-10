package com.odk02.ikavote.controllers;



import com.odk02.ikavote.messages.request.LoginRequest;
import com.odk02.ikavote.messages.request.SignupRequest;

import com.odk02.ikavote.messages.response.JwtResponse;
import com.odk02.ikavote.messages.response.MessageResponse;
import com.odk02.ikavote.models.ERole;

import com.odk02.ikavote.models.Role;
import com.odk02.ikavote.models.User;
import com.odk02.ikavote.repository.RoleRepository;
import com.odk02.ikavote.repository.UserRepository;
import com.odk02.ikavote.security.jwt.JwtUtils;
import com.odk02.ikavote.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/*@CrossOrigin(value = "http://localhost:4200",maxAge = 3600,allowCredentials = "true")*/
@CrossOrigin(origins ={ "http://localhost:4200/", "http://localhost:8100/" }, maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    List<String> roles = userDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());

    return ResponseEntity.ok(new JwtResponse(jwt,
                         userDetails.getId(),
                         userDetails.getUsername(),
                         userDetails.getEmail(),
                         roles));
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {

    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity
        .badRequest()
        .body(new MessageResponse("Erreur: le nom d'utilisateur est d??j?? pris!"));
    }

//    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
//      return ResponseEntity
//        .badRequest()
//        .body(new MessageResponse("Erreur: l'e-mail est d??j?? utilis??!"));
//    }



    User user = new User(signUpRequest.getUsername(),
      signUpRequest.getEmail(),
      encoder.encode(signUpRequest.getPassword()), new HashSet<>());


    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Role userRole = roleRepository.findByName(ERole.USER)
        .orElseThrow(() -> new RuntimeException("Erreur: Role non trouv??"));
      roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
          case "admin":
            Role adminRole = roleRepository.findByName(ERole.ADMIN)
              .orElseThrow(() -> new RuntimeException("Erreur: Role non trouv??"));
            roles.add(adminRole);

            break;
          case "jury":
            Role modRole = roleRepository.findByName(ERole.JURY)
              .orElseThrow(() -> new RuntimeException("Erreur: Role non trouv??"));
            roles.add(modRole);

            break;

          case "superadmin":
            Role superadminRole = roleRepository.findByName(ERole.SUPERADMIN)
              .orElseThrow(() -> new RuntimeException("Erreur: Role non trouv??"));
            roles.add(superadminRole);
            break;

          default:
            Role userRole = roleRepository.findByName(ERole.USER)
              .orElseThrow(() -> new RuntimeException("Erreur: Role non trouv??"));
            roles.add(userRole);
        }
      });
    }

    user.setRoles(roles);
    userRepository.save(user);

    return ResponseEntity.ok(new MessageResponse("Utilisateur enregistr?? avec succ??s!"));
  }

  public ResponseEntity<?> registerDefaultUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity
        .badRequest()
        .body(new MessageResponse("Erreur: le nom d'utilisateur est d??j?? pris!"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity
        .badRequest()
        .body(new MessageResponse("Erreur: l'e-mail est d??j?? utilis??!"));
    }

    // Cr??er un nouveau compte d'utilisateur


    User user = new User(null,signUpRequest.getUsername(),
      signUpRequest.getEmail(),
      encoder.encode(signUpRequest.getPassword()),null, new HashSet<>());

    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Role userRole = roleRepository.findByName(ERole.USER)
        .orElseThrow(() -> new RuntimeException("Erreur: Role non trouv??"));
      roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
          case "admin":
            Role adminRole = roleRepository.findByName(ERole.ADMIN)
              .orElseThrow(() -> new RuntimeException("Erreur: Role non trouv??"));
            roles.add(adminRole);

            break;
          case "jury":
            Role modRole = roleRepository.findByName(ERole.JURY)
              .orElseThrow(() -> new RuntimeException("Erreur: Role non trouv??"));
            roles.add(modRole);

            break;

          case "superadmin":
            Role superadminRole = roleRepository.findByName(ERole.SUPERADMIN)
              .orElseThrow(() -> new RuntimeException("Erreur: Role non trouv??"));
            roles.add(superadminRole);
            break;

          default:
            Role userRole = roleRepository.findByName(ERole.USER)
              .orElseThrow(() -> new RuntimeException("Erreur: Role non trouv??"));
            roles.add(userRole);
        }
      });
    }

    user.setRoles(roles);
    userRepository.save(user);

    return ResponseEntity.ok(new MessageResponse("Utilisateur enregistr?? avec succ??s!"));
  }

  @GetMapping("/getalluser")
  //@PostAuthorize("hasAuthority('SUPERADMIN')")
  public List<User> getAll() {

    return userRepository.findAll();
  }
  @GetMapping("/userByRole/{id}")
  public List<User> getUserByRole(@PathVariable Long id){
    return userRepository.findByAdminRole(id);
  }

}
