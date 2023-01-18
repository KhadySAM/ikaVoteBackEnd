package com.odk02.ikavote.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "codevotant")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Codevotant {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Size(max = 50)
  private Long code;


  @JoinColumn(name = "id_events")
  @ManyToOne
  private Evenements evenements;


}
