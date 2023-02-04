package com.odk02.ikavote.controllers;

import com.odk02.ikavote.img.ConfigImg;
import com.odk02.ikavote.models.Authentification;
import com.odk02.ikavote.models.Pays;
import com.odk02.ikavote.repository.AuthentificationRepository;
import com.odk02.ikavote.service.AuthentificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@CrossOrigin(value = "http://localhost:4200",maxAge = 3600,allowCredentials = "true")
@RestController
@RequestMapping("/api/auth")
public class TypeAuthController {

  @Autowired
  AuthentificationService authentificationService;

  @Autowired
  AuthentificationRepository authentificationRepository;


  @PostMapping("/ajouttypesauth")
  // @PostAuthorize("hasAuthority('SUPERADMIN')")
  public Object  add(@Param("libelle") String libelle) {

    Authentification authentification = new Authentification();

    authentification.setLibelle(libelle);

    return authentificationService.ajouterTypesAuth(authentification);
  }



  // Afficher tous les types d'authentification enregistrer
  @GetMapping("/getalltype")
  //@PostAuthorize("hasAuthority('SUPERADMIN')")
  public List <Authentification> getAll() {

    return authentificationRepository.findAll();
  }

  // Afficher un types d'authentification par id
  @GetMapping("/getonetypeauth/{id}")
  @PostAuthorize("hasAuthority('SUPERADMIN')")
  public Object afficherTypeAuthParId(@PathVariable Long id) {

    return authentificationService.afficherUnTypesAuth(id);
  }

  // Modifier un types d'authentification par id
  @PutMapping("/modifierauth/{id}")
  @PostAuthorize("hasAuthority('SUPERADMIN')")
  public  Object updateTypeAuth(@RequestBody Authentification authentification, @PathVariable Long id) {

    return authentificationService.ModifierTypesAuth(authentification, id);
  }

  // Supprimer un types d'authentification par id
  @DeleteMapping("/supprimeauth/{id}")
  @PostAuthorize("hasAuthority('SUPERADMIN')")
  public Object deleteTypeAuth(@PathVariable Long id) {

    return  authentificationService.supprimerTypesAuth(id);
  }

}
