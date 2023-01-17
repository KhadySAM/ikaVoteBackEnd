package com.odk02.ikavote.controllers;



import com.odk02.ikavote.models.Evaluation;
import com.odk02.ikavote.models.Evenements;
import com.odk02.ikavote.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class EvaluationController {



  @PostMapping("/noter/{idprojet}/evaluation/{idcriteres}/{idreference}")
  @PostAuthorize("hasAuthority('SUPERADMIN')")
  public Object  evaluationProject(
    @PathVariable("idprojet") Long idprojet,
    @PathVariable("idcriteres") Long idcriteres,
    @PathVariable("idreference") Long idreference,
    @RequestBody Long note) {


   return "";
  }

}
