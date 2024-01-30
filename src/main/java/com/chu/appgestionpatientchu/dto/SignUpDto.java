package com.chu.appgestionpatientchu.dto;

import com.chu.appgestionpatientchu.utils.enums.Genders;
import com.chu.appgestionpatientchu.utils.enums.Roles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpDto {

    private String email;
    private String password;
    private String nom;
    private String prenom;
    private Date dateNaissance;
    private String numTel;
    private Genders genre;
    private String adresse;
    private String ville;
    private String codePostal;
    private Roles roles;
    private String service;


}
