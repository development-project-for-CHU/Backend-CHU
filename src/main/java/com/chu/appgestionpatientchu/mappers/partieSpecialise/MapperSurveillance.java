package com.chu.appgestionpatientchu.mappers.partieSpecialise;


import com.chu.appgestionpatientchu.domain.PartieSpecialise;
import com.chu.appgestionpatientchu.domain.Surveillance;
import com.chu.appgestionpatientchu.dto.SurveillanceDto;

import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;

public class MapperSurveillance {

    public static Surveillance mapToSurveillance(SurveillanceDto surveillanceDto){
        return Surveillance.builder()
                .nomSurveillance(surveillanceDto.getNomSurveillance())
                .addedAt(new Date())
                .isPassedToCommune(false)
                .build();
    }
    public static SurveillanceDto mapToSurveillanceDto(Surveillance surveillance){
        return SurveillanceDto.builder()
                .nomSurveillance(surveillance.getNomSurveillance())
                .id(surveillance.getId())
                .isPassedToCommune(surveillance.getIsPassedToCommune())
                .addedAt(surveillance.getAddedAt())
                .listIdPartieSpecialise(
                        surveillance.getListPartieSpecialise() != null ?
                                surveillance.getListPartieSpecialise().stream()
                                        .map(PartieSpecialise::getId)
                                        .collect(Collectors.toList()) :
                                Collections.emptyList()
                )
                .build();
    }
}
