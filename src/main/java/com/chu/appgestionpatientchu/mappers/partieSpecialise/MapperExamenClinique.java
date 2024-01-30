package com.chu.appgestionpatientchu.mappers.partieSpecialise;

import com.chu.appgestionpatientchu.domain.ExamenClinique;
import com.chu.appgestionpatientchu.domain.PartieSpecialise;
import com.chu.appgestionpatientchu.dto.ExamenCliniqueDto;

import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;

public class MapperExamenClinique {


    public static ExamenClinique mapToExamenClinique(ExamenCliniqueDto examenCliniqueDto){
        return ExamenClinique.builder()
                .name(examenCliniqueDto.getName())
                .addedAt(new Date())
                .isPassedToCommune(false)
                .build();
    }
    public static ExamenCliniqueDto mapToExamenCliniqueDto(ExamenClinique examenClinique){
        return ExamenCliniqueDto.builder()
                .name(examenClinique.getName())
                .id(examenClinique.getId())
                .isPassedToCommune(examenClinique.getIsPassedToCommune())
                .addedAt(examenClinique.getAddedAt())
                .listIdPartieSpecialise(
                        examenClinique.getListPartieSpecialise() != null ?
                                examenClinique.getListPartieSpecialise().stream()
                                        .map(PartieSpecialise::getId)
                                        .collect(Collectors.toList()) :
                                Collections.emptyList()
                )
                .build();
    }
}
