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
                .name(allergieDto.getName())
                .addedAt(new Date())
                .isDeleted(false)
                .build();
    }
    public static AllergieDto mapToAllergieDto (Allergie allergie){
        return AllergieDto.builder()
                .name(allergie.getName())
                .id(allergie.getId())
                .addedAt(allergie.getAddedAt())
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
