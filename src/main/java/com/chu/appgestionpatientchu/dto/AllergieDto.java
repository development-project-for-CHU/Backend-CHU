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

public class AllergieDto {

    private Long id ;
    private String  nomAllergie ;


    private Date addedAt ;


    private List<PartieCommuneDto> ListPartieCommune ;
}
