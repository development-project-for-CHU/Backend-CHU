package com.chu.appgestionpatientchu.web;

import com.chu.appgestionpatientchu.domain.Allergie;
import com.chu.appgestionpatientchu.dto.AllergieDto;
import com.chu.appgestionpatientchu.services.AllergieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/allergies")
@RequiredArgsConstructor
public class AllergieController {

    private final AllergieService allergieService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveAllergie(@RequestBody AllergieDto allergieDto) {
        allergieService.saveAllergie(allergieDto);
    }

    // Read (Get) operations
    @GetMapping
    public ResponseEntity<List<Allergie>> getAllAllergies() {
        return ResponseEntity.ok(allergieService.getAllAllergies());
    }

    @GetMapping("/{allergieId}")
    public ResponseEntity<Allergie> getAllergieById(@PathVariable("allergieId") Long id) {
        Optional<Allergie> allergie = allergieService.getAllergieById(id);

        if (allergie.isPresent()) {
            return ResponseEntity.ok(allergie.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Update operation
    @PutMapping("/{allergieId}")
    public ResponseEntity<Allergie> updateAllergie(
            @PathVariable("allergieId") Long id,
            @RequestBody Allergie updatedAllergie) {
        try {
            Allergie updated = allergieService.updateAllergie(id, updatedAllergie);
            return ResponseEntity.ok(updated);
        } catch (AllergieService.AllergieNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete operation
    @DeleteMapping("/{allergieId}")
    public ResponseEntity<Void> deleteAllergie(@PathVariable("allergieId") Long id) {
        try {
            allergieService.deleteAllergie(id);
            return ResponseEntity.noContent().build();
        } catch (AllergieService.AllergieNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
