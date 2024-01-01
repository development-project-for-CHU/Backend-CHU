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
@Table(name = "SURVEILLANCE")
public class Surveillance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomSurveillance;

    private boolean isPassedToCommune;

    @Temporal(value = TemporalType.DATE)
    private Date addedAt;
    @ManyToMany
    private List<PartieSpecialise> listPartieSpecialise ;



}

