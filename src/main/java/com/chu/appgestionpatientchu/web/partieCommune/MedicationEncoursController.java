package com.chu.appgestionpatientchu.web.partieCommune;



import com.chu.appgestionpatientchu.dto.MedicationEncoursDto;
import com.chu.appgestionpatientchu.dto.SearchMedicamentRequest;
import com.chu.appgestionpatientchu.exceptions.EmptyParamArrayException;
import com.chu.appgestionpatientchu.services.partieCommune.MedicationEncoursService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/categories/partieCommune/medication")
public class MedicationEncoursController {

    private final MedicationEncoursService medicationEncoursService;

    @PostMapping()
    public ResponseEntity<List<Long>> addListOfMedications(@RequestBody List<MedicationEncoursDto> medicamentsDtoList){
        if (medicamentsDtoList == null || medicamentsDtoList.isEmpty()) {
            throw new EmptyParamArrayException("The list of medicaments cannot be null or empty.");
        }
        List<Long> savedMedacmentsId = this.medicationEncoursService.saveAll(medicamentsDtoList)
                .stream()
                .map(MedicationEncoursDto::getId)
                .collect(Collectors.toList());
        return new ResponseEntity<>(savedMedacmentsId , HttpStatus.CREATED);
    }
    @GetMapping()
    public ResponseEntity<List<MedicationEncoursDto>> getListOfMedicaments(
            @RequestParam(name = "size" , defaultValue = "5") Integer size ,
            @RequestParam(name = "page" , defaultValue = "0") Integer page
    ){

        List<MedicationEncoursDto> medicationEncoursDtoList = this.medicationEncoursService.findAll(size,page);
        return new ResponseEntity<>(medicationEncoursDtoList , HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<MedicationEncoursDto>> searchMedicaments(SearchMedicamentRequest searchRequest) {
        searchRequest.validate();  // Validate the search criteria ==> throw the InvalidSearchCriteriaException
        List<MedicationEncoursDto> medicationEncoursDtos = this.medicationEncoursService
                .findMedicationEncoursByNameOrCreationDate(searchRequest.getName(), searchRequest.getCreationDate());
        return new ResponseEntity<>(medicationEncoursDtos, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<MedicationEncoursDto> updateMedicament(
            @PathVariable Long id ,
            @RequestBody MedicationEncoursDto medicationEncoursDto
    ){
        MedicationEncoursDto updatedMedicament = this.medicationEncoursService.updateMedicationEncours(id ,medicationEncoursDto);
        return new ResponseEntity<>(updatedMedicament , HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteMedicament(@PathVariable Long id) {
        MedicationEncoursDto deletedMedicament =  medicationEncoursService.deleteMedicationEncours(id);
        return new ResponseEntity<>(deletedMedicament.getId() , HttpStatus.OK);
    }
}
