package com.chu.appgestionpatientchu.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Builder
@Table(name = "VISITE")
public class Visite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    @Temporal(value = TemporalType.TIMESTAMP )
    private Date dateVisite ;
    @ManyToOne
    @JoinColumn(name = "dossier_patient_id")
    private DossierPatient dossierPatient ;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PARTIE_COMMUN_ID")
    private PartieCommune partieCommune ;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PARTIE_SPECIALISE_ID")
    private PartieSpecialise partieSpecialise ;
}
