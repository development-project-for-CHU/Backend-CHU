package com.chu.appgestionpatientchu.mappers.partieCommune;


import com.chu.appgestionpatientchu.domain.DiagnostiqueConnue;
import com.chu.appgestionpatientchu.dto.DiagnostiqueConnueDto;
import com.chu.appgestionpatientchu.domain.PartieCommune;


import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;

public class MapperDiagnostiqueConnue {

    public static DiagnostiqueConnue mapToDiagnostiqueConnue(DiagnostiqueConnueDto diagnostiqueConnueDto) {
        return DiagnostiqueConnue.builder()
                .nomDiagnostiqueConnue(diagnostiqueConnueDto.getNomDiagnostiqueConnue())
                .addedAt(new Date())
                .isDeleted(false)
                .build();
    }

    public static DiagnostiqueConnueDto mapToDiagnostiqueConnueDto (DiagnostiqueConnue diagnostiqueConnue){
        return DiagnostiqueConnueDto.builder()
                .nomDiagnostiqueConnue(diagnostiqueConnue.getNomDiagnostiqueConnue())
                .id(diagnostiqueConnue.getId())
                .addedAt(diagnostiqueConnue.getAddedAt())
                .listIdPartieCommune(
                        diagnostiqueConnue.getListPartieCommune() != null ?
                                diagnostiqueConnue.getListPartieCommune().stream()
                                        .map(PartieCommune::getId)
                                        .collect(Collectors.toList()) :
                                Collections.emptyList()
                )
                .build();
    }




}
