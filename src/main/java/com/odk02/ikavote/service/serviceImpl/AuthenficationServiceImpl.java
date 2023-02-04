package com.odk02.ikavote.service.serviceImpl;

import com.odk02.ikavote.models.Authentification;
import com.odk02.ikavote.repository.AuthentificationRepository;
import com.odk02.ikavote.service.AuthentificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthenficationServiceImpl implements AuthentificationService {


  @Autowired
  private AuthentificationRepository authentificationRepository;

  @Override
  public Object ajouterTypesAuth(Authentification authentification) {
    if (authentificationRepository.findByLibelle(authentification.getLibelle()) == null) {

      authentificationRepository.save(authentification) ;

      return "Types d'authentification ajouter succès";

    } else {
      return  "Ce types d'authentification existe déjà";
    }

  }

  @Override
  public Object supprimerTypesAuth(Long id) {

    Optional<Authentification> authentification = authentificationRepository.findById(id);
    if (!authentification.isPresent()) {
      return "Ce type d'authentification n'existe pas !";
    }
    else {
      authentificationRepository.delete(authentification.get());
      return "Type d'authentification supprimé avec succès";
    }
  }



  @Override
  public Object ModifierTypesAuth(Authentification authentification, Long id) {
    Optional<Authentification> authentificationExist = this.authentificationRepository.findById(id);
    if (authentificationExist.isEmpty()) {
      return "Type d'authentification non trouvé !";
    }
    else {
      Authentification authMod = authentificationRepository.findById(id).get();
      authMod.setLibelle(authentification.getLibelle());
      authentificationRepository.saveAndFlush(authMod);

      return "Type d'authentification modifié";
    }
  }



  @Override
  public Object afficherUnTypesAuth(Long id) {
    Optional<Authentification> authentification = authentificationRepository.findById(id);

    if (authentification.isEmpty()) {
      return "Type d'authentification non trouvé";
    }
    else {
      return authentificationRepository.findById(id).get();
    }
  }

  @Override
  public List<Authentification> afficherTousLesTypesAuth() {
    return authentificationRepository.findAll();
  }

 /* @Override
  public Object generCode(Integer nbreVotant, Evenements evenements) {
    List<Authentification> listauthentification=new ArrayList<>();
    Random r=new Random();
    for (int i=0;i<nbreVotant;i++){
      String element="";
      Authentification authentification=new Authentification();

      for (int j=0;j<3;j++){
        element+=r.nextInt(9);
      }
      authentification.setCode(Long.valueOf(element));
      authentification.getEvenements().add(evenements);
      listauthentification.add(authentificationRepository.save(authentification));
    }
    return listauthentification;
  }*/
}
