package com.chu.appgestionpatientchu.mappers;

import com.chu.appgestionpatientchu.domain.Allergie;
import com.chu.appgestionpatientchu.dto.AllergieDto;


import java.util.Objects;
import java.util.stream.Collectors;

public class AllergieMapper {



    public static Allergie mapToEntity (AllergieDto allergieDto){
        if (Objects.isNull(allergieDto)) {
            return null;
        }
        return Allergie.builder()
                .id(allergieDto.getId())
                .nomAllergie(allergieDto.getNomAllergie())
                .addedAt(allergieDto.getAddedAt())
                .build();

    }
    public static AllergieDto mapToDto (Allergie allergie){
        if (Objects.isNull(allergie)) {
            return null;
        }
        return AllergieDto.builder()
                .id(allergie.getId())
                .nomAllergie(allergie.getNomAllergie())
                .addedAt(allergie.getAddedAt())
                .build();

    }

}
