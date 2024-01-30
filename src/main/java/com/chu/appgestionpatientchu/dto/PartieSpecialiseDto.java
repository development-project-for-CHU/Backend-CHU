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
public class PartieSpecialiseDto {

    private List<Long> listAnamnese;

    private List<Long> listExamenClinique;

    private List<Long> listPrescriptionDiagnostique;

    private List<Long> listDiagnostiqueNiveauSuperieur;

    private List<Long> listPrescriptionTherapeutique;

    private List<Long> listSurveillance;
}
