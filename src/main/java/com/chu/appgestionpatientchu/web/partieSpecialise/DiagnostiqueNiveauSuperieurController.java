package com.chu.appgestionpatientchu.web.partieSpecialise;


import com.chu.appgestionpatientchu.dto.DiagnostiqueNiveauSuperieurDto;
import com.chu.appgestionpatientchu.dto.SearchDiagnostiqueNiveauSuperieurRequest;
import com.chu.appgestionpatientchu.exceptions.EmptyParamArrayException;
import com.chu.appgestionpatientchu.services.partieSpecialise.DiagnostiqueNiveauSuperieurService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories/partieSpecialise/diagnostiqueNiveauSuperieur")
public class DiagnostiqueNiveauSuperieurController {


    private final DiagnostiqueNiveauSuperieurService diagnostiqueNiveauSuperieurService;

    @PostMapping()
    public ResponseEntity<List<Long>> addListOfDiagnostiqueNiveauSuperieur(@RequestBody List<DiagnostiqueNiveauSuperieurDto> diagnostiqueNiveauSuperieurDtoList){
        if (diagnostiqueNiveauSuperieurDtoList == null || diagnostiqueNiveauSuperieurDtoList.isEmpty()) {
            throw new EmptyParamArrayException("The list of anamnese cannot be null or empty.");
        }
        List<Long> savedDiagnostiqueNiveauSuperieurId = this.diagnostiqueNiveauSuperieurService.saveAll(diagnostiqueNiveauSuperieurDtoList)
                .stream()
                .map(DiagnostiqueNiveauSuperieurDto::getId)
                .collect(Collectors.toList());
        return new ResponseEntity<>(savedDiagnostiqueNiveauSuperieurId , HttpStatus.CREATED);
    }



    @GetMapping()
    public ResponseEntity<List<DiagnostiqueNiveauSuperieurDto>> getListOfDiagnostiqueNiveauSuperieur(
            @RequestParam(name = "size" , defaultValue = "5") Integer size ,
            @RequestParam(name = "page" , defaultValue = "0") Integer page
    ){

        List<DiagnostiqueNiveauSuperieurDto> diagnostiqueNiveauSuperieurDtoList = this.diagnostiqueNiveauSuperieurService.findAll(size,page);
        return new ResponseEntity<>(diagnostiqueNiveauSuperieurDtoList , HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<DiagnostiqueNiveauSuperieurDto>> searchDiagnostiqueNiveauSuperieur(SearchDiagnostiqueNiveauSuperieurRequest searchRequest) {
        searchRequest.validate();
        List<DiagnostiqueNiveauSuperieurDto> diagnostiqueNiveauSuperieurDtos = this.diagnostiqueNiveauSuperieurService
                .findDiagnostiqueNiveauSuperieurByNameOrCreationDate(searchRequest.getName(), searchRequest.getCreationDate());
        return new ResponseEntity<>(diagnostiqueNiveauSuperieurDtos, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<DiagnostiqueNiveauSuperieurDto> updateDiagnostiqueNiveauSuperieur(
            @PathVariable Long id ,
            @RequestBody DiagnostiqueNiveauSuperieurDto diagnostiqueNiveauSuperieurDto
    ){
        DiagnostiqueNiveauSuperieurDto updatedDiagnostiqueNiveauSuperieur = this.diagnostiqueNiveauSuperieurService.updateDiagnostiqueNiveauSuperieur(id ,diagnostiqueNiveauSuperieurDto);
        return new ResponseEntity<>(updatedDiagnostiqueNiveauSuperieur , HttpStatus.OK);
    }


}
