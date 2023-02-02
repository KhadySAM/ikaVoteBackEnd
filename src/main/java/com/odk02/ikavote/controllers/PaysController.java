package com.odk02.ikavote.controllers;

import com.odk02.ikavote.img.ConfigImg;
import com.odk02.ikavote.models.Pays;
import com.odk02.ikavote.repository.PaysRepository;
import com.odk02.ikavote.service.PaysService;
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
public class PaysController {

  @Autowired
  PaysService paysService;

  @Autowired
  PaysRepository paysRepository;

  // Afficher tous les pays
  @GetMapping("/getallpays")
 // @PostAuthorize("hasAuthority('SUPERADMIN')")
  public List<Pays> getAll() {

    return paysRepository.findAll();
  }

  // Afficher un pays par id
  @GetMapping("/getonepays/{id}")
 // @PostAuthorize("hasAuthority('SUPERADMIN')")
  public Object afficherPaysParId(@PathVariable Long id) {

    return paysService.afficherUn(id);
  }


  // Ajouter un pays
  @PostMapping("/ajoutpays")
 // @PostAuthorize("hasAuthority('SUPERADMIN')")
  public Object addPays(
    @Param("nom") String nom,
    @Param("initiale") String initiale,
    @Param("file") MultipartFile file) throws IOException {

    Pays pays = new Pays();
    pays.setNom(nom);
    pays.setInitiale(initiale);
    pays.setImages(ConfigImg.save(file,file.getOriginalFilename()));


    return paysService.ajouterPays(pays);

  }





  @PutMapping("/modifier/{id}")
 // @PostAuthorize("hasAuthority('SUPERADMIN')")
  public Object updatePays(@PathVariable Long id,
    @Param("nom") String nom,
    @Param("initiale") String initiale,
    @Param("file") MultipartFile file) throws IOException {

    Pays pays = new Pays();
    pays.setNom(nom);
    pays.setInitiale(initiale);
    pays.setImages(ConfigImg.save(file,file.getOriginalFilename()));

    return paysService.ModifierPays(pays, id);
  }

  @DeleteMapping("/supprime/{id}")
 // @PostAuthorize("hasAuthority('SUPERADMIN')")
  public Object delete(@PathVariable Long id) {

    return paysService.supprimerPays(id);
  }



}
