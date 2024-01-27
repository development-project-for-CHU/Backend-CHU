package com.chu.appgestionpatientchu.services.partieSpecialise;

import com.chu.appgestionpatientchu.domain.Anamnese;
import com.chu.appgestionpatientchu.dto.AnamneseDto;
import com.chu.appgestionpatientchu.mappers.partieCommune.MapperAllergie;
import com.chu.appgestionpatientchu.mappers.partieSpecialise.MapperAnamnese;
import com.chu.appgestionpatientchu.repository.partieSpecialise.AnamneseRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.NoArgsConstructor;
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

public class AnamneseService {


    private final AnamneseRepository anamneseRepository;


    public List<AnamneseDto> saveAll(List<AnamneseDto> allergies){
        List<Anamnese> anamneseList = allergies.stream().map(MapperAnamnese::mapToAnamnese).collect(Collectors.toList());
        List<Anamnese> savedAnamnese = this.anamneseRepository.saveAll(anamneseList);
        return savedAnamnese.stream()
                .map(MapperAnamnese::mapToAnamneseDto)
                .collect(Collectors.toList());
    }


    public List<AnamneseDto> findAll(Integer size , Integer page){
        List<Anamnese> anamneseList = this.anamneseRepository
                .findByIsDeletedFalse( PageRequest.of(page , size))
                .stream().toList();

        return anamneseList.stream()
                .map(MapperAnamnese::mapToAnamneseDto)
                .collect(Collectors.toList());
    }



    public List<AnamneseDto> findAnamneseByNameOrCreationDate(
            Optional<String> name,
            Optional<LocalDate> creationDate)
    {
        if (name.isPresent() && creationDate.isPresent()) {
            return anamneseRepository.findByNomAnamneseAndAddedAt(name.get(), creationDate.get() )
                    .stream()
                    .map(MapperAnamnese::mapToAnamneseDto)
                    .collect(Collectors.toList());
        } else if (name.isPresent()) {
            return anamneseRepository.findByNomAnamnese(name.get())
                    .stream()
                    .map(MapperAnamnese::mapToAnamneseDto)
                    .collect(Collectors.toList());
        } else if (creationDate.isPresent()) {
            return anamneseRepository.findByAddedAt(creationDate.get())
                    .stream()
                    .map(MapperAnamnese::mapToAnamneseDto)
                    .collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }

    }

    public AnamneseDto updateAnamnese(Long id , AnamneseDto updateAnamnese) throws EntityNotFoundException {
        return anamneseRepository.findAnamneseById(id)
                .map(allergie -> {
                    allergie.setNomAnamnese(updateAnamnese.getNomAnamnese());
                    allergie.setAddedAt(updateAnamnese.getAddedAt());

                    return MapperAnamnese.mapToAnamneseDto(
                            anamneseRepository.save(allergie)
                    );
                })
                .orElseThrow(() -> new EntityNotFoundException("Anamnese not found with id " + id));
    }


/*    public AnamneseDto deleteAnamnese(Long id) throws EntityNotFoundException  {
        return anamneseRepository.findAnamneseByIdAndIsDeletedFalse(id )
                .map(anamnese  -> {
                    anamnese.setDeleted(true);

                    return MapperAnamnese.mapToAnamneseDto(
                            anamneseRepository.save(anamnese)
                    );
                })
                .orElseThrow(() -> new EntityNotFoundException("Anamnese not found with id " + id));
    }


 */
}
