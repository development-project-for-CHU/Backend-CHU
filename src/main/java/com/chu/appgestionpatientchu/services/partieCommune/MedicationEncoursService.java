package com.chu.appgestionpatientchu.services.partieCommune;


import com.chu.appgestionpatientchu.domain.MedicationEncours;
import com.chu.appgestionpatientchu.dto.MedicationEncoursDto;
import com.chu.appgestionpatientchu.mappers.partieCommune.MapperMedicationEncours;
import com.chu.appgestionpatientchu.repository.partieCommune.MedicationEncoursRepository;
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
public class MedicationEncoursService {


    private final MedicationEncoursRepository medicationEncoursRepository;





    public List<MedicationEncoursDto> saveAll(List<MedicationEncoursDto> medicationEncours){
        List<MedicationEncours> medicationEncoursList = medicationEncours.stream().map(MapperMedicationEncours::mapToMedicationEncours).collect(Collectors.toList());
        List<MedicationEncours> savedMedicationEncours= this.medicationEncoursRepository.saveAll(medicationEncoursList);
        return savedMedicationEncours.stream()
                .map(MapperMedicationEncours::mapToMedicationEncoursDto)
                .collect(Collectors.toList());
    }




    public List<MedicationEncoursDto> findAll(Integer size , Integer page){
        List<MedicationEncours> medicationEncoursList = this.medicationEncoursRepository
                .findByIsDeletedFalse( PageRequest.of(page , size))
                .stream().toList();

        return medicationEncoursList.stream()
                .map(MapperMedicationEncours::mapToMedicationEncoursDto)
                .collect(Collectors.toList());
    }




    public List<MedicationEncoursDto> findMedicationEncoursByNameOrCreationDate(
            Optional<String> name,
            Optional<LocalDate> creationDate)
    {
        if (name.isPresent() && creationDate.isPresent()) {
            return medicationEncoursRepository.findByNomMedicationEncoursAndAddedAtAndIsDeletedFalse(name.get(), creationDate.get() )
                    .stream()
                    .map(MapperMedicationEncours::mapToMedicationEncoursDto)
                    .collect(Collectors.toList());
        } else if (name.isPresent()) {
            return medicationEncoursRepository.findByNomMedicationEncoursAndIsDeletedFalse(name.get())
                    .stream()
                    .map(MapperMedicationEncours::mapToMedicationEncoursDto)
                    .collect(Collectors.toList());
        } else if (creationDate.isPresent()) {
            return medicationEncoursRepository.findByAddedAtAndIsDeletedFalse(creationDate.get())
                    .stream()
                    .map(MapperMedicationEncours::mapToMedicationEncoursDto)
                    .collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }

    }

    public MedicationEncoursDto updateMedicationEncours(Long id , MedicationEncoursDto updateMedicationEncours) throws EntityNotFoundException {
        return medicationEncoursRepository.findMedicationEncoursByIdAndIsDeletedFalse(id)
                .map(medicationEncours -> {
                    medicationEncours.setNomMedicationEncours(updateMedicationEncours.getNomMedicament());
                    medicationEncours.setAddedAt(updateMedicationEncours.getAddedAt());

                    return MapperMedicationEncours.mapToMedicationEncoursDto(
                            medicationEncoursRepository.save(medicationEncours)
                    );
                })
                .orElseThrow(() -> new EntityNotFoundException("Medication not found with id " + id));
    }


    public MedicationEncoursDto deleteMedicationEncours(Long id) throws EntityNotFoundException  {
        return medicationEncoursRepository.findMedicationEncoursByIdAndIsDeletedFalse(id )
                .map(medicationEncours -> {
                    medicationEncours.setDeleted(true);

                    return MapperMedicationEncours.mapToMedicationEncoursDto(
                            medicationEncoursRepository.save(medicationEncours)
                    );
                })
                .orElseThrow(() -> new EntityNotFoundException("medicament  not found with id " + id));
    }


}


