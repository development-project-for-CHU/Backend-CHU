package com.chu.appgestionpatientchu.services.partieSpecialise;


import com.chu.appgestionpatientchu.domain.ExamenClinique;
import com.chu.appgestionpatientchu.domain.PrescriptionDiagnostique;
import com.chu.appgestionpatientchu.dto.ExamenCliniqueDto;
import com.chu.appgestionpatientchu.dto.PrescriptionDiagnostiqueDto;
import com.chu.appgestionpatientchu.mappers.partieSpecialise.MapperExamenClinique;
import com.chu.appgestionpatientchu.mappers.partieSpecialise.MapperPrescriptionDiagnostique;
import com.chu.appgestionpatientchu.repository.partieSpecialise.ExamenCliniqueRepository;
import com.chu.appgestionpatientchu.repository.partieSpecialise.PrescriptionDiagnostiqueRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PrescriptionDiagnostiqueService {

    private final PrescriptionDiagnostiqueRepository prescriptionDiagnostiqueRepository;


    public List<PrescriptionDiagnostiqueDto> saveAll(List<PrescriptionDiagnostiqueDto> prescriptionDiagnostique){
        List<PrescriptionDiagnostique> prescriptionDiagnostiqueList = prescriptionDiagnostique.stream().map(MapperPrescriptionDiagnostique::mapToPrescriptionDiagnostique).collect(Collectors.toList());
        List<PrescriptionDiagnostique> savedprescriptionDiagnostique= this.prescriptionDiagnostiqueRepository.saveAll(prescriptionDiagnostiqueList);
        return savedprescriptionDiagnostique.stream()
                .map(MapperPrescriptionDiagnostique::mapToPrescriptionDiagnostiqueDto)
                .collect(Collectors.toList());
    }


    public List<PrescriptionDiagnostiqueDto> findAll(Integer size , Integer page){
        List<PrescriptionDiagnostique> prescriptionDiagnostiqueList = this.prescriptionDiagnostiqueRepository
                .findByIsDeletedFalse( PageRequest.of(page , size))
                .stream().toList();

        return prescriptionDiagnostiqueList.stream()
                .map(MapperPrescriptionDiagnostique::mapToPrescriptionDiagnostiqueDto)
                .collect(Collectors.toList());
    }


    public List<PrescriptionDiagnostiqueDto> findPrescriptionDiagnostiqueByNameOrCreationDate(
            Optional<String> name,
            Optional<LocalDate> creationDate)
    {
        if (name.isPresent() && creationDate.isPresent()) {
            return prescriptionDiagnostiqueRepository.findByNomPrescriptionDiagnostiqueAndAddedAt(name.get(), creationDate.get() )
                    .stream()
                    .map(MapperPrescriptionDiagnostique::mapToPrescriptionDiagnostiqueDto)
                    .collect(Collectors.toList());
        } else if (name.isPresent()) {
            return prescriptionDiagnostiqueRepository.findByNomPrescriptionDiagnostique(name.get())
                    .stream()
                    .map(MapperPrescriptionDiagnostique::mapToPrescriptionDiagnostiqueDto)
                    .collect(Collectors.toList());
        } else if (creationDate.isPresent()) {
            return prescriptionDiagnostiqueRepository.findByAddedAt(creationDate.get())
                    .stream()
                    .map(MapperPrescriptionDiagnostique::mapToPrescriptionDiagnostiqueDto)
                    .collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }

    }


    public PrescriptionDiagnostiqueDto updatePrescriptionDiagnostique(Long id ,PrescriptionDiagnostiqueDto updatePrescriptionDiagnostique) throws EntityNotFoundException {
        return prescriptionDiagnostiqueRepository.findPrescriptionDiagnostiqueById(id)
                .map(prescriptionDiagnostique -> {
                    prescriptionDiagnostique.setNomPrescriptionDiagnostique(updatePrescriptionDiagnostique.getNomPrescriptionDiagnostique());
                    prescriptionDiagnostique.setAddedAt(updatePrescriptionDiagnostique.getAddedAt());

                    return MapperPrescriptionDiagnostique.mapToPrescriptionDiagnostiqueDto(
                            prescriptionDiagnostiqueRepository.save(prescriptionDiagnostique)
                    );
                })
                .orElseThrow(() -> new EntityNotFoundException("  Prescription DiagnostiqueDto not found with id " + id));
    }


}
