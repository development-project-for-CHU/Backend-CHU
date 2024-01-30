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
public class SpecificiteDto {

    private Long id ;
    @NotBlank(message = "The  Specificite name must not be blank")
    private String  name ;
    private Date addedAt;
    private List<Long> listIdPartieCommune ;
}
