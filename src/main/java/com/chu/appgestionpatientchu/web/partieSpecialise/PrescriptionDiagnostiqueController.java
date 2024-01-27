package com.chu.appgestionpatientchu.web.partieSpecialise;


import com.chu.appgestionpatientchu.dto.AnamneseDto;
import com.chu.appgestionpatientchu.dto.ExamenCliniqueDto;
import com.chu.appgestionpatientchu.dto.PrescriptionDiagnostiqueDto;
import com.chu.appgestionpatientchu.dto.SearchExamenCliniqueRequest;
import com.chu.appgestionpatientchu.exceptions.EmptyParamArrayException;
import com.chu.appgestionpatientchu.services.partieSpecialise.AnamneseService;
import com.chu.appgestionpatientchu.services.partieSpecialise.PrescriptionDiagnostiqueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories/partieSpecialise/PrescriptionDiagnostique")
public class PrescriptionDiagnostiqueController {

    private final PrescriptionDiagnostiqueService prescriptionDiagnostiqueService;

    @PostMapping()
    public ResponseEntity<List<Long>> addListOfPrescriptionDiagnostique(@RequestBody List<PrescriptionDiagnostiqueDto> prescriptionDiagnostiqueDtoList){
        if (prescriptionDiagnostiqueDtoList == null || prescriptionDiagnostiqueDtoList.isEmpty()) {
            throw new EmptyParamArrayException("The list of PrescriptionDiagnostique cannot be null or empty.");
        }
        List<Long> savedPrescriptionDiagnostiqueId = this.prescriptionDiagnostiqueService.saveAll(prescriptionDiagnostiqueDtoList)
                .stream()
                .map(PrescriptionDiagnostiqueDto::getId)
                .collect(Collectors.toList());
        return new ResponseEntity<>(savedPrescriptionDiagnostiqueId , HttpStatus.CREATED);
    }


    @GetMapping()
    public ResponseEntity<List<PrescriptionDiagnostiqueDto>> getListOfPrescriptionDiagnostique(
            @RequestParam(name = "size" , defaultValue = "5") Integer size ,
            @RequestParam(name = "page" , defaultValue = "0") Integer page
    ){

        List<PrescriptionDiagnostiqueDto> prescriptionDiagnostiqueDtoList = this.prescriptionDiagnostiqueService.findAll(size,page);
        return new ResponseEntity<>(prescriptionDiagnostiqueDtoList , HttpStatus.OK);
    }


    @GetMapping("/search")
    public ResponseEntity<List<PrescriptionDiagnostiqueDto>> searchPrescriptionDiagnostique(SearchExamenCliniqueRequest searchRequest) {
        searchRequest.validate();
        List<PrescriptionDiagnostiqueDto> prescriptionDiagnostiqueDtos = this.prescriptionDiagnostiqueService
                .findPrescriptionDiagnostiqueByNameOrCreationDate(searchRequest.getName(), searchRequest.getCreationDate());
        return new ResponseEntity<>(prescriptionDiagnostiqueDtos, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<PrescriptionDiagnostiqueDto> updatePrescriptionDiagnostique(
            @PathVariable Long id ,
            @RequestBody PrescriptionDiagnostiqueDto prescriptionDiagnostiqueDto
    ){
        PrescriptionDiagnostiqueDto updatedPrescriptionDiagnostique= this.prescriptionDiagnostiqueService.updatePrescriptionDiagnostique(id , prescriptionDiagnostiqueDto);
        return new ResponseEntity<>(updatedPrescriptionDiagnostique , HttpStatus.OK);
    }


}
