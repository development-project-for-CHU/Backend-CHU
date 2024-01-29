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
@Table(name = "PRESCRIPTION_THERAPEUTIQUE")
public class PrescriptionTherapeutique {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private boolean isSelected;

    private boolean isPassedToCommune;

    @Temporal(value = TemporalType.DATE)
    private Date addedAt;
    @ManyToMany
    private List<PartieSpecialise> listPartieSpecialise ;

    @NonNull
    private boolean isDeleted  ;

    public boolean getIsPassedToCommune() {
        return isPassedToCommune;
    }

    public void setIsPassedToCommune(boolean passedToCommune) {
        isPassedToCommune = passedToCommune;
    }
}
