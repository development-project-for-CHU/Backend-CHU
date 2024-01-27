package com.chu.appgestionpatientchu.mappers;

import com.chu.appgestionpatientchu.domain.Patient;
import com.chu.appgestionpatientchu.domain.Visite;
import com.chu.appgestionpatientchu.dto.PatientDto;
import com.chu.appgestionpatientchu.dto.VisiteDto;

public class VisiteMapper {
    public static Visite mapToVisite(VisiteDto visiteDto){

        return Visite.builder()
                .dateVisite(visiteDto.getDateVisite())
                .build();
}
public static VisiteDto mapToVisitedto(Visite visite){
        return VisiteDto.builder()
                .dateVisite(visite.getDateVisite())
                .build();


}
}
