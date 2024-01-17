package com.chu.appgestionpatientchu.web.partieCommune;



import com.chu.appgestionpatientchu.dto.DiagnostiqueConnueDto;
import com.chu.appgestionpatientchu.dto.SearchDiagnostiqueConnueRequest;
import com.chu.appgestionpatientchu.exceptions.EmptyParamArrayException;
import com.chu.appgestionpatientchu.services.partieCommune.DiagnostiqueConnueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories/partieCommune/diagnostics")
public class DiagnostiqueConnueController {

    private final DiagnostiqueConnueService diagnostiqueConnueService;

    @PostMapping()
    public ResponseEntity<List<Long>> addListOfDiagnostiqueConnue(@RequestBody List<DiagnostiqueConnueDto> diagnostiqueConnueDtoList){
        if (diagnostiqueConnueDtoList == null || diagnostiqueConnueDtoList.isEmpty()) {
            throw new EmptyParamArrayException("The list of diagnostique connue cannot be null or empty.");
        }
        List<Long> savedDiagnostiqueConnueId = this.diagnostiqueConnueService.saveAll(diagnostiqueConnueDtoList)
                .stream()
                .map(DiagnostiqueConnueDto::getId)
                .collect(Collectors.toList());
        return new ResponseEntity<>(savedDiagnostiqueConnueId , HttpStatus.CREATED);
    }
    @GetMapping()
    public ResponseEntity<List<DiagnostiqueConnueDto>> getListOfAllergies(
            @RequestParam(name = "size" , defaultValue = "5") Integer size ,
            @RequestParam(name = "page" , defaultValue = "0") Integer page
    ){

        List<DiagnostiqueConnueDto> diagnostiqueConnueDtoList = this.diagnostiqueConnueService.findAll(size,page);
        return new ResponseEntity<>(diagnostiqueConnueDtoList , HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<DiagnostiqueConnueDto>> searchDiagnostiqueConnue(SearchDiagnostiqueConnueRequest searchRequest) {
        searchRequest.validate();  // Validate the search criteria ==> throw the InvalidSearchCriteriaException
        List<DiagnostiqueConnueDto> diagnostiqueConnueDtoList = this.diagnostiqueConnueService
                .findDiagnostiqueConnueByNameOrCreationDate(searchRequest.getName(), searchRequest.getCreationDate());
        return new ResponseEntity<>(diagnostiqueConnueDtoList, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<DiagnostiqueConnueDto> updateDiagnostiqueConnue(
            @PathVariable Long id ,
            @RequestBody DiagnostiqueConnueDto diagnostiqueConnueDto
    ){
        DiagnostiqueConnueDto updateDiagnostiqueConnue = this.diagnostiqueConnueService.updateDiagnostiqueConnue(id ,diagnostiqueConnueDto);
        return new ResponseEntity<>(updateDiagnostiqueConnue , HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteDiagnostiqueConnue(@PathVariable Long id) {
        DiagnostiqueConnueDto deletedDiagnostiqueConnue =  diagnostiqueConnueService.deleteDiagnostiqueConnue(id);
        return new ResponseEntity<>(deletedDiagnostiqueConnue.getId() , HttpStatus.OK);
    }
}
