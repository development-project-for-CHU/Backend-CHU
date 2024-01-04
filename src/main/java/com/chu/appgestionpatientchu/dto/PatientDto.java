package com.chu.appgestionpatientchu.dto;

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
public class PatientDto {

    private Long ipp ;

    private String nom ;
    private String prenom ;
    private String adresse;
    private String codePostal;
    private String ville;
    private Date dateNaissance;
    private String cin ;
    private String numTel ;
    private Genders genre ;

    private Date createAt ;

    private Date updateAt;

    private Long  idDossierPatient;

}
