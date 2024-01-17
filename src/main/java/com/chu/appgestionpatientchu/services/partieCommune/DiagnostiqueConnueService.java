package com.chu.appgestionpatientchu.services.partieCommune;


import com.chu.appgestionpatientchu.domain.DiagnostiqueConnue;
import com.chu.appgestionpatientchu.dto.DiagnostiqueConnueDto;
import com.chu.appgestionpatientchu.mappers.partieCommune.MapperDiagnostiqueConnue;
import com.chu.appgestionpatientchu.repository.partieCommune.DiagnostiqueConnueRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DiagnostiqueConnueService {
    
    private final DiagnostiqueConnueRepository diagnostiqueConnueRepository;

    public List<DiagnostiqueConnueDto> saveAll(List<DiagnostiqueConnueDto> diagnostiqueConnue){
        List<DiagnostiqueConnue> diagnostiqueConnueList = diagnostiqueConnue.stream().map(MapperDiagnostiqueConnue::mapToDiagnostiqueConnue).collect(Collectors.toList());
        List<DiagnostiqueConnue> savedDiagnostiqueConnue = this.diagnostiqueConnueRepository.saveAll(diagnostiqueConnueList);
        return savedDiagnostiqueConnue.stream()
                .map(MapperDiagnostiqueConnue::mapToDiagnostiqueConnueDto)
                .collect(Collectors.toList());
    }



    public List<DiagnostiqueConnueDto> findAll(Integer size , Integer page){
        List<DiagnostiqueConnue> diagnostiqueConnueList = this.diagnostiqueConnueRepository
                .findByIsDeletedFalse( PageRequest.of(page , size))
                .stream().toList();

        return diagnostiqueConnueList.stream()
                .map(MapperDiagnostiqueConnue::mapToDiagnostiqueConnueDto)
                .collect(Collectors.toList());
    }


    public List<DiagnostiqueConnueDto> findDiagnostiqueConnueByNameOrCreationDate(
            Optional<String> name,
            Optional<LocalDate> creationDate)
    {
        if (name.isPresent() && creationDate.isPresent()) {
            return diagnostiqueConnueRepository.findByNomDiagnostiqueConnueAndAddedAtAndIsDeletedFalse(name.get(), creationDate.get() )
                    .stream()
                    .map(MapperDiagnostiqueConnue::mapToDiagnostiqueConnueDto)
                    .collect(Collectors.toList());
        } else if (name.isPresent()) {
            return diagnostiqueConnueRepository.findByNomDiagnostiqueConnueAndIsDeletedFalse(name.get())
                    .stream()
                    .map(MapperDiagnostiqueConnue::mapToDiagnostiqueConnueDto)
                    .collect(Collectors.toList());
        } else if (creationDate.isPresent()) {
            return diagnostiqueConnueRepository.findByAddedAtAndIsDeletedFalse(creationDate.get())
                    .stream()
                    .map(MapperDiagnostiqueConnue::mapToDiagnostiqueConnueDto)
                    .collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }

    }


    public DiagnostiqueConnueDto updateDiagnostiqueConnue(Long id , DiagnostiqueConnueDto updateDiagnostiqueConnue) throws EntityNotFoundException {
        return diagnostiqueConnueRepository.findDiagnostiqueConnueByIdAndIsDeletedFalse(id)
                .map(diagnostiqueConnue -> {
                    diagnostiqueConnue.setNomDiagnostiqueConnue(updateDiagnostiqueConnue.getNomDiagnostiqueConnue());
                    diagnostiqueConnue.setAddedAt(updateDiagnostiqueConnue.getAddedAt());

                    return MapperDiagnostiqueConnue.mapToDiagnostiqueConnueDto(
                            diagnostiqueConnueRepository.save(diagnostiqueConnue)
                    );
                })
                .orElseThrow(() -> new EntityNotFoundException("diagnostique Connue not found with id " + id));
    }


    public DiagnostiqueConnueDto deleteDiagnostiqueConnue(Long id) throws EntityNotFoundException  {
        return diagnostiqueConnueRepository.findDiagnostiqueConnueByIdAndIsDeletedFalse(id )
                .map(diagnostiqueConnue -> {
                    diagnostiqueConnue.setDeleted(true);

                    return MapperDiagnostiqueConnue.mapToDiagnostiqueConnueDto(
                            diagnostiqueConnueRepository.save(diagnostiqueConnue)
                    );
                })
                .orElseThrow(() -> new EntityNotFoundException("Allergie not found with id " + id));
    }




}
