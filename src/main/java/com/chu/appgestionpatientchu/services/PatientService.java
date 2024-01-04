package com.chu.appgestionpatientchu.services;


import com.chu.appgestionpatientchu.domain.Patient;
import com.chu.appgestionpatientchu.dto.PatientDto;
import com.chu.appgestionpatientchu.mappers.PatientMapper;
import com.chu.appgestionpatientchu.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatientService {
    @Autowired
    private final PatientRepository patientRepository;
    public PatientDto savePatient(PatientDto patientDto) {

        patientDto.setCreateAt(new Date(System.currentTimeMillis()));
        patientDto.setUpdateAt(new Date(System.currentTimeMillis()));
        Patient patient = PatientMapper.mapToPatient(patientDto);
        Patient savedPatient =  patientRepository.save(patient);

        return PatientMapper.mapToDto(savedPatient);
    }

    public List<Patient> findAllPatient() {
        return patientRepository.findAll();
    }

    public Patient findPatientIpp(Long patientipp) {
        return patientRepository.findByIpp(patientipp);
    }

    public void deletePatient(Long ipp) {
        if (patientRepository.existsById(ipp)) {
            patientRepository.deleteById(ipp);
        } else {
            throw new PatientNotFoundException("Patient not found with IPP: " + ipp);
        }
    }


    public Patient updatePatient(Long ipp, Patient updatedPatient) {
        Optional<Patient> existingPatientOptional = patientRepository.findById(ipp);
        updatedPatient.setUpdateAt(new Date(System.currentTimeMillis()));

        if (existingPatientOptional.isPresent()) {
            Patient existingPatient = existingPatientOptional.get();

            existingPatient.setNom(updatedPatient.getNom());
            existingPatient.setPrenom(updatedPatient.getPrenom());
            existingPatient.setCin(updatedPatient.getCin());
            existingPatient.setNumTel(updatedPatient.getNumTel());
            existingPatient.setGenre(updatedPatient.getGenre());
            existingPatient.setCreateAt(updatedPatient.getCreateAt());
            existingPatient.setAdresse(updatedPatient.getAdresse());
            existingPatient.setDateNaissance(updatedPatient.getDateNaissance());
            existingPatient.setCodePostal(updatedPatient.getCodePostal());
            existingPatient.setVille(updatedPatient.getVille());
            existingPatient.setUpdateAt(updatedPatient.getUpdateAt());
            existingPatient.setDossierPatient(updatedPatient.getDossierPatient());


            return patientRepository.save(existingPatient);
        } else {
            // Handle not found case
            throw new PatientNotFoundException("Patient not found with ipp: " + ipp);
        }
    }
    public static class PatientNotFoundException extends RuntimeException {
        public PatientNotFoundException(String message) {
            super(message);
        }
    }
}