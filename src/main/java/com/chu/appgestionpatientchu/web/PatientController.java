package com.chu.appgestionpatientchu.web;


import com.chu.appgestionpatientchu.domain.Patient;
import com.chu.appgestionpatientchu.dto.PatientDto;
import com.chu.appgestionpatientchu.services.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/patient")
@RequiredArgsConstructor
public class PatientController {
    @Autowired
private final PatientService patientService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Long> save(
            @RequestBody PatientDto patientDto
    ) {
        PatientDto savedPatient = patientService.savePatient(patientDto);
        return new ResponseEntity<>(savedPatient.getIpp() , HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Patient>> findAllPatients() {
        return ResponseEntity.ok(patientService.findAllPatient());
    }

    @GetMapping("/{patientIpp}")
    public ResponseEntity<Patient> findPatientByIpp(@PathVariable("patientIpp") Long ipp) {
        Patient patient = patientService.findPatientIpp(ipp);

        if (patient != null) {
            return new ResponseEntity<>(patient, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{patientIpp}")
    public ResponseEntity<Void> deletePatient(@PathVariable("patientIpp") Long ipp) {
        try {
            patientService.deletePatient(ipp);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{patientIpp}")
    public ResponseEntity<Patient> updatePatient(
            @PathVariable("patientIpp") Long ipp,
            @RequestBody Patient updatedPatient
    ) {
        Patient patient = patientService.updatePatient(ipp, updatedPatient);

        if (patient != null) {
            return new ResponseEntity<>(patient, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
