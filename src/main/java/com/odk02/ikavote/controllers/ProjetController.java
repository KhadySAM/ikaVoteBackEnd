package com.odk02.ikavote.controllers;

import com.odk02.ikavote.img.ConfigImg;
import com.odk02.ikavote.models.Projets;
import com.odk02.ikavote.service.ProjetsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import java.util.List;

public class ProjetController {

    @Autowired
    ProjetsServices projetsServices;


    // Afficher tous les evenements
    @GetMapping("/getallpays")
    @PostAuthorize("hasAuthority('ADMIN')")
    public List<Projets> getAll() {

        return projetsServices.afficherTousLesProjets();
    }

    // Afficher un pays par id
    @GetMapping("/getonepays/{id}")
    @PostAuthorize("hasAuthority('ADMIN')")
    public Object afficherUnEventParId(@PathVariable Long id) {

        return projetsServices.afficherProjetsParId(id);
    }


    // Ajouter un pays
    @PostMapping("/ajoutpays")
    @PostAuthorize("hasAuthority('SUPERADMIN')")
    public Object addProjets(
            @Param("libelle") String libelle,
            @Param("description") String description,
            @Param("file") MultipartFile file) throws IOException {

        Projets projets = new Projets();
        projets.setLibelle(libelle);
        projets.setDescription(description);
        projets.setImages(ConfigImg.save(file,file.getOriginalFilename()));


        return projetsServices.ajouterProjets(projets);

    }


    @PutMapping("/modifier/{id}")
    @PostAuthorize("hasAuthority('SUPERADMIN')")
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

    @DeleteMapping("/supprime/{id}")
    @PostAuthorize("hasAuthority('ADMIN')")
    public Object deleteProjets(@PathVariable Long id) {

        return projetsServices.supprimerProjets(id);
    }
}
