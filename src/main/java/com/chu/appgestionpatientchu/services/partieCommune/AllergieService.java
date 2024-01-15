package com.chu.appgestionpatientchu.services.partieCommune;

import com.chu.appgestionpatientchu.domain.Allergie;
import com.chu.appgestionpatientchu.dto.AllergieDto;
import com.chu.appgestionpatientchu.mappers.partieCommune.MapperAllergie;
import com.chu.appgestionpatientchu.repository.partieCommune.AllergieRepository;
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
public class AllergieService {
    private final AllergieRepository allergieRepository;

    public List<AllergieDto> saveAll(List<AllergieDto> allergies){
         List<Allergie> allergieList = allergies.stream().map(MapperAllergie::mapToAllergie).collect(Collectors.toList());
         List<Allergie> savedAllergies = this.allergieRepository.saveAll(allergieList);
         return savedAllergies.stream()
                 .map(MapperAllergie::mapToAllergieDto)
                 .collect(Collectors.toList());
    }

    public List<AllergieDto> findAll(Integer size , Integer page){
        List<Allergie> allergieList = this.allergieRepository
                .findByIsDeletedFalse( PageRequest.of(page , size))
                .stream().toList();

        return allergieList.stream()
                .map(MapperAllergie::mapToAllergieDto)
                .collect(Collectors.toList());
    }

    public List<AllergieDto> findAllergiessByNameOrCreationDate(
            Optional<String> name,
            Optional<LocalDate> creationDate)
    {
        if (name.isPresent() && creationDate.isPresent()) {
            return allergieRepository.findByNomAllergieAndAddedAtAndIsDeletedFalse(name.get(), creationDate.get() )
                    .stream()
                    .map(MapperAllergie::mapToAllergieDto)
                    .collect(Collectors.toList());
        } else if (name.isPresent()) {
            return allergieRepository.findByNomAllergieAndIsDeletedFalse(name.get())
                    .stream()
                    .map(MapperAllergie::mapToAllergieDto)
                    .collect(Collectors.toList());
        } else if (creationDate.isPresent()) {
            return allergieRepository.findByAddedAtAndIsDeletedFalse(creationDate.get())
                    .stream()
                    .map(MapperAllergie::mapToAllergieDto)
                    .collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }

    }

    public AllergieDto updateAllergie(Long id , AllergieDto updateAllergie) throws EntityNotFoundException {
        return allergieRepository.findAllergieByIdAndIsDeletedFalse(id)
                .map(allergie -> {
                    allergie.setNomAllergie(updateAllergie.getNomAllergie());
                    allergie.setAddedAt(updateAllergie.getAddedAt());

                    return MapperAllergie.mapToAllergieDto(
                            allergieRepository.save(allergie)
                    );
                })
                .orElseThrow(() -> new EntityNotFoundException("Allergie not found with id " + id));
    }

    public AllergieDto deleteAllergie(Long id) throws EntityNotFoundException  {
        return allergieRepository.findAllergieByIdAndIsDeletedFalse(id )
                .map(allergie -> {
                    allergie.setDeleted(true);

                    return MapperAllergie.mapToAllergieDto(
                            allergieRepository.save(allergie)
                    );
                })
                .orElseThrow(() -> new EntityNotFoundException("Allergie not found with id " + id));
    }
}
