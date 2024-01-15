package com.chu.appgestionpatientchu.web.partieCommune;

import com.chu.appgestionpatientchu.dto.AllergieDto;
import com.chu.appgestionpatientchu.dto.SearchAllergieRequest;
import com.chu.appgestionpatientchu.exceptions.EmptyParamArrayException;
import com.chu.appgestionpatientchu.services.partieCommune.AllergieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories/partieCommune/allergies")
public class AllergieController {
    private final AllergieService allergieService;

    @PostMapping()
    public ResponseEntity<List<Long>> addListOfAllergies(@RequestBody List<AllergieDto> allergieDtoList){
        if (allergieDtoList == null || allergieDtoList.isEmpty()) {
            throw new EmptyParamArrayException("The list of allergies cannot be null or empty.");
        }
        List<Long> savedAllergiesId = this.allergieService.saveAll(allergieDtoList)
                                        .stream()
                                        .map(AllergieDto::getId)
                                        .collect(Collectors.toList());
        return new ResponseEntity<>(savedAllergiesId , HttpStatus.CREATED);
    }
    @GetMapping()
    public ResponseEntity<List<AllergieDto>> getListOfAllergies(
            @RequestParam(name = "size" , defaultValue = "5") Integer size ,
            @RequestParam(name = "page" , defaultValue = "0") Integer page
            ){

        List<AllergieDto> allergieDtoList = this.allergieService.findAll(size,page);
        return new ResponseEntity<>(allergieDtoList , HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<AllergieDto>> searchAllergies(SearchAllergieRequest searchRequest) {
        searchRequest.validate();  // Validate the search criteria ==> throw the InvalidSearchCriteriaException
        List<AllergieDto> allergieDtos = this.allergieService
                .findAllergiessByNameOrCreationDate(searchRequest.getName(), searchRequest.getCreationDate());
        return new ResponseEntity<>(allergieDtos, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<AllergieDto> updateAllergie(
            @PathVariable Long id ,
            @RequestBody AllergieDto allergieDto
    ){
        AllergieDto updatedAllergie = this.allergieService.updateAllergie(id ,allergieDto);
        return new ResponseEntity<>(updatedAllergie , HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteAllergy(@PathVariable Long id) {
        AllergieDto deletedAllergie =  allergieService.deleteAllergie(id);
        return new ResponseEntity<>(deletedAllergie.getId() , HttpStatus.OK);
    }
}
