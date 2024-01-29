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
@Table(name = "PARTIE_COMMUNE")
public class PartieCommune {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "DIAGNOSTIQUE_CONNUE_PARTIE_CONNUE")
    private List<DiagnostiqueConnue> listDiagnostiqueConnue ;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "ALLERGIE_PARTIE_COMMUNE")
    private List<Allergie> listAllergie;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "SPECIALITE_PARTIE_COMMUNE")
    private  List<Specialite> listSpecialite ;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "MEDICATION_ENCOURS_PARTIE_COMMUNE")
    private List<MedicationEncours> listMedicationEncours ;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "SPECIFICITE_PARTIE_COMMUNE")
    private List<Specificite> listSpecificite ;

    private boolean grossesFemme ;




}
