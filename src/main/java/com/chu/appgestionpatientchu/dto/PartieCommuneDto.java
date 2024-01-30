package com.chu.appgestionpatientchu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PartieCommuneDto {

    private List<Long> allergies;

    private List<Long> diagnostiqueConnus;

    private List<Long> medicamentEnCours;

    private List<Long> specificite;
}
