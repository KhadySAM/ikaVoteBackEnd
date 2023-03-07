package com.odk02.ikavote.controllers;

import com.odk02.ikavote.img.ConfigImg;
import com.odk02.ikavote.models.Evenements;

import com.odk02.ikavote.repository.AuthentificationRepository;
import com.odk02.ikavote.repository.EvenementsRepository;
import com.odk02.ikavote.repository.PaysRepository;
import com.odk02.ikavote.repository.ProjetsRepository;
import com.odk02.ikavote.service.CodevotantService;
import com.odk02.ikavote.service.EvenementsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;


@CrossOrigin(origins ={ "http://localhost:4200/", "http://localhost:8100/" }, maxAge = 3600, allowCredentials="true")

@RestController
@RequestMapping("/api/auth")
public class EvenementController {

    @Autowired
    EvenementsService evenementsService;

    @Autowired
    CodevotantService codevotantService;

  @Autowired
  EvenementsRepository evenementsRepository;


    @Autowired
    AuthentificationRepository authentificationRepository;

    @Autowired
    PaysRepository paysRepository;

  @GetMapping("/events/{id}")
  public Evenements getEventById(@PathVariable Long id) {
    return evenementsService.getEvenementsById(id);
  }

    // Afficher tous les evenements
  @GetMapping("/getallevents")
  public List<Evenements> getAll() {

      return evenementsService.afficherTousLesEvenements();
  }


  @GetMapping("/eventsbyprojet/{id}")

  public List<Evenements> getEventsByprojectId(@PathVariable Long id) {

    return evenementsService.getEventWithProjects(id);
  }


    // Afficher un event par id
    @GetMapping("/getonevents/{id}")
    public Object afficherUnEventParId(@PathVariable Long id) {

        return evenementsService.afficherEvenementsParId(id);
    }

  // Ajouter un pays
  @PostMapping("/ajoutevents/{nompays}/{libelleauth}")
 // @PostAuthorize("hasAuthority('ADMIN')")
  public Object addEvents(@PathVariable("nompays") String nompays, @PathVariable("libelleauth") String libelleauth,
    @Param("libelle") String libelle,
    @Param("dateDebut") Date dateDebut,
    @Param("dateFin") Date dateFin,
    @Param("bareme") Long bareme,
    @Param("coefficientUser") Double coefficientUser,
    @Param("coefficientJury") Double coefficientJury,
    @Param("nbreVotant") Integer nbreVotant,
    @Param("file") MultipartFile file) throws IOException {

    Date d = new Date();


    Evenements evenements = new Evenements();
    evenements.setLibelle(libelle);
    evenements.setDateDebut(dateDebut);
    evenements.setDateFin(dateFin);
    evenements.setBareme(bareme);
    evenements.setCoefficientUser(coefficientUser);
    evenements.setCoefficientJury(coefficientJury);
    evenements.setNbreVotant(nbreVotant);

    evenements.setAuthentification(authentificationRepository.findByLibelle(libelleauth));
    evenements.setPays(paysRepository.findByNom(nompays));
    evenements.setImages(ConfigImg.save(file,file.getOriginalFilename()));
    if (evenements.getDateDebut().equals(d) || evenements.getDateDebut().after(d)){

      if (evenements.getDateFin().equals(evenements.getDateDebut()) || evenements.getDateFin().before(evenements.getDateDebut())) {
        return "La date de début ne peut pas être après la date de fin";
      } else if (coefficientJury + coefficientUser != 100) {
        return "Attention la somme des coefficients user et jury est toujours egale à 100% ";
      }
      else {
        return evenementsService.ajouterEvenements(evenements);
      }
    } else {
      return "La date de debut est après la date du jour";
    }

  }

  @PutMapping("/modifiier/{id}")
  public Evenements Modifier(@Param("evenements") Evenements evenements, @PathVariable Long id){
    return evenementsService.modifier(evenements, id);
  }


//    @PutMapping("/modifierevent/{id}")
//    public Object updatePays(@PathVariable Long id,
//                             @Param("libelle") String libelle,
//                             @Param("dateDebut") Date dateDebut,
//                             @Param("dateFin") Date dateFin,
//                             @Param("bareme") Long bareme,
//                             @Param("coefficientUser") Double coefficientUser,
//                             @Param("coefficientJury") Double coefficientJury,
//                             @Param("nbreVotant") Integer nbreVotant,
//                             @Param("file") MultipartFile file) throws IOException {
//
//      Random r=new Random();
//      List<String> reference=new ArrayList<>();
//      for (int i=0;i<nbreVotant;i++){
//        String element="";
//        for (int j=0;j<3;j++){
//          element+=r.nextInt(9);
//        }
//        reference.add(element);
//      }
//
//        Evenements evenements = new Evenements();
//        evenements.setLibelle(libelle);
//        evenements.setDateDebut(dateDebut);
//        evenements.setDateFin(dateFin);
//        evenements.setBareme(bareme);
//        evenements.setCoefficientUser(coefficientUser);
//        evenements.setCoefficientJury(coefficientJury);
//
//        evenements.setImages(ConfigImg.save(file,file.getOriginalFilename()));
//
//      if (evenements.getDateFin().before(evenements.getDateDebut())) {
//        return "La date de début ne peut pas être après la date de fin";
//      } else if (coefficientJury + coefficientUser != 100) {
//        return "Attention la somme des coefficients user et jury est toujours egale à 100% ";
//      }
//      else {
//        return evenementsService.ModifierEvenements();
//      }
//    }

    @DeleteMapping("/supprimevent/{id}")
    public Object delete(@PathVariable Long id) {

        return evenementsService.supprimerEvenements(id);
    }

  @GetMapping("/checkevent/{libelle}")
  public boolean checkEmail(@PathVariable("libelle") String libelle) {

    return evenementsService.existsByNom(libelle);

  }

  @GetMapping("/getunvents/{libelle}")
  public Object afficherUnEventParLibelle(@PathVariable("libelle") String libelle) {

    return evenementsRepository.findByLibelle(libelle);
  }



}
