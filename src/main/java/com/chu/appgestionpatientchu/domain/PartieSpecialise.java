package com.chu.appgestionpatientchu.domain;

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
@Entity
@Table(name = "PARTIE_SPECIALISE")
public class PartieSpecialise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "ANAMNESE_PARTIE_SPECIALISE")
    private List<Anamnese> listAnamnese;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "EXAMEN_CLINIQUE_PARTIE_SPECIALISE")
    private List<ExamenClinique> listExamenClinique;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "PRESCRITPION_DIAGNOSTIQUE_PARTIE_SPECIALISE")
    private List<PrescriptionDiagnostique> listPrescriptionDiagnostique;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "DIAGNOSTIQUE_NIVEAU_SUPERIEUR_PARTIE_SPECIALISE")
    private List<DiagnostiqueNiveauSuperieur> listDiagnostiqueNiveauSuperieur;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "PRESCRITPION_THERAPEUTIQUE_PARTIE_SPECIALISE")
    private List<PrescriptionTherapeutique> listPrescriptionTherapeutique;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "SURVEILLANCE_PARTIE_SPECIALISE")
    private List<Surveillance> listSurveillance;

}
