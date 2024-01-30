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

public class PartieSpecialiseDto {

    private Long id;

    private List<Long> listAnamnese;

    private List<Long> listExamenClinique;

    private List<Long> listPrescriptionDiagnostique;

    private List<Long> listDiagnostiqueNiveauSuperieur;

    private List<Long> listPrescriptionTherapeutique;

    private List<Long> listSurveillance;
}
