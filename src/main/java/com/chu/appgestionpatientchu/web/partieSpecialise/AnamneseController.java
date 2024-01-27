package com.chu.appgestionpatientchu.web.partieSpecialise;


import com.chu.appgestionpatientchu.dto.AnamneseDto;
import com.chu.appgestionpatientchu.dto.SearchAnamneseRequest;
import com.chu.appgestionpatientchu.exceptions.EmptyParamArrayException;

import com.chu.appgestionpatientchu.services.partieSpecialise.AnamneseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories/partieSpecialise/anamnese")
public class AnamneseController {


    private final AnamneseService anamneseService;

    @PostMapping()
    public ResponseEntity<List<Long>> addListOfAnamnese(@RequestBody List<AnamneseDto> anamneseDtoList){
        if (anamneseDtoList == null || anamneseDtoList.isEmpty()) {
            throw new EmptyParamArrayException("The list of anamnese cannot be null or empty.");
        }
        List<Long> savedAnamneseId = this.anamneseService.saveAll(anamneseDtoList)
                .stream()
                .map(AnamneseDto::getId)
                .collect(Collectors.toList());
        return new ResponseEntity<>(savedAnamneseId , HttpStatus.CREATED);
    }



    @GetMapping()
    public ResponseEntity<List<AnamneseDto>> getListOfAnamneses(
            @RequestParam(name = "size" , defaultValue = "5") Integer size ,
            @RequestParam(name = "page" , defaultValue = "0") Integer page
    ){

        List<AnamneseDto> anamneseDtoList = this.anamneseService.findAll(size,page);
        return new ResponseEntity<>(anamneseDtoList , HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<AnamneseDto>> searchAnamneses(SearchAnamneseRequest searchRequest) {
        searchRequest.validate();
        List<AnamneseDto> anamneseDtos = this.anamneseService
                .findAnamneseByNameOrCreationDate(searchRequest.getName(), searchRequest.getCreationDate());
        return new ResponseEntity<>(anamneseDtos, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<AnamneseDto> updateAnamnese(
            @PathVariable Long id ,
            @RequestBody AnamneseDto anamneseDto
    ){
        AnamneseDto updatedAnamnese = this.anamneseService.updateAnamnese(id ,anamneseDto);
        return new ResponseEntity<>(updatedAnamnese , HttpStatus.OK);
    }

 /*   @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteAnamneses(@PathVariable Long id) {
        AnamneseDto deleteAnamnese =  anamneseService.deleteAnamnese(id);
        return new ResponseEntity<>(deleteAnamnese.getId() , HttpStatus.OK);
    }

  */

}
