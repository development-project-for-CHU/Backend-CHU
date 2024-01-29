package com.chu.appgestionpatientchu.services.partieSpecialise;


import com.chu.appgestionpatientchu.domain.DiagnostiqueNiveauSuperieur;
import com.chu.appgestionpatientchu.dto.DiagnostiqueNiveauSuperieurDto;
import com.chu.appgestionpatientchu.mappers.partieSpecialise.MapperDiagnostiqueNiveauSuperieur;
import com.chu.appgestionpatientchu.repository.partieSpecialise.DiagnostiqueNiveauSuperieurRepository;
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
public class DiagnostiqueNiveauSuperieurService {


    private final DiagnostiqueNiveauSuperieurRepository diagnostiqueNiveauSuperieurRepository;


    public List<DiagnostiqueNiveauSuperieurDto> saveAll(List<DiagnostiqueNiveauSuperieurDto> diagnostiqueNiveauSuperieur){
        List<DiagnostiqueNiveauSuperieur> diagnostiqueNiveauSuperieurList = diagnostiqueNiveauSuperieur.stream().map(MapperDiagnostiqueNiveauSuperieur::mapToDiagnostiqueNiveauSuperieur).collect(Collectors.toList());
        List<DiagnostiqueNiveauSuperieur> savedDiagnostiqueNiveauSuperieur = this.diagnostiqueNiveauSuperieurRepository.saveAll(diagnostiqueNiveauSuperieurList);
        return savedDiagnostiqueNiveauSuperieur.stream()
                .map(MapperDiagnostiqueNiveauSuperieur::mapToDiagnostiqueNiveauSuperieurDto)
                .collect(Collectors.toList());
    }


    public List<DiagnostiqueNiveauSuperieurDto> findAll(Integer size , Integer page){
        List<DiagnostiqueNiveauSuperieur> diagnostiqueNiveauSuperieurList = this.diagnostiqueNiveauSuperieurRepository
                .findByIsDeletedFalse( PageRequest.of(page , size))
                .stream().toList();

        return diagnostiqueNiveauSuperieurList.stream()
                .map(MapperDiagnostiqueNiveauSuperieur::mapToDiagnostiqueNiveauSuperieurDto)
                .collect(Collectors.toList());
    }



    public List<DiagnostiqueNiveauSuperieurDto> findDiagnostiqueNiveauSuperieurByNameOrCreationDate(
            Optional<String> name,
            Optional<LocalDate> creationDate)
    {
        if (name.isPresent() && creationDate.isPresent()) {
            return diagnostiqueNiveauSuperieurRepository.findByNameAndAddedAt(name.get(), creationDate.get() )
                    .stream()
                    .map(MapperDiagnostiqueNiveauSuperieur::mapToDiagnostiqueNiveauSuperieurDto)
                    .collect(Collectors.toList());
        } else if (name.isPresent()) {
            return diagnostiqueNiveauSuperieurRepository.findByName(name.get())
                    .stream()
                    .map(MapperDiagnostiqueNiveauSuperieur::mapToDiagnostiqueNiveauSuperieurDto)
                    .collect(Collectors.toList());
        } else if (creationDate.isPresent()) {
            return diagnostiqueNiveauSuperieurRepository.findByAddedAt(creationDate.get())
                    .stream()
                    .map(MapperDiagnostiqueNiveauSuperieur::mapToDiagnostiqueNiveauSuperieurDto)
                    .collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }

    }

    public DiagnostiqueNiveauSuperieurDto updateDiagnostiqueNiveauSuperieur(Long id , DiagnostiqueNiveauSuperieurDto updateDiagnostiqueNiveauSuperieur) throws EntityNotFoundException {
        return diagnostiqueNiveauSuperieurRepository.findDiagnostiqueNiveauSuperieurById(id)
                .map(diagnostiqueNiveauSuperieur -> {
                    diagnostiqueNiveauSuperieur.setName(updateDiagnostiqueNiveauSuperieur.getName());
                    diagnostiqueNiveauSuperieur.setIsPassedToCommune(updateDiagnostiqueNiveauSuperieur.getIsPassedToCommune());
                    diagnostiqueNiveauSuperieur.setAddedAt(updateDiagnostiqueNiveauSuperieur.getAddedAt());

                    return MapperDiagnostiqueNiveauSuperieur.mapToDiagnostiqueNiveauSuperieurDto(
                            diagnostiqueNiveauSuperieurRepository.save(diagnostiqueNiveauSuperieur)
                    );
                })
                .orElseThrow(() -> new EntityNotFoundException("DiagnostiqueNiveauSuperieur not found with id " + id));
    }
}
