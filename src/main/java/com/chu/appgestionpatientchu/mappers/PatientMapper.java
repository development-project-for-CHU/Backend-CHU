package com.chu.appgestionpatientchu.mappers;

import com.chu.appgestionpatientchu.domain.Patient;
import com.chu.appgestionpatientchu.dto.PatientDto;

public class PatientMapper {
    public static Patient mapToPatient(PatientDto patientDto){
        return Patient.builder()
                .adresse(patientDto.getAdresse())
                .dateNaissance(patientDto.getDateNaissance())
                .cin(patientDto.getCin())
                .nom(patientDto.getNom())
                .prenom(patientDto.getPrenom())
                .codePostal(patientDto.getCodePostal())
                .numTel(patientDto.getNumTel())
                .ville(patientDto.getVille())
                .genre(patientDto.getGenre())
                .createAt(patientDto.getCreateAt())
                .createAt(patientDto.getUpdateAt())
                .build();
    }

    public static PatientDto mapToDto(Patient patient) {
        return PatientDto.builder()
                .ipp(patient.getIpp())
                .nom(patient.getNom())
                .prenom(patient.getPrenom())
                .cin(patient.getCin())
                .adresse(patient.getAdresse())
                .ville(patient.getVille())
                .codePostal(patient.getCodePostal())
                .numTel(patient.getNumTel())
                .dateNaissance(patient.getDateNaissance())
                .genre(patient.getGenre())
                .createAt(patient.getCreateAt())
                .updateAt(patient.getUpdateAt())
                .build();
    }
}
