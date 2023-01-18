package com.odk02.ikavote.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "evaluation")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Evaluation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  private Long note;


  //Les jointures

  @JoinColumn(name = "id_user")
  @ManyToOne
  private User user;

  @JoinColumn(name = "id_critere")
  @ManyToOne
  private Criteres criteres;

  @JoinColumn(name = "id_projet")
  @ManyToOne
  private Projets projets;

  @JoinColumn(name = "id_codevotant")
  @ManyToOne
  private Codevotant codevotant;


}
