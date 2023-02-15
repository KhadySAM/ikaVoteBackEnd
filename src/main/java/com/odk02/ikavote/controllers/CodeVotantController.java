package com.odk02.ikavote.controllers;

import com.odk02.ikavote.models.Codevotant;
import com.odk02.ikavote.service.CodevotantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins ={ "http://localhost:4200/", "http://localhost:8100/" }, maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/api/auth")
public class CodeVotantController {

  @Autowired
  CodevotantService codevotantService;

  @GetMapping("/qrcodebyevents/{idevents}")
  public List<Codevotant> getCodeVotantByEventsId(@PathVariable Long idevents) {

    return codevotantService.getCodeVotantByIdEvent(idevents);
  }


  @GetMapping("/getonecode/{id}")
  public Object afficherUnCodeParId(@PathVariable Long id) {

    return codevotantService.getCodevotantById(id);
  }



}
