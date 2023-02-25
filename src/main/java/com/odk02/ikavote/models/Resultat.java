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

    private  Double noteFinal;

    private  Double noteJury;

    private  Double noteVotant;


    //Les jointures
    @OneToOne
    @JoinColumn(name = "id_projet")
    private Projets projets;
}
