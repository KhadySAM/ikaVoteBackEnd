package com.odk02.ikavote.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "authentification")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Authentification {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  //champ obligatoire
  @NotBlank
  @Size(max = 20)
  private String libelle;


}
