package com.chu.appgestionpatientchu.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

public class VisiteDto {

    private Long id ;

    private Date dateVisite ;
    private DossierPatientDto dossierPatient ;
    private PartieCommuneDto partieCommune ;
    private PartieSpecialiseDto partieSpecialise ;
}
