package com.chu.appgestionpatientchu.dto;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class PrescriptionTherapeutiqueDto {

    private Long id;

    private String name;
    private boolean isSelected;

    private boolean isPassedToCommune;


    private Date addedAt;
    private List<Long> listIdPartieSpecialise ;

    public boolean getIsPassedToCommune() {
        return isPassedToCommune;
    }

    public void setIsPassedToCommune(boolean passedToCommune) {
        isPassedToCommune = passedToCommune;
    }
}
