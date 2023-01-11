package com.odk02.ikavote.service.serviceImpl;



import com.odk02.ikavote.models.Pays;
import com.odk02.ikavote.repository.PaysRepository;
import com.odk02.ikavote.service.PaysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class PaysServiceImpl implements PaysService {

  @Autowired
  private PaysRepository paysRepository;

  @Override
  public Object ajouterPays(Pays pays) {

    if(paysRepository.findByNom(pays.getNom()) == null) {

       paysRepository.save(pays);

       return "pays ajouter succès";
    }else {
      return "ce pays existe déjà";
    }
  }

  @Override
  public Object supprimerPays(Long id) {
    Optional<Pays> pays = paysRepository.findById(id);
    if (pays.isPresent()) {
      return "Ce pays n'existe pas !";
    }
    else {

      paysRepository.delete(pays.get());
      return "Pays supprimé avec succès";
    }
  }


  @Override
  public Object ModifierPays(Pays pays, Long id) {
    Optional<Pays> paysExiste = this.paysRepository.findById(id);
    if (paysExiste.isEmpty()) {
      return "Pays non trouvé !";

    }
    else {
      Pays paysMod = paysRepository.findById(id).get();
      paysMod.setNom(pays.getNom());
      paysMod.setInitiale(pays.getInitiale());
      paysMod.setImages(pays.getImages());
      paysRepository.saveAndFlush(paysMod);

      return "Pays modifier avec succès";
    }
  }

  @Override
  public Object afficherUn(Long id) {
    Optional<Pays> pays = paysRepository.findById(id);

    if (pays.isEmpty()) {

      return  "Cet pays n'est pas trouvée !";
    }
    else {
      return paysRepository.findById(id).get();
    }
  }

  @Override
  public List<Pays> afficherTousLesPays() {

    return paysRepository.findAll();
  }


}
