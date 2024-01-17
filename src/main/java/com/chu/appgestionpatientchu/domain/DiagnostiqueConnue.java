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
@Table(name = "DIAGNOSTIQUE_CONNUE")
public class DiagnostiqueConnue {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id ;
     private String nomDiagnostiqueConnue ;
     @Temporal(value = TemporalType.DATE)
     private Date addedAt;

     @NonNull
     private boolean isDeleted  ;
     @ManyToMany(mappedBy = "listDiagnostiqueConnue")
     private List<PartieCommune> listPartieCommune ;
}
