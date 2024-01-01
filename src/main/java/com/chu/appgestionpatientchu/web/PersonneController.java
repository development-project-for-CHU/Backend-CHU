package com.chu.appgestionpatientchu.web;

import com.chu.appgestionpatientchu.domain.Personne;
import com.chu.appgestionpatientchu.services.PersonneService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/personne")
@RequiredArgsConstructor
public class PersonneController {


    @Autowired
    private final PersonneService personneService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(
            @RequestBody Personne personne
    ) {
        personneService.savePersonne(personne);
    }

    @GetMapping
    public ResponseEntity<List<Personne>> findAllPersonne() {
        return ResponseEntity.ok(personneService.findAllPersonne());
    }

    @GetMapping("/{personneId}")
    public ResponseEntity<Personne> findPersonneId(@PathVariable("personneId") Long id) {
        Optional<Personne> personne = personneService.findPersonneId(id);


        return personne.map(personne1 -> new ResponseEntity<>(personne1, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @DeleteMapping("/{personneId}")
    public ResponseEntity<Void> deletePersonne(@PathVariable("personneId") Long id) {
        try {
            personneService.deletePersonne(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{personneId}")
    public ResponseEntity<Personne> updatePersonne(
            @PathVariable("personneId") Long id,
            @RequestBody Personne updatedPersonne
    ) {
        Personne personne = personneService.updatePersonne(id, updatedPersonne);

        if (personne != null) {
            return new ResponseEntity<>(personne, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }






}
