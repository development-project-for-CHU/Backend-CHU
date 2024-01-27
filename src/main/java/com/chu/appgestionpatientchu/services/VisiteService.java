package com.chu.appgestionpatientchu.services;

import com.chu.appgestionpatientchu.domain.DossierPatient;
import com.chu.appgestionpatientchu.domain.Visite;
import com.chu.appgestionpatientchu.dto.VisiteDto;
import com.chu.appgestionpatientchu.mappers.VisiteMapper;
import com.chu.appgestionpatientchu.repository.VisiteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
    @RequiredArgsConstructor
    public class VisiteService {
    @Autowired
    private final VisiteRepository visiteRepository;

        private final DossierpatientService dossierPatientService;


        public VisiteDto saveVisite(VisiteDto visiteDto) {
            visiteDto.setDateVisite(new Date(System.currentTimeMillis()));
            Visite visite = VisiteMapper.mapToVisite(visiteDto);
            Optional<DossierPatient> dossierPatientOptional = dossierPatientService.findDossierpatientId(visiteDto.getDossierPatientid());

            if (dossierPatientOptional.isPresent()) {
                DossierPatient dossierPatient = dossierPatientOptional.get();

                // Associate the Visite with the DossierPatient
                visite.setDossierPatient(dossierPatient);

                // Save the Visite
                Visite savedVisite = visiteRepository.save(visite);

                return VisiteMapper.mapToVisitedto(savedVisite);
            } else {
                // Handle the case when DossierPatient is not found
                throw new DossierpatientService.DossierPatientNotFoundException("DossierPatient not found with ID: " + visiteDto.getDossierPatientid());
            }
        }

        public List<Visite> findAllVisites() {
            return visiteRepository.findAll();
        }

        public Visite findVisiteById(Long id) {
            return visiteRepository.findById(id).orElse(null);
        }

        public void deleteVisite(Long id) {
            if (visiteRepository.existsById(id)) {
                visiteRepository.deleteById(id);
            } else {
                throw new VisiteNotFoundException("Visite not found with ID: " + id);
            }
        }

//        public Visite updateVisite(Long id, Visite updatedVisite) {
//            Optional<Patient> existingVisiteOptional = visiteRepository.findById(id);
//
//            if (existingVisiteOptional.isPresent()) {
//                Visite existingVisite = existingVisiteOptional.get();
//
//                existingVisite.setDateVisite(updatedVisite.getDateVisite());
//
//                return visiteRepository.save(existingVisite);
//            } else {
//                // Handle not found case
//                throw new VisiteNotFoundException("Visite not found with ID: " + id);
//            }
//        }

        // You can add more methods as needed

        public static class VisiteNotFoundException extends RuntimeException {
            public VisiteNotFoundException(String message) {
                super(message);
            }
        }
    }


