package com.chu.appgestionpatientchu.mappers;

import com.chu.appgestionpatientchu.domain.Patient;
import com.chu.appgestionpatientchu.domain.Personne;
import com.chu.appgestionpatientchu.dto.PatientDto;
import com.chu.appgestionpatientchu.dto.PersonneDto;
import com.chu.appgestionpatientchu.dto.SignUpDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


public class PersonneMapper {

    public static Personne mapToPersonne(SignUpDto personneDto){
        return Personne.builder()

                .email(personneDto.getEmail())
                .password(personneDto.getPassword())
                .nom(personneDto.getNom())
                .prenom(personneDto.getPrenom())
                .dateNaissance(personneDto.getDateNaissance())
                .numTel(personneDto.getNumTel())
                .genre(personneDto.getGenre())
                .adresse(personneDto.getAdresse())
                .ville(personneDto.getVille())
                .codePostal(personneDto.getCodePostal())
                .roles(personneDto.getRoles())
                .service(personneDto.getService())
                .build();
    }
    public static PersonneDto mapToDto(Personne personne) {

       return PersonneDto.builder()

                .email(personne.getEmail())
                .password(personne.getPassword())
                .nom(personne.getNom())
                .prenom(personne.getPrenom())
                .dateNaissance(personne.getDateNaissance())
                .numTel(personne.getNumTel())
                .genre(personne.getGenre())
                .adresse(personne.getAdresse())
                .ville(personne.getVille())
                .codePostal(personne.getCodePostal())
                .roles(personne.getRoles())
                .service(personne.getService())

                .build();


    }

}
