package com.chu.appgestionpatientchu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class DiagnostiqueNiveauSuperieurDto {

    private Long id;

    private String nomDiagnostiqueNiveauSuperieur;

    private boolean isPassedToCommune;


    private Date addedAt;

    private List<PartieSpecialiseDto> listPartieSpecialise ;

}