package com.chu.appgestionpatientchu.domain;
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
@Entity
@Table(name = "EXAMEN_CLINIQUE")
public class ExamenClinique {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomExamenClinique;


    private boolean isPassedToCommune;

    @Temporal(value = TemporalType.DATE)
    private Date addedAt;
    @ManyToMany
    private List<PartieSpecialise> listPartieSpecialise ;

}