package com.chu.appgestionpatientchu.web.partieSpecialise;


import com.chu.appgestionpatientchu.dto.AnamneseDto;
import com.chu.appgestionpatientchu.dto.PrescriptionTherapeutiqueDto;
import com.chu.appgestionpatientchu.dto.SearchSurveillanceRequest;
import com.chu.appgestionpatientchu.dto.SurveillanceDto;
import com.chu.appgestionpatientchu.exceptions.EmptyParamArrayException;
import com.chu.appgestionpatientchu.services.partieSpecialise.AnamneseService;
import com.chu.appgestionpatientchu.services.partieSpecialise.SurveillanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories/partieSpecialise/surveillance")
public class SurveillanceController {


    private final SurveillanceService surveillanceService;

    @PostMapping()
    public ResponseEntity<List<Long>> addListOfSurveillance(@RequestBody List<SurveillanceDto> surveillanceDtoList){
        if (surveillanceDtoList == null || surveillanceDtoList.isEmpty()) {
            throw new EmptyParamArrayException("The list of Surveillance cannot be null or empty.");
        }
        List<Long> savedSurveillanceId = this.surveillanceService.saveAll(surveillanceDtoList)
                .stream()
                .map(SurveillanceDto::getId)
                .collect(Collectors.toList());
        return new ResponseEntity<>(savedSurveillanceId , HttpStatus.CREATED);
    }



    @GetMapping()
    public ResponseEntity<List<SurveillanceDto>> getListOfSurveillance(
            @RequestParam(name = "size" , defaultValue = "5") Integer size ,
            @RequestParam(name = "page" , defaultValue = "0") Integer page
    ){

        List<SurveillanceDto> surveillanceDtoList = this.surveillanceService.findAll(size,page);
        return new ResponseEntity<>(surveillanceDtoList , HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<SurveillanceDto>> searchSurveillance(SearchSurveillanceRequest searchRequest) {
        searchRequest.validate();
        List<SurveillanceDto> surveillanceDtos = this.surveillanceService
                .findSurveillanceByNameOrCreationDate(searchRequest.getName(), searchRequest.getCreationDate());
        return new ResponseEntity<>(surveillanceDtos, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<SurveillanceDto> updateSurveillance(
            @PathVariable Long id ,
            @RequestBody SurveillanceDto surveillanceDto
    ){
        SurveillanceDto updatedSurveillance = this.surveillanceService.updateSurveillance(id ,surveillanceDto);
        return new ResponseEntity<>(updatedSurveillance , HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteSurveillance(@PathVariable Long id) {
        SurveillanceDto deleteSurveillance=  surveillanceService.deleteSurveillance(id);
        return new ResponseEntity<>(deleteSurveillance.getId() , HttpStatus.OK);
    }


}
