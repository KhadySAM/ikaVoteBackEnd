package com.odk02.ikavote.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "evenements")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Evenements {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Size(max = 50)
  private String libelle;

  @NotBlank
  private Date dateDebut;

  @NotBlank
  private Date dateFin;

  @NotBlank
  private String images;

  @NotBlank
  private Long bareme;

  @NotBlank
  private Long coefficientUser;

  @NotBlank
  private Long coefficientJury;

  @NotBlank
  private Integer nbreVotant;


  //Les jointures
  @JoinColumn(name = "id_Auth")
  @ManyToOne
  private Authentification authentification;

  @JoinColumn(name = "id_pays")
  @ManyToOne
  private Pays pays;



//  @ManyToMany
//  private List<User> users;




}
