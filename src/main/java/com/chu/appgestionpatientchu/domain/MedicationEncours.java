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
@Table(name = "MEDICATION_ENCOURS")
public class MedicationEncours {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String nomMedicament ;
    @Temporal(value = TemporalType.DATE)
    private Date addedAt ;
    @ManyToMany(mappedBy = "listMedicationEncours")
    private List<PartieCommune> ListPartieCommune ;
}