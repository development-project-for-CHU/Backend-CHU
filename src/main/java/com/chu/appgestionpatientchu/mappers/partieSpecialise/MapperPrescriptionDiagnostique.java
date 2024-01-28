package com.chu.appgestionpatientchu.mappers.partieSpecialise;

import com.chu.appgestionpatientchu.domain.ExamenClinique;
import com.chu.appgestionpatientchu.domain.PartieSpecialise;
import com.chu.appgestionpatientchu.domain.PrescriptionDiagnostique;
import com.chu.appgestionpatientchu.dto.PrescriptionDiagnostiqueDto;

import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;

public class MapperPrescriptionDiagnostique {

    public static PrescriptionDiagnostique mapToPrescriptionDiagnostique(PrescriptionDiagnostiqueDto prescriptionDiagnostiqueDto){
        return PrescriptionDiagnostique.builder()
                .nomPrescriptionDiagnostique(prescriptionDiagnostiqueDto.getNomPrescriptionDiagnostique())
                .addedAt(new Date())
                .isPassedToCommune(false)
                .build();
    }
    public static PrescriptionDiagnostiqueDto mapToPrescriptionDiagnostiqueDto(PrescriptionDiagnostique prescriptionDiagnostique){
        return PrescriptionDiagnostiqueDto.builder()
                .nomPrescriptionDiagnostique(prescriptionDiagnostique.getNomPrescriptionDiagnostique())
                .id(prescriptionDiagnostique.getId())
                .isPassedToCommune(prescriptionDiagnostique.getIsPassedToCommune())
                .addedAt(prescriptionDiagnostique.getAddedAt())
                .listIdPartieSpecialise(
                        prescriptionDiagnostique.getListPartieSpecialise() != null ?
                                prescriptionDiagnostique.getListPartieSpecialise().stream()
                                        .map(PartieSpecialise::getId)
                                        .collect(Collectors.toList()) :
                                Collections.emptyList()
                )
                .build();
    }
}
