package com.chu.appgestionpatientchu.web;

import com.chu.appgestionpatientchu.domain.DossierPatient;
import com.chu.appgestionpatientchu.domain.Patient;
import com.chu.appgestionpatientchu.services.DossierpatientService;
import com.chu.appgestionpatientchu.services.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/dossierPatient")
@RequiredArgsConstructor
public class DossierpatientController {

    @Autowired
    private final DossierpatientService dossierpatientService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(
            @RequestBody DossierPatient dossierPatient
    ) {
        dossierpatientService.saveDossierpatient(dossierPatient);
    }


    @GetMapping("/{dossierPatientId}")
    public ResponseEntity<DossierPatient> findDossierPatientById(@PathVariable("dossierPatientId") Long id) {
        Optional<DossierPatient> dossierPatient = dossierpatientService.findDossierpatientId(id);

        return dossierPatient.map(patient -> new ResponseEntity<>(patient, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{dossierPatientId}")
    public ResponseEntity<Void> deleteDossierPatient(@PathVariable("dossierPatientId") Long id) {
        try {
            dossierpatientService.deleteDossierPatient(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<DossierPatient> updateDossierPatient(
            @PathVariable Long id,
            @RequestBody DossierPatient updatedDossierPatient) {
        DossierPatient updatedPatient = dossierpatientService.updateDossierPatient(id, updatedDossierPatient);
        return ResponseEntity.ok(updatedPatient);
    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class DossierPatientNotFoundException extends RuntimeException {
        public DossierPatientNotFoundException(String message) {
            super(message);
        }
    }

}
