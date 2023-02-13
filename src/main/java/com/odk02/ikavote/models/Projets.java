package com.odk02.ikavote.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Entity
@Table(name = "projets")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Projets {


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Size(max = 50)
  private String libelle;

  @NotBlank
  @Size(max =50)
  private  String description;

  private  Double moyJury;

  private  Double moyParcitipant;

  private  Double moyTotal;

  @NotBlank
  private String images;

  //Les jointures

  @ManyToOne()
  private Evenements evenements;



}
