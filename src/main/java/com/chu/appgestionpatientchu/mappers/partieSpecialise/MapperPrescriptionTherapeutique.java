package com.chu.appgestionpatientchu.mappers.partieSpecialise;

import com.chu.appgestionpatientchu.domain.PartieSpecialise;
import com.chu.appgestionpatientchu.domain.PrescriptionTherapeutique;
import com.chu.appgestionpatientchu.dto.PrescriptionTherapeutiqueDto;

import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;

public class MapperPrescriptionTherapeutique {

    public static PrescriptionTherapeutique mapToPrescriptionTherapeutique(PrescriptionTherapeutiqueDto prescriptionTherapeutiqueDto){
        return PrescriptionTherapeutique.builder()
                .nomPrescriptionTherapeutique(prescriptionTherapeutiqueDto.getNomPrescriptionTherapeutique())
                .addedAt(new Date())
                .isPassedToCommune(false)
                .build();
    }
    public static PrescriptionTherapeutiqueDto mapToPrescriptionTherapeutiqueDto(PrescriptionTherapeutique prescriptionTherapeutique){
        return PrescriptionTherapeutiqueDto.builder()
                .nomPrescriptionTherapeutique(prescriptionTherapeutique.getNomPrescriptionTherapeutique())
                .id(prescriptionTherapeutique.getId())


                .isPassedToCommune(prescriptionTherapeutique.getIsPassedToCommune())
                .addedAt(prescriptionTherapeutique.getAddedAt())
                .listIdPartieSpecialise(
                        prescriptionTherapeutique.getListPartieSpecialise() != null ?
                                prescriptionTherapeutique.getListPartieSpecialise().stream()
                                        .map(PartieSpecialise::getId)
                                        .collect(Collectors.toList()) :
                                Collections.emptyList()
                )
                .build();
    }
}
