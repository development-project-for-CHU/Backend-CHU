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
@Table(name = "SPECIFICITE")
public class Specificite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String  name ;

    @Temporal(value = TemporalType.DATE)
    private Date addedAt ;


    @ManyToMany(mappedBy = "listSpecificite")
    private List<PartieCommune> ListPartieCommune ;

    @NonNull
    private boolean isDeleted  ;
}
