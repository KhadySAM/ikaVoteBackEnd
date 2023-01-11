package com.odk02.ikavote.controllers;

import com.odk02.ikavote.models.Authentification;
import com.odk02.ikavote.service.AuthentificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/superadmin")
@RestController
public class TypeAuthController {

  @Autowired
  AuthentificationService authentificationService;


  // Afficher tous les types d'authentification enregistrer
  @PostMapping("/ajouttypesauth")
  @PostAuthorize("hasAuthority('SUPERADMIN')")
  public Object  add(@RequestBody Authentification authentification) {

    return authentificationService.ajouterTypesAuth(authentification);
  }



  // Afficher tous les types d'authentification enregistrer
  @GetMapping("/getalltype")
  @PostAuthorize("hasAuthority('SUPERADMIN')")
  public List <Authentification> getAll() {

    return authentificationService.afficherTousLesTypesAuth();
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
