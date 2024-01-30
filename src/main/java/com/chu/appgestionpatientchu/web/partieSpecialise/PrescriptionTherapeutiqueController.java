package com.chu.appgestionpatientchu.web.partieSpecialise;



import com.chu.appgestionpatientchu.dto.PrescriptionTherapeutiqueDto;
import com.chu.appgestionpatientchu.dto.SearchPrescriptionTherapeutiqueRequest;
import com.chu.appgestionpatientchu.exceptions.EmptyParamArrayException;
import com.chu.appgestionpatientchu.services.partieSpecialise.PrescriptionTherapeutiqueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/categories/partieSpecialise/prescriptionTherapeutique")
public class PrescriptionTherapeutiqueController {


    private final PrescriptionTherapeutiqueService prescriptionTherapeutiqueService;

    @PostMapping()
    public ResponseEntity<List<Long>> addListOfPrescriptionTherapeutique(@RequestBody List<PrescriptionTherapeutiqueDto> prescriptionTherapeutiqueDtoList){
        if (prescriptionTherapeutiqueDtoList == null || prescriptionTherapeutiqueDtoList.isEmpty()) {
            throw new EmptyParamArrayException("The list of PrescriptionTherapeutique cannot be null or empty.");
        }
        List<Long> savedPrescriptionTherapeutiqueId = this.prescriptionTherapeutiqueService.saveAll(prescriptionTherapeutiqueDtoList)
                .stream()
                .map(PrescriptionTherapeutiqueDto::getId)
                .collect(Collectors.toList());
        return new ResponseEntity<>(savedPrescriptionTherapeutiqueId , HttpStatus.CREATED);
    }



    @GetMapping()
    public ResponseEntity<List<PrescriptionTherapeutiqueDto>> getListOfPrescriptionTherapeutique(
            @RequestParam(name = "size" , defaultValue = "5") Integer size ,
            @RequestParam(name = "page" , defaultValue = "0") Integer page
    ){

        List<PrescriptionTherapeutiqueDto> prescriptionTherapeutiqueDtoList = this.prescriptionTherapeutiqueService.findAll(size,page);
        return new ResponseEntity<>(prescriptionTherapeutiqueDtoList , HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<PrescriptionTherapeutiqueDto>> searchPrescriptionTherapeutique(SearchPrescriptionTherapeutiqueRequest searchRequest) {
        searchRequest.validate();
        List<PrescriptionTherapeutiqueDto> prescriptionTherapeutiqueDtos = this.prescriptionTherapeutiqueService
                .findPrescriptionTherapeutiqueByNameOrCreationDate(searchRequest.getName(), searchRequest.getCreationDate());
        return new ResponseEntity<>(prescriptionTherapeutiqueDtos, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<PrescriptionTherapeutiqueDto> updatePrescriptionTherapeutique(
            @PathVariable Long id ,
            @RequestBody PrescriptionTherapeutiqueDto prescriptionTherapeutiqueDto
    ){
        PrescriptionTherapeutiqueDto updatedPrescriptionTherapeutique = this.prescriptionTherapeutiqueService.updatePrescriptionTherapeutique(id ,prescriptionTherapeutiqueDto);
        return new ResponseEntity<>(updatedPrescriptionTherapeutique , HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deletePrescriptionTherapeutique(@PathVariable Long id) {
        PrescriptionTherapeutiqueDto deletePrescriptionTherapeutique=  prescriptionTherapeutiqueService.deletePrescriptionTherapeutique(id);
        return new ResponseEntity<>(deletePrescriptionTherapeutique.getId() , HttpStatus.OK);
    }

}
