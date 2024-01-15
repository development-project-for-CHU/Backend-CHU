package com.chu.appgestionpatientchu.dto;


import jakarta.validation.constraints.NotBlank;
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

public class AllergieDto {

    private Long id ;
    @NotBlank(message = "The allergy name must not be blank")
    private String  nomAllergie ;
    private Date addedAt;
    private List<Long> listIdPartieCommune ;
}
