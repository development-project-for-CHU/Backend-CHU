package com.chu.appgestionpatientchu.services;


import com.chu.appgestionpatientchu.domain.Allergie;

import com.chu.appgestionpatientchu.dto.AllergieDto;
import com.chu.appgestionpatientchu.mappers.AllergieMapper;
import com.chu.appgestionpatientchu.repository.AllergieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AllergieService {

    @Autowired
    private  final AllergieRepository allergieRepository;
    public void saveAllergie(AllergieDto allergieDto) {

        allergieDto.setAddedAt(new Date(System.currentTimeMillis()));
        Allergie allergie= AllergieMapper.mapToEntity(allergieDto);
        allergieRepository.save(allergie);
    }

    public List<Allergie> getAllAllergies() {
        return allergieRepository.findAll();
    }

    public Optional<Allergie> getAllergieById(Long id) {
        return allergieRepository.findById(id);
    }

    public void deleteAllergie(Long id) {
        allergieRepository.deleteById(id);
    }

    public Allergie updateAllergie(Long id, Allergie updatedAllergie) {
        Optional<Allergie> existingAllergieOptional = allergieRepository.findById(id);
        updatedAllergie.setAddedAt(new Date(System.currentTimeMillis()));
        if (existingAllergieOptional.isPresent()) {
            Allergie existingAllergie = existingAllergieOptional.get();
            existingAllergie.setNomAllergie(updatedAllergie.getNomAllergie());
            existingAllergie.setAddedAt(updatedAllergie.getAddedAt());
            existingAllergie.setListPartieCommune(updatedAllergie.getListPartieCommune());
            return allergieRepository.save(existingAllergie);
        } else {
            throw new AllergieNotFoundException("Allergie not found with id: " + id);
        }
    }




    public static class AllergieNotFoundException extends RuntimeException {
        public AllergieNotFoundException(String message) {
            super(message);
        }
    }
}
