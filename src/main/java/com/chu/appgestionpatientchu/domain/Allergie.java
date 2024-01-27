package com.chu.appgestionpatientchu.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor(force = true)
@Data
@Builder
@Entity
@Table(name = "ALLERGIE")
public class Allergie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String  nomAllergie ;

    @Temporal(value = TemporalType.DATE)
    private Date addedAt ;


    @ManyToMany(mappedBy = "listAllergie")
    private List<PartieCommune> ListPartieCommune ;

    // the following field is used for fake deletion
    @NonNull
    private boolean isDeleted  ;
}
