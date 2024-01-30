package com.chu.appgestionpatientchu.services.partieSpecialise;


import com.chu.appgestionpatientchu.domain.PrescriptionTherapeutique;
import com.chu.appgestionpatientchu.dto.PrescriptionTherapeutiqueDto;
import com.chu.appgestionpatientchu.mappers.partieSpecialise.MapperPrescriptionTherapeutique;
import com.chu.appgestionpatientchu.repository.partieSpecialise.PrescriptionTherapeutiqueRepository;
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
public class PrescriptionTherapeutiqueService {

    private final PrescriptionTherapeutiqueRepository prescriptionTherapeutiqueRepository;


    public List<PrescriptionTherapeutiqueDto> saveAll(List<PrescriptionTherapeutiqueDto> prescriptionTherapeutique){
        List<PrescriptionTherapeutique> prescriptionTherapeutiqueList = prescriptionTherapeutique.stream().map(MapperPrescriptionTherapeutique::mapToPrescriptionTherapeutique).collect(Collectors.toList());
        List<PrescriptionTherapeutique> savedPrescriptionTherapeutique = this.prescriptionTherapeutiqueRepository.saveAll(prescriptionTherapeutiqueList);
        return savedPrescriptionTherapeutique.stream()
                .map(MapperPrescriptionTherapeutique::mapToPrescriptionTherapeutiqueDto)
                .collect(Collectors.toList());
    }


    public List<PrescriptionTherapeutiqueDto> findAll(Integer size , Integer page){
        List<PrescriptionTherapeutique> prescriptionTherapeutiqueList = this.prescriptionTherapeutiqueRepository
                .findByIsDeletedFalse( PageRequest.of(page , size))
                .stream().toList();

        return prescriptionTherapeutiqueList.stream()
                .map(MapperPrescriptionTherapeutique::mapToPrescriptionTherapeutiqueDto)
                .collect(Collectors.toList());
    }



    public List<PrescriptionTherapeutiqueDto> findPrescriptionTherapeutiqueByNameOrCreationDate(
            Optional<String> name,
            Optional<LocalDate> creationDate)
    {
        if (name.isPresent() && creationDate.isPresent()) {
            return prescriptionTherapeutiqueRepository.findByNameAndAddedAtAndIsDeletedFalse(name.get(), creationDate.get() )
                    .stream()
                    .map(MapperPrescriptionTherapeutique::mapToPrescriptionTherapeutiqueDto)
                    .collect(Collectors.toList());
        } else if (name.isPresent()) {
            return prescriptionTherapeutiqueRepository.findByNameAndIsDeletedFalse(name.get())
                    .stream()
                    .map(MapperPrescriptionTherapeutique::mapToPrescriptionTherapeutiqueDto)
                    .collect(Collectors.toList());
        } else if (creationDate.isPresent()) {
            return prescriptionTherapeutiqueRepository.findByAddedAtAndIsDeletedFalse(creationDate.get())
                    .stream()
                    .map(MapperPrescriptionTherapeutique::mapToPrescriptionTherapeutiqueDto)
                    .collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }

    }

    public PrescriptionTherapeutiqueDto updatePrescriptionTherapeutique(Long id , PrescriptionTherapeutiqueDto updatePrescriptionTherapeutique) throws EntityNotFoundException {
        return prescriptionTherapeutiqueRepository.findPrescriptionTherapeutiqueByIdAndIsDeletedFalse(id)
                .map(prescriptionTherapeutique -> {
                    prescriptionTherapeutique.setName(updatePrescriptionTherapeutique.getName());
                    prescriptionTherapeutique.setIsPassedToCommune(updatePrescriptionTherapeutique.getIsPassedToCommune());
                    prescriptionTherapeutique.setAddedAt(updatePrescriptionTherapeutique.getAddedAt());

                    return MapperPrescriptionTherapeutique.mapToPrescriptionTherapeutiqueDto(
                            prescriptionTherapeutiqueRepository.save(prescriptionTherapeutique)
                    );
                })
                .orElseThrow(() -> new EntityNotFoundException("PrescriptionTherapeutique not found with id " + id));
    }

    public PrescriptionTherapeutiqueDto deletePrescriptionTherapeutique(Long id) throws EntityNotFoundException  {
        return prescriptionTherapeutiqueRepository.findPrescriptionTherapeutiqueByIdAndIsDeletedFalse(id )
                .map(anamnese  -> {
                    anamnese.setDeleted(true);

                    return MapperPrescriptionTherapeutique.mapToPrescriptionTherapeutiqueDto(
                            prescriptionTherapeutiqueRepository.save(anamnese)
                    );
                })
                .orElseThrow(() -> new EntityNotFoundException("Anamnese not found with id " + id));
    }
}
