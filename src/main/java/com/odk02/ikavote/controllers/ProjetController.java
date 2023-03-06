package com.odk02.ikavote.controllers;

import com.odk02.ikavote.img.ConfigImg;
import com.odk02.ikavote.models.Evenements;
import com.odk02.ikavote.models.Projets;
import com.odk02.ikavote.repository.EvenementsRepository;
import com.odk02.ikavote.service.ProjetsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import java.util.List;

@CrossOrigin(origins ={ "http://localhost:4200/", "http://localhost:8100/" }, maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/api/auth")
public class ProjetController {

    @Autowired
    ProjetsServices projetsServices;

  @Autowired
  EvenementsRepository evenementsRepository;


  @GetMapping("/projets/{id}")
  public Projets getProjetsById(@PathVariable Long id) {
    return projetsServices.getProjetsById(id);
  }

  // Ajouter un projet
  @GetMapping("/projetbyevents/{idEvents}")
  public List<Projets> getEventsByprojectId(@PathVariable Long idEvents) {

    return projetsServices.getProjectsWithEvent(idEvents);
  }

  @PostMapping("/ajoutprojet/{idEvents}")
 // @PostAuthorize("hasAuthority('SUPERADMIN')")
  public Object addProjets( @PathVariable ("idEvents") Long idEvents,
                            @Param("libelle") String libelle,
                            @Param("description") String description,
                            @Param("file") MultipartFile file) throws IOException {

    Projets projets = new Projets();
    projets.setLibelle(libelle);
    projets.setDescription(description);
    projets.setEvenements(evenementsRepository.findById(idEvents).get());
    projets.setImages(ConfigImg.save(file,file.getOriginalFilename()));


    return projetsServices.ajouterProjets(projets);

  }

  @GetMapping("/getallprojet")
  @PostAuthorize("hasAuthority('SUPERADMIN')")
  public List<Projets> getAll() {

    return projetsServices.afficherTousLesProjets();
  }

  // Afficher un projet par id
  @GetMapping("/getoneprojet/{id}")
  @PostAuthorize("hasAuthority('SUPERADMIN')")
  public Object afficherUnEventParId(@PathVariable Long id) {

    return projetsServices.afficherProjetsParId(id);
  }

    @PutMapping("/modifierprojet/{id}")
   // @PostAuthorize("hasAuthority('SUPERADMIN')")
    public Object updatePays(@PathVariable Long id,
                             @Param("libelle") String libelle,
                             @Param("description") String description,
                             @Param("file") MultipartFile file) throws IOException {

        Projets projets = new Projets();
        projets.setLibelle(libelle);
        projets.setDescription(description);
        projets.setImages(ConfigImg.save(file,file.getOriginalFilename()));

        return projetsServices.ModifierProjets(projets, id);
    }

    @DeleteMapping("/supprimeprojet/{id}")
   // @PostAuthorize("hasAuthority('SUPERADMIN')")
    public Object deleteProjets(@PathVariable Long id) {

        return projetsServices.supprimerProjets(id);
    }

  @GetMapping("/checkProjets/{libelle}")
  public boolean checkProjets(@PathVariable("libelle") String libelle) {

    return projetsServices.existsProjets(libelle);

  }
}
