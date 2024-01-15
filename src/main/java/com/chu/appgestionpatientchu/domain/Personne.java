package com.chu.appgestionpatientchu.domain;

import com.chu.appgestionpatientchu.utils.enums.Genders;
import org.apache.commons.lang3.RandomStringUtils;
import com.chu.appgestionpatientchu.utils.enums.Roles;
import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.Size;

import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "Personne")

public class Personne {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    @Column(nullable = false)
    @Size(max = 100)
    private String password;
    private String nom;
    private String prenom;
    private Date dateNaissance;
    private  String numTel;
    private Genders genre;
    private String adresse ;
    private String ville ;
    private String codePostal;
    private Roles roles ;
    private String service;

    public void generateRandomPassword() {
        this.password = RandomStringUtils.randomAlphanumeric(15);
    }

}


