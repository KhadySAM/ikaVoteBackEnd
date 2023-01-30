package com.odk02.ikavote.models;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "pays")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pays {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  //champ obligatoire
  @NotBlank
  @Size(max = 20)
  private String nom;

  @NotBlank
  @Size(max = 20)
  private String initiale;

  @NotBlank
  private String images;

}
