package com.chu.appgestionpatientchu.services;

import com.chu.appgestionpatientchu.domain.DossierPatient;
import com.chu.appgestionpatientchu.repository.DossierpatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DossierpatientService {

    @Autowired
    private final DossierpatientRepository dossierpatientRepository;
    public void saveDossierpatient(DossierPatient dossierPatient) {
        dossierPatient.setDateCreation(new Date(System.currentTimeMillis()));
               dossierpatientRepository.save(dossierPatient);
    }
    public Optional<DossierPatient> findDossierpatientId(Long id) {
        return dossierpatientRepository.findById(id);

    }
    public void deleteDossierPatient(Long id) {
        if (dossierpatientRepository.existsById(id)) {
            dossierpatientRepository.deleteById(id);
        } else {
            throw new DossierPatientNotFoundException("Dossier Patient not found with ID: " + id);
        }
    }

    public DossierPatient updateDossierPatient(Long id, DossierPatient updatedDossierPatient) {
        Optional<DossierPatient> existingDossierPatientOptional = dossierpatientRepository.findById(id);

        if (existingDossierPatientOptional.isPresent()) {
            DossierPatient existingDossierPatient = existingDossierPatientOptional.get();
            existingDossierPatient.setDateCreation(updatedDossierPatient.getDateCreation());
            existingDossierPatient.setListVisite(updatedDossierPatient.getListVisite());


            return dossierpatientRepository.save(existingDossierPatient);
        } else {
            throw new DossierPatientNotFoundException("Dossier Patient not found with ID: " + id);
        }
    }









    public static class DossierPatientNotFoundException extends RuntimeException {
        public DossierPatientNotFoundException(String message) {
            super(message);
        }
    }



}
