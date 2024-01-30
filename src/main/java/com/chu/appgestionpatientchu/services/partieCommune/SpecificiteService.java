package com.chu.appgestionpatientchu.services.partieCommune;


import com.chu.appgestionpatientchu.domain.Allergie;
import com.chu.appgestionpatientchu.domain.Specificite;
import com.chu.appgestionpatientchu.dto.AllergieDto;
import com.chu.appgestionpatientchu.dto.SpecificiteDto;
import com.chu.appgestionpatientchu.mappers.partieCommune.MapperAllergie;
import com.chu.appgestionpatientchu.mappers.partieCommune.MapperSpecificite;
import com.chu.appgestionpatientchu.repository.partieCommune.SpecificiteRepository;
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
public class SpecificiteService {


    private final SpecificiteRepository specificiteRepository;

    public List<SpecificiteDto> saveAll(List<SpecificiteDto> specificite){
        List<Specificite> specificiteList = specificite.stream().map(MapperSpecificite::mapToSpecificite).collect(Collectors.toList());
        List<Specificite> savedSpecificite = this.specificiteRepository.saveAll(specificiteList);
        return savedSpecificite.stream()
                .map(MapperSpecificite::mapToSpecificiteDto)
                .collect(Collectors.toList());
    }

    public List<SpecificiteDto> findAll(Integer size , Integer page){
        List<Specificite> specificiteList = this.specificiteRepository
                .findByIsDeletedFalse( PageRequest.of(page , size))
                .stream().toList();

        return specificiteList.stream()
                .map(MapperSpecificite::mapToSpecificiteDto)
                .collect(Collectors.toList());
    }

    public List<SpecificiteDto> findSpecificiteByNameOrCreationDate(
            Optional<String> name,
            Optional<LocalDate> creationDate)
    {
        if (name.isPresent() && creationDate.isPresent()) {
            return specificiteRepository.findByNameAndAddedAtAndIsDeletedFalse(name.get(), creationDate.get() )
                    .stream()
                    .map(MapperSpecificite::mapToSpecificiteDto)
                    .collect(Collectors.toList());
        } else if (name.isPresent()) {
            return specificiteRepository.findByNameAndIsDeletedFalse(name.get())
                    .stream()
                    .map(MapperSpecificite::mapToSpecificiteDto)
                    .collect(Collectors.toList());
        } else if (creationDate.isPresent()) {
            return specificiteRepository.findByAddedAtAndIsDeletedFalse(creationDate.get())
                    .stream()
                    .map(MapperSpecificite::mapToSpecificiteDto)
                    .collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }

    }

    public SpecificiteDto updateSpecificite(Long id , SpecificiteDto updateSpecificite) throws EntityNotFoundException {
        return specificiteRepository.findSpecificiteByIdAndIsDeletedFalse(id)
                .map(allergie -> {
                    allergie.setName(updateSpecificite.getName());
                    allergie.setAddedAt(updateSpecificite.getAddedAt());

                    return MapperSpecificite.mapToSpecificiteDto(
                            specificiteRepository.save(allergie)
                    );
                })
                .orElseThrow(() -> new EntityNotFoundException("Specificite not found with id " + id));
    }

    public SpecificiteDto deleteSpecificite(Long id) throws EntityNotFoundException  {
        return specificiteRepository.findSpecificiteByIdAndIsDeletedFalse(id )
                .map(specificite -> {
                    specificite.setDeleted(true);

                    return MapperSpecificite.mapToSpecificiteDto(
                            specificiteRepository.save(specificite)
                    );
                })
                .orElseThrow(() -> new EntityNotFoundException("Specificite not found with id " + id));
    }
}


