package com.chu.appgestionpatientchu.mappers.partieSpecialise;


import com.chu.appgestionpatientchu.domain.DiagnostiqueNiveauSuperieur;
import com.chu.appgestionpatientchu.domain.PartieSpecialise;
import com.chu.appgestionpatientchu.dto.DiagnostiqueNiveauSuperieurDto;

import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;

public class MapperDiagnostiqueNiveauSuperieur {

    public static DiagnostiqueNiveauSuperieur mapToDiagnostiqueNiveauSuperieur(DiagnostiqueNiveauSuperieurDto diagnostiqueNiveauSuperieurDto){
        return DiagnostiqueNiveauSuperieur.builder()
                .name(diagnostiqueNiveauSuperieurDto.getName())
                .addedAt(new Date())
                .isPassedToCommune(false)
                .build();
    }
    public static DiagnostiqueNiveauSuperieurDto mapToDiagnostiqueNiveauSuperieurDto(DiagnostiqueNiveauSuperieur diagnostiqueNiveauSuperieur){
        return DiagnostiqueNiveauSuperieurDto.builder()
                .name(diagnostiqueNiveauSuperieur.getName())
                .id(diagnostiqueNiveauSuperieur.getId())
                .isPassedToCommune(diagnostiqueNiveauSuperieur.getIsPassedToCommune())
                .addedAt(diagnostiqueNiveauSuperieur.getAddedAt())
                .listIdPartieSpecialise(
                        diagnostiqueNiveauSuperieur.getListPartieSpecialise() != null ?
                                diagnostiqueNiveauSuperieur.getListPartieSpecialise().stream()
                                        .map(PartieSpecialise::getId)
                                        .collect(Collectors.toList()) :
                                Collections.emptyList()
                )
                .build();
    }
}
