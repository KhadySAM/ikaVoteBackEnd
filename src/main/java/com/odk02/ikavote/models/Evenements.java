package com.odk02.ikavote.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

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

}
