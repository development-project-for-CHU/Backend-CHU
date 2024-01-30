package com.chu.appgestionpatientchu.web;

import com.chu.appgestionpatientchu.config.UserAuthenticationProvider;
import com.chu.appgestionpatientchu.domain.Personne;
import com.chu.appgestionpatientchu.dto.LoginDto;
import com.chu.appgestionpatientchu.dto.PersonneDto;
import com.chu.appgestionpatientchu.dto.SignUpDto;
import com.chu.appgestionpatientchu.services.PersonneService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/personne")
@RequiredArgsConstructor
public class PersonneController {

    private final UserAuthenticationProvider userAuthenticationProvider;
    @Autowired
    private final PersonneService personneService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Long> save(
            @RequestBody Personne personne
    ) {
       Personne p =   personneService.savePersonne(personne);
       return new ResponseEntity<>(p.getId() , HttpStatus.CREATED);
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
        System.out.println(updatedPersonne);
        Personne personne = personneService.updatePersonne(id, updatedPersonne);

        if (personne != null) {
            return new ResponseEntity<>(personne, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<PersonneDto> login(@RequestBody @Valid LoginDto credentialsDto) {
        PersonneDto personneDto = personneService.login(credentialsDto);
        personneDto.setToken(userAuthenticationProvider.createToken(personneDto));

        return ResponseEntity.ok(personneDto);
    }


    @PostMapping("/register")
    public ResponseEntity<PersonneDto> register(@RequestBody @Valid SignUpDto personne) {
        PersonneDto registeredPersonne = personneService.register(personne);
        return ResponseEntity.ok(registeredPersonne);
    }






}
