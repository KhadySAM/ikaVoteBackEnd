package com.odk02.ikavote.controllers;

import com.odk02.ikavote.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class EvaluationController {


    @Autowired
    EvaluationService evaluationService;
    @PostMapping("/noter/{idprojet}/evaluation/{idcriteres}/{idreference}")
    @PostAuthorize("hasAuthority('SUPERADMIN')")
    public Object noterUnprojetParCriter(@RequestBody Long id_projets, Long id_criteres, Long id_reference) {
        return evaluationService.noterUnprojetParCriter(id_projets, id_criteres, id_reference);
    }

}
