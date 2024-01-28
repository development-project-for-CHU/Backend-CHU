package com.chu.appgestionpatientchu.mappers.partieCommune;

import com.chu.appgestionpatientchu.domain.DiagnostiqueConnue;
import com.chu.appgestionpatientchu.domain.MedicationEncours;
import com.chu.appgestionpatientchu.domain.PartieCommune;
import com.chu.appgestionpatientchu.dto.MedicationEncoursDto;

import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;

public class MapperMedicationEncours {

    public static MedicationEncours mapToMedicationEncours(MedicationEncoursDto medicationEncoursDto) {
        return MedicationEncours.builder()
                .nomMedicationEncours(medicationEncoursDto.getNomMedicament())
                .addedAt(new Date())
                .isDeleted(false)
                .build();
    }

    public static MedicationEncoursDto mapToMedicationEncoursDto (MedicationEncours medicationEncours){
        return MedicationEncoursDto.builder()
                .nomMedicament(medicationEncours.getNomMedicationEncours())
                .id(medicationEncours.getId())
                .addedAt(medicationEncours.getAddedAt())
                .listIdPartieCommune(
                        medicationEncours.getListPartieCommune() != null ?
                                medicationEncours.getListPartieCommune().stream()
                                        .map(PartieCommune::getId)
                                        .collect(Collectors.toList()) :
                                Collections.emptyList()
                )
                .build();
    }



}
