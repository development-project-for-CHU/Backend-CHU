package com.chu.appgestionpatientchu.domain;

import jakarta.persistence.*;
import lombok.*;

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
    private String name ;
    @Temporal(value = TemporalType.DATE)
    private Date addedAt ;

    @NonNull
    private boolean isDeleted  ;
    @ManyToMany(mappedBy = "listMedicationEncours")
    private List<PartieCommune> ListPartieCommune ;
}
