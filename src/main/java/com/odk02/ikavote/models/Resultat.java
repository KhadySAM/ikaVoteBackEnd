package com.odk02.ikavote.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "resultat")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Resultat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // a enlever
    private  Double note;


    //Les jointures
    @JoinColumn(name = "id_projet")
    @OneToOne
    private Projets projets;
}
