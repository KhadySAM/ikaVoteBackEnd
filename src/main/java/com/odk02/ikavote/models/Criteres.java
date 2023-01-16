package com.odk02.ikavote.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "criteres")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Criteres {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Size(max = 50)
  private String titre;

  @NotBlank
  @Size(max =50)
  private  String contenu;


  //Les jointures
  @JoinColumn(name = "id_events")
  @ManyToOne
  private Evenements evenements;
}


