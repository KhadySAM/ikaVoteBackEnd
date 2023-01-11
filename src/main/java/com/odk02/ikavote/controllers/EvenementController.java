package com.odk02.ikavote.controllers;

import com.odk02.ikavote.img.ConfigImg;
import com.odk02.ikavote.models.Evenements;
import com.odk02.ikavote.models.Pays;
import com.odk02.ikavote.service.EvenementsService;
import com.odk02.ikavote.service.PaysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class EvenementController {

    @Autowired
    EvenementsService evenementsService;


    // Afficher tous les evenements
    @GetMapping("/getallpays")
    @PostAuthorize("hasAuthority('ADMIN')")
    public List<Evenements> getAll() {

        return evenementsService.afficherTousLesEvenements();
    }

    // Afficher un pays par id
    @GetMapping("/getonepays/{id}")
    @PostAuthorize("hasAuthority('ADMIN')")
    public Object afficherUnEventParId(@PathVariable Long id) {

        return evenementsService.afficherEvenementsParId(id);
    }


    // Ajouter un pays
    @PostMapping("/ajoutpays")
    @PostAuthorize("hasAuthority('SUPERADMIN')")
    public Object addEvents(
            @Param("libelle") String libelle,
            @Param("dateDebut") Date dateDebut,
            @Param("dateFin") Date dateFin,
            @Param("file") MultipartFile file) throws IOException {

        Evenements evenements = new Evenements();
        evenements.setLibelle(libelle);
        evenements.setDateDebut(dateDebut);
        evenements.setDateFin(dateFin);
        evenements.setImages(ConfigImg.save(file,file.getOriginalFilename()));


        return evenementsService.ajouterEvenements(evenements);

    }


    @PutMapping("/modifier/{id}")
    @PostAuthorize("hasAuthority('SUPERADMIN')")
    public Object updatePays(@PathVariable Long id,
                             @Param("libelle") String libelle,
                             @Param("dateDebut") Date dateDebut,
                             @Param("dateFin") Date dateFin,
                             @Param("file") MultipartFile file) throws IOException {

        Evenements evenements = new Evenements();
        evenements.setLibelle(libelle);
        evenements.setDateDebut(dateDebut);
        evenements.setDateFin(dateFin);
        evenements.setImages(ConfigImg.save(file,file.getOriginalFilename()));

        return evenementsService.ModifierEvenements(evenements, id);
    }

    @DeleteMapping("/supprime/{id}")
    @PostAuthorize("hasAuthority('SUPERADMIN')")
    public Object delete(@PathVariable Long id) {

        return evenementsService.supprimerEvenements(id);
    }
}
