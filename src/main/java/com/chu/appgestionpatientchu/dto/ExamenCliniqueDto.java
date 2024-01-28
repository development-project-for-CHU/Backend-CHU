package com.chu.appgestionpatientchu.dto;
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

public class ExamenCliniqueDto {

    private Long id;

    private String nomExamenClinique;


    private boolean isPassedToCommune;


    private Date addedAt;

    private List<Long> listIdPartieSpecialise ;

    public void setIsPassedToCommune(boolean passedToCommune) {
        isPassedToCommune = passedToCommune;
    }

    public boolean getIsPassedToCommune() {
        return isPassedToCommune;
    }

}
