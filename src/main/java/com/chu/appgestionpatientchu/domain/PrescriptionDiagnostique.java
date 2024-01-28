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
@Table(name = "PRESCRIPTION_DIAGNOSTIQUE")
public class PrescriptionDiagnostique {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomPrescriptionDiagnostique;


    private boolean isPassedToCommune;

    @Temporal(value = TemporalType.DATE)
    private Date addedAt;
    @ManyToMany
    private List<PartieSpecialise> listPartieSpecialise ;

    @NonNull
    private boolean isDeleted  ;

    public void setIsPassedToCommune(boolean passedToCommune) {
        isPassedToCommune = passedToCommune;
    }

    public boolean getIsPassedToCommune() {
        return isPassedToCommune;
    }
}
