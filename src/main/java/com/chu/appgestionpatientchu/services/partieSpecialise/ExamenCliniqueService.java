package com.chu.appgestionpatientchu.services.partieSpecialise;



import com.chu.appgestionpatientchu.domain.ExamenClinique;
import com.chu.appgestionpatientchu.dto.ExamenCliniqueDto;
import com.chu.appgestionpatientchu.mappers.partieSpecialise.MapperExamenClinique;
import com.chu.appgestionpatientchu.repository.partieSpecialise.ExamenCliniqueRepository;
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
public class ExamenCliniqueService {


    private final ExamenCliniqueRepository examenCliniqueRepository;


    public List<ExamenCliniqueDto> saveAll(List<ExamenCliniqueDto> examenClinique){
        List<ExamenClinique> examenCliniqueList = examenClinique.stream().map(MapperExamenClinique::mapToExamenClinique).collect(Collectors.toList());
        List<ExamenClinique> savedExamenClinique= this.examenCliniqueRepository.saveAll(examenCliniqueList);
        return savedExamenClinique.stream()
                .map(MapperExamenClinique::mapToExamenCliniqueDto)
                .collect(Collectors.toList());
    }


    public List<ExamenCliniqueDto> findAll(Integer size , Integer page){
        List<ExamenClinique> anamneseList = this.examenCliniqueRepository
                .findByIsDeletedFalse( PageRequest.of(page , size))
                .stream().toList();

        return anamneseList.stream()
                .map(MapperExamenClinique::mapToExamenCliniqueDto)
                .collect(Collectors.toList());
    }



    public List<ExamenCliniqueDto> findExamenCliniqueByNameOrCreationDate(
            Optional<String> name,
            Optional<LocalDate> creationDate)
    {
        if (name.isPresent() && creationDate.isPresent()) {
            return examenCliniqueRepository.findByNomExamenCliniqueAndAddedAt(name.get(), creationDate.get() )
                    .stream()
                    .map(MapperExamenClinique::mapToExamenCliniqueDto)
                    .collect(Collectors.toList());
        } else if (name.isPresent()) {
            return examenCliniqueRepository.findByNomExamenClinique(name.get())
                    .stream()
                    .map(MapperExamenClinique::mapToExamenCliniqueDto)
                    .collect(Collectors.toList());
        } else if (creationDate.isPresent()) {
            return examenCliniqueRepository.findByAddedAt(creationDate.get())
                    .stream()
                    .map(MapperExamenClinique::mapToExamenCliniqueDto)
                    .collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }

    }

    public ExamenCliniqueDto updateExamenClinique(Long id ,ExamenCliniqueDto updateExamenClinique) throws EntityNotFoundException {
        return examenCliniqueRepository.findExamenCliniqueById(id)
                .map(examenClinique -> {
                    examenClinique.setNomExamenClinique(updateExamenClinique.getNomExamenClinique());
                    examenClinique.setAddedAt(updateExamenClinique.getAddedAt());

                    return MapperExamenClinique.mapToExamenCliniqueDto(
                            examenCliniqueRepository.save(examenClinique)
                    );
                })
                .orElseThrow(() -> new EntityNotFoundException(" examen Clinique not found with id " + id));
    }
}
