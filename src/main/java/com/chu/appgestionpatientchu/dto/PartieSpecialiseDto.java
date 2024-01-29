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


    private List<AnamneseDto> listAnamnese;


    private List<ExamenCliniqueDto> listExamenClinique;
    private List<PrescriptionDiagnostiqueDto> listPrescriptionDiagnostique;
    private List<DiagnostiqueNiveauSuperieurDto> listDiagnostiqueNiveauSuperieur;
    private List<PrescriptionTherapeutiqueDto> listPrescriptionTherapeutique;
    private List<SurveillanceDto> listSurveillance;

}
