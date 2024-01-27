package com.chu.appgestionpatientchu.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor(force = true)

@Builder
@Entity
@Table(name = "ANAMNESE")
public class Anamnese {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomAnamnese;

    private boolean isPassedToCommune;
    @Temporal(value = TemporalType.DATE)
    private Date addedAt;
    @ManyToMany
    private List<PartieSpecialise> listPartieSpecialise ;

    @NonNull
    private boolean isDeleted  ;

}
