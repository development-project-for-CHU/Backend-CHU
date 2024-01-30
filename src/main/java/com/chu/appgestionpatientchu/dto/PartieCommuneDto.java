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

    private List<Long> allergies;

    private List<Long> diagnostiqueConnus;

    private List<Long> medicamentEnCours;

    private List<Long> specificite;
}
