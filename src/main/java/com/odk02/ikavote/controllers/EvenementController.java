package com.odk02.ikavote.controllers;

import com.odk02.ikavote.img.ConfigImg;
import com.odk02.ikavote.models.Evenements;

import com.odk02.ikavote.repository.AuthentificationRepository;
import com.odk02.ikavote.repository.PaysRepository;
import com.odk02.ikavote.repository.ProjetsRepository;
import com.odk02.ikavote.service.EvenementsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/*@CrossOrigin(value = "http://localhost:4200",maxAge = 3600,allowCredentials = "true")*/
@CrossOrigin(origins ={ "http://localhost:4200/", "http://localhost:8100/" }, maxAge = 3600, allowCredentials="true")

@RestController
@RequestMapping("/api/auth")
public class EvenementController {

    @Autowired
    EvenementsService evenementsService;


    @Autowired
    AuthentificationRepository authentificationRepository;

    @Autowired
    PaysRepository paysRepository;

    // Afficher tous les evenements
    @GetMapping("/getallevents")
   // @PostAuthorize("hasAuthority('SUPERADMIN')")
    public List<Evenements> getAll() {

        return evenementsService.afficherTousLesEvenements();
    }


 /* @GetMapping("/getallevents/{id}")

  public List<Evenements> getEventsId(@PathVariable Long id) {

    return projetsRepository.findEvenementByProjets(id);
  }*/


    // Afficher un event par id
    @GetMapping("/getonevents/{id}")
   // @PostAuthorize("hasAuthority('SUPERADMIN')")
    public Object afficherUnEventParId(@PathVariable Long id) {

        return evenementsService.afficherEvenementsParId(id);
    }


    // Ajouter un pays
    @PostMapping("/ajoutevents")
  //  @PostAuthorize("hasAuthority('SUPERADMIN')")
    public Object addEvents(
            @Param("libelle") String libelle,
            @Param("dateDebut") Date dateDebut,
            @Param("dateFin") Date dateFin,
            @Param("bareme") Long bareme,
            @Param("coefficientUser") Long coefficientUser,
            @Param("coefficientJury") Long coefficientJury,
            @Param("nbreVotant") Integer nbreVotant,
            @Param("idauth") Long idauth,
            @Param("idpays") Long idpays,
            @Param("file") MultipartFile file) throws IOException {


        Evenements evenements = new Evenements();
        evenements.setLibelle(libelle);
        evenements.setDateDebut(dateDebut);
        evenements.setDateFin(dateFin);
        evenements.setBareme(bareme);
        evenements.setCoefficientUser(coefficientUser);
        evenements.setCoefficientJury(coefficientJury);
        evenements.setNbreVotant(nbreVotant);

        evenements.setAuthentification(authentificationRepository.findById(idauth).get());
        evenements.setPays(paysRepository.findById(idpays).get());
        evenements.setImages(ConfigImg.save(file,file.getOriginalFilename()));

        if (evenements.getDateFin().before(evenements.getDateDebut())) {
          return "La date de début ne peut pas être après la date de fin";
        } else if (coefficientJury + coefficientUser != 100) {
          return "Attention la somme des coefficients user et jury est toujours egale à 100% ";
        }
        else {
          return evenementsService.ajouterEvenements(evenements);
        }

    }


    @PutMapping("/modifierevent/{id}")
  //  @PostAuthorize("hasAuthority('SUPERADMIN')")
    public Object updatePays(@PathVariable Long id,
                             @Param("libelle") String libelle,
                             @Param("dateDebut") Date dateDebut,
                             @Param("dateFin") Date dateFin,
                             @Param("bareme") Long bareme,
                             @Param("coefficientUser") Long coefficientUser,
                             @Param("coefficientJury") Long coefficientJury,
                             @Param("nbreVotant") Integer nbreVotant,
                             @Param("file") MultipartFile file) throws IOException {

      Random r=new Random();
      List<String> reference=new ArrayList<>();
      for (int i=0;i<nbreVotant;i++){
        String element="";
        for (int j=0;j<3;j++){
          element+=r.nextInt(9);
        }
        reference.add(element);
      }

        Evenements evenements = new Evenements();
        evenements.setLibelle(libelle);
        evenements.setDateDebut(dateDebut);
        evenements.setDateFin(dateFin);
        evenements.setBareme(bareme);
        evenements.setCoefficientUser(coefficientUser);
        evenements.setCoefficientJury(coefficientJury);

        evenements.setImages(ConfigImg.save(file,file.getOriginalFilename()));

      if (evenements.getDateFin().before(evenements.getDateDebut())) {
        return "La date de début ne peut pas être après la date de fin";
      } else if (coefficientJury + coefficientUser != 100) {
        return "Attention la somme des coefficients user et jury est toujours egale à 100% ";
      }
      else {
        return evenementsService.ajouterEvenements(evenements);
      }
    }

    @DeleteMapping("/supprimevent/{id}")
  //  @PostAuthorize("hasAuthority('SUPERADMIN')")
    public Object delete(@PathVariable Long id) {

        return evenementsService.supprimerEvenements(id);
    }
}
