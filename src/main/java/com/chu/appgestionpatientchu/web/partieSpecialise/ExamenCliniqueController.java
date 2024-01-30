package com.chu.appgestionpatientchu.web.partieSpecialise;


import com.chu.appgestionpatientchu.dto.ExamenCliniqueDto;
import com.chu.appgestionpatientchu.dto.SearchExamenCliniqueRequest;
import com.chu.appgestionpatientchu.exceptions.EmptyParamArrayException;
import com.chu.appgestionpatientchu.services.partieSpecialise.ExamenCliniqueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/categories/partieSpecialise/exemanClinique")
public class ExamenCliniqueController {


    private final ExamenCliniqueService examenCliniqueService;

    @PostMapping()
    public ResponseEntity<List<Long>> addListOfExamenClinique(@RequestBody List<ExamenCliniqueDto> examenCliniqueDtoList){
        if (examenCliniqueDtoList == null || examenCliniqueDtoList.isEmpty()) {
            throw new EmptyParamArrayException("The list of exeman cannot be null or empty.");
        }
        List<Long> savedExamenCliniqueId = this.examenCliniqueService.saveAll(examenCliniqueDtoList)
                .stream()
                .map(ExamenCliniqueDto::getId)
                .collect(Collectors.toList());
        return new ResponseEntity<>(savedExamenCliniqueId , HttpStatus.CREATED);
    }


    @GetMapping()
    public ResponseEntity<List<ExamenCliniqueDto>> getListOfExamenClinique(
            @RequestParam(name = "size" , defaultValue = "5") Integer size ,
            @RequestParam(name = "page" , defaultValue = "0") Integer page
    ){

        List<ExamenCliniqueDto> examenCliniqueDtoList = this.examenCliniqueService.findAll(size,page);
        return new ResponseEntity<>(examenCliniqueDtoList , HttpStatus.OK);
    }


    @GetMapping("/search")
    public ResponseEntity<List<ExamenCliniqueDto>> searchExamenClinique(SearchExamenCliniqueRequest searchRequest) {
        searchRequest.validate();
        List<ExamenCliniqueDto> examenCliniqueDtos = this.examenCliniqueService
                .findExamenCliniqueByNameOrCreationDate(searchRequest.getName(), searchRequest.getCreationDate());
        return new ResponseEntity<>(examenCliniqueDtos, HttpStatus.OK);
    }




    @PutMapping("/{id}")
    public  ResponseEntity<ExamenCliniqueDto> updateExamenClinique(
            @PathVariable Long id ,
            @RequestBody ExamenCliniqueDto examenCliniqueDto
    ){
        ExamenCliniqueDto updatedExamenClinique = this.examenCliniqueService.updateExamenClinique(id , examenCliniqueDto);
        return new ResponseEntity<>(updatedExamenClinique , HttpStatus.OK);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteExamenClinique(@PathVariable Long id) {
        ExamenCliniqueDto deleteExamenClinique =  examenCliniqueService.deleteExamenClinique(id);
        return new ResponseEntity<>(deleteExamenClinique.getId() , HttpStatus.OK);
    }


}
