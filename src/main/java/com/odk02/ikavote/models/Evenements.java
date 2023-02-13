package com.odk02.ikavote.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
  private Double coefficientUser;

  @NotBlank
  private Double coefficientJury;

  @NotBlank
  private Integer nbreVotant;


  //Les jointures
  @JoinColumn(name = "id_Auth")
  @ManyToOne
  private Authentification authentification;

  @JoinColumn(name = "id_pays")
  @ManyToOne
  private Pays pays;


  @OneToMany(mappedBy = "evenements")
  @JsonIgnore
  private List<Projets> projets;




}
