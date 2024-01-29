package com.chu.appgestionpatientchu.web.partieCommune;


import com.chu.appgestionpatientchu.dto.AllergieDto;
import com.chu.appgestionpatientchu.dto.SearchAllergieRequest;
import com.chu.appgestionpatientchu.dto.SearchSpecificiteRequest;
import com.chu.appgestionpatientchu.dto.SpecificiteDto;
import com.chu.appgestionpatientchu.exceptions.EmptyParamArrayException;
import com.chu.appgestionpatientchu.services.partieCommune.AllergieService;
import com.chu.appgestionpatientchu.services.partieCommune.SpecificiteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories/partieCommune/specificite")
public class SpecificiteController {



    private final SpecificiteService specificiteService;

    @PostMapping()
    public ResponseEntity<List<Long>> addListOfSpecificite(@RequestBody List<SpecificiteDto> specificiteDtoList){
        if (specificiteDtoList == null || specificiteDtoList.isEmpty()) {
            throw new EmptyParamArrayException("The list of Specificite cannot be null or empty.");
        }
        List<Long> savedSpecificiteId = this.specificiteService.saveAll(specificiteDtoList)
                .stream()
                .map(SpecificiteDto::getId)
                .collect(Collectors.toList());
        return new ResponseEntity<>(savedSpecificiteId , HttpStatus.CREATED);
    }
    @GetMapping()
    public ResponseEntity<List<SpecificiteDto>> getListOfAllergies(
            @RequestParam(name = "size" , defaultValue = "5") Integer size ,
            @RequestParam(name = "page" , defaultValue = "0") Integer page
    ){

        List<SpecificiteDto> specificiteDtoList = this.specificiteService.findAll(size,page);
        return new ResponseEntity<>(specificiteDtoList , HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<SpecificiteDto>> searchSpecificit(SearchSpecificiteRequest searchRequest) {
        searchRequest.validate();  // Validate the search criteria ==> throw the InvalidSearchCriteriaException
        List<SpecificiteDto> specificiteDtos = this.specificiteService
                .findSpecificiteByNameOrCreationDate(searchRequest.getName(), searchRequest.getCreationDate());
        return new ResponseEntity<>(specificiteDtos, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<SpecificiteDto> updateSpecificite(
            @PathVariable Long id ,
            @RequestBody SpecificiteDto specificiteDto
    ){
        SpecificiteDto updatedSpecificite = this.specificiteService.updateSpecificite(id ,specificiteDto);
        return new ResponseEntity<>(updatedSpecificite , HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteSpecificite(@PathVariable Long id) {
        SpecificiteDto deletedSpecificite =  specificiteService.deleteSpecificite(id);
        return new ResponseEntity<>(deletedSpecificite.getId() , HttpStatus.OK);
    }
}



