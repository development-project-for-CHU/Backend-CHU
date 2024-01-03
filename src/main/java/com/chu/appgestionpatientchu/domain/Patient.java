package com.chu.appgestionpatientchu.domain;

import com.chu.appgestionpatientchu.utils.enums.Genders;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "PATIENT")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ipp ;

    private String nom ;
    private String prenom ;

    private String cin ;
    private String numTel ;
    private Genders genre ;
    private String ville;
    private String adresse;
    private String codePostal;
    @Temporal(value = TemporalType.DATE)
    private Date dateNaissance;

    @Temporal(value = TemporalType.DATE)
    private Date createAt ;
    @Temporal(value = TemporalType.DATE)
    private Date updateAt;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DOSSIER_PATIENT" , referencedColumnName = "id")
    private DossierPatient dossierPatient ;

}
