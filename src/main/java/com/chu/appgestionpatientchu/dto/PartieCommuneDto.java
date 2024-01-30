package com.chu.appgestionpatientchu.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class PartieCommuneDto {

    private Long id ;

    private List<DiagnostiqueConnueDto> listDiagnostiqueConnue ;


    private List<AllergieDto> listAllergie;



    private List<MedicationEncoursDto> listMedicationEncours ;

    private List<SpecificiteDto> listSpecificite ;
    private boolean grossesFemme ;


}
