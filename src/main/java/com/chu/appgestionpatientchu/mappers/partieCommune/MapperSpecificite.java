package com.chu.appgestionpatientchu.mappers.partieCommune;

import com.chu.appgestionpatientchu.domain.PartieCommune;
import com.chu.appgestionpatientchu.domain.Specificite;
import com.chu.appgestionpatientchu.dto.SpecificiteDto;

import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;

public class MapperSpecificite {

    public static Specificite mapToSpecificite(SpecificiteDto specificiteDto){
        return Specificite.builder()
                .name(specificiteDto.getName())
                .addedAt(new Date())
                .isDeleted(false)
                .build();
    }
    public static SpecificiteDto mapToSpecificiteDto (Specificite specificite){
        return SpecificiteDto.builder()
                .name(specificite.getName())
                .id(specificite.getId())
                .addedAt(specificite.getAddedAt())
                .listIdPartieCommune(
                        specificite.getListPartieCommune() != null ?
                                specificite.getListPartieCommune().stream()
                                        .map(PartieCommune::getId)
                                        .collect(Collectors.toList()) :
                                Collections.emptyList()
                )
                .build();
    }
}
