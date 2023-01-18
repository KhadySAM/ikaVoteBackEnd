package com.odk02.ikavote.controllers;

import com.odk02.ikavote.models.Codevotant;
import com.odk02.ikavote.models.Evaluation;
import com.odk02.ikavote.models.Evenements;
import com.odk02.ikavote.models.User;
import com.odk02.ikavote.repository.CriteresRepository;
import com.odk02.ikavote.repository.ProjetsRepository;
import com.odk02.ikavote.repository.UserRepository;
import com.odk02.ikavote.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class EvaluationController {


    @Autowired
    EvaluationService evaluationService;

    @PostMapping("/noterprojet")
    @PostAuthorize("hasAuthority('SUPERADMIN')")
    public Object noterUnprojetParCriter(@RequestBody Codevotant codevotant) {
      return  "fffff";
    }

}
