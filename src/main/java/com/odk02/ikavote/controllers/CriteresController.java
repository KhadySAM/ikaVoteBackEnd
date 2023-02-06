package com.odk02.ikavote.controllers;


import com.odk02.ikavote.models.Criteres;


import com.odk02.ikavote.service.CriteresService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;



import java.util.List;

@CrossOrigin(origins ={ "http://localhost:4200/", "http://localhost:8100/" }, maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/api/auth")
public class CriteresController {

  @Autowired
  CriteresService criteresService;

  @GetMapping("/criteresbyevents/{id}")
  public List<Criteres> getCriteresByEventsId(@PathVariable Long id) {
    return criteresService.getCritersWithEvent(id);
  }

  // Ajouter un Criteres
  @PostMapping("/ajoutcriteres")
  @PostAuthorize("hasAuthority('SUPERADMIN')")
  public Object  add(@RequestBody Criteres criteres) {

    return criteresService.ajouterCriteres(criteres);
  }



  // Afficher tous les Criteres enregistrer
  @GetMapping("/getallcritere")
  @PostAuthorize("hasAuthority('SUPERADMIN')")
  public List <Criteres> getAllCriteres() {

    return criteresService.afficherTousLesCriteres();
  }

  // Afficher un Criteres par id
  @GetMapping("/getonetcriteres/{id}")
  @PostAuthorize("hasAuthority('SUPERADMIN')")
  public Object afficherCriteresParId(@PathVariable Long id) {

    return criteresService.afficherUnCriteres(id);
  }

  // Modifier un criteres par id
  @PutMapping("/modifiercriteres/{id}")
  @PostAuthorize("hasAuthority('SUPERADMIN')")
  public  Object updateCriteres(@RequestBody Criteres criteres, @PathVariable Long id) {

    return criteresService.ModifierCriteres(criteres, id);
  }

  // Supprimer un Criteres par id
  @DeleteMapping("/supprimecriteres/{id}")
  @PostAuthorize("hasAuthority('SUPERADMIN')")
  public Object deleteCritere(@PathVariable Long id) {

    return  criteresService.supprimerCriteres(id);
  }
}
