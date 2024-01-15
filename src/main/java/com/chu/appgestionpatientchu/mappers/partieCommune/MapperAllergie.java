package com.chu.appgestionpatientchu.mappers.partieCommune;

import com.chu.appgestionpatientchu.domain.Allergie;
import com.chu.appgestionpatientchu.domain.PartieCommune;
import com.chu.appgestionpatientchu.dto.AllergieDto;

import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;

public class MapperAllergie {
    public static Allergie mapToAllergie(AllergieDto allergieDto){
        return Allergie.builder()
                .nomAllergie(allergieDto.getNomAllergie())
                .addedAt(new Date())
                .isDeleted(false)
                .build();
    }
    public static AllergieDto mapToAllergieDto (Allergie allergie){
        return AllergieDto.builder()
                .nomAllergie(allergie.getNomAllergie())
                .id(allergie.getId())
                .listIdPartieCommune(
                        allergie.getListPartieCommune() != null ?
                                allergie.getListPartieCommune().stream()
                                        .map(PartieCommune::getId)
                                        .collect(Collectors.toList()) :
                                Collections.emptyList()
                )
                .build();
    }
}
