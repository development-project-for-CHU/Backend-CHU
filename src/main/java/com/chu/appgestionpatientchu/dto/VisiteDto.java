package com.chu.appgestionpatientchu.dto;


import com.chu.appgestionpatientchu.domain.DossierPatient;
import com.chu.appgestionpatientchu.domain.PartieCommune;
import com.chu.appgestionpatientchu.domain.PartieSpecialise;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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

    private long dossierPatientid ;


    private long partieCommuneid ;

    private long partieSpecialiseid ;
}
