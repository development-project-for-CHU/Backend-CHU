package com.chu.appgestionpatientchu.mappers.partieSpecialise;

import com.chu.appgestionpatientchu.domain.Allergie;
import com.chu.appgestionpatientchu.domain.Anamnese;
import com.chu.appgestionpatientchu.domain.PartieCommune;
import com.chu.appgestionpatientchu.domain.PartieSpecialise;

import com.chu.appgestionpatientchu.dto.AllergieDto;
import com.chu.appgestionpatientchu.dto.AnamneseDto;

import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;

public class MapperAnamnese {


    public static Anamnese mapToAnamnese(AnamneseDto anamneseDto){
        return Anamnese.builder()
                .nomAnamnese(anamneseDto.getNomAnamnese())
                .addedAt(new Date())
                .isPassedToCommune(false)
                .build();
    }
    public static AnamneseDto mapToAnamneseDto(Anamnese anamnese){
        return AnamneseDto.builder()
                .nomAnamnese(anamnese.getNomAnamnese())
                .id(anamnese.getId())
                .isPassedToCommune(anamnese.getIsPassedToCommune())
                .addedAt(anamnese.getAddedAt())
                .listIdPartieSpecialise(
                        anamnese.getListPartieSpecialise() != null ?
                                anamnese.getListPartieSpecialise().stream()
                                        .map(PartieSpecialise::getId)
                                        .collect(Collectors.toList()) :
                                Collections.emptyList()
                )
                .build();
    }
}


