package com.chu.appgestionpatientchu.dto;

import jakarta.persistence.*;
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

public class AnamneseDto {

    private Long id;

    private String nomAnamnese;

    private boolean isPassedToCommune;

    private Date addedAt;

    private List<PartieSpecialiseDto> listPartieSpecialise ;

}