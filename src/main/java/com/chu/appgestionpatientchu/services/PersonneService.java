package com.chu.appgestionpatientchu.services;



import com.chu.appgestionpatientchu.domain.Personne;
import com.chu.appgestionpatientchu.dto.LoginDto;
import com.chu.appgestionpatientchu.dto.PersonneDto;
import com.chu.appgestionpatientchu.dto.SignUpDto;
import com.chu.appgestionpatientchu.exceptions.AppException;
import com.chu.appgestionpatientchu.mappers.PersonneMapper;
import com.chu.appgestionpatientchu.repository.PersonneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonneService {

    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final PersonneRepository personneRepository;

    public Personne savePersonne(Personne personne) {
        return personneRepository.save(personne);
    }
    public List<Personne> findAllPersonne() {
        return personneRepository.findAll();
    }

    public Optional<Personne> findPersonneId(Long id) {
        return personneRepository.findById(id);
    }

    public void deletePersonne(Long id) {
        if (personneRepository.existsById(id)) {
            personneRepository.deleteById(id);
        } else {
            throw new PersonneService.PersonneNotFoundException("Personne not found with id: " + id);
        }
    }

    public Personne updatePersonne(Long id, Personne updatedPersonne) {
        Optional<Personne> existingPersonneOptional = personneRepository.findById(id);

        if (existingPersonneOptional.isPresent()) {
            Personne existingPersonne = existingPersonneOptional.get();
              existingPersonne.setService(updatedPersonne.getService());
              existingPersonne.setCodePostal(updatedPersonne.getCodePostal());
              existingPersonne.setVille(updatedPersonne.getVille());
              existingPersonne.setAdresse(updatedPersonne.getAdresse());
              existingPersonne.setGenre(updatedPersonne.getGenre());
              existingPersonne.setDateNaissance(updatedPersonne.getDateNaissance());
              existingPersonne.setPrenom(updatedPersonne.getPrenom());
              existingPersonne.setNom(updatedPersonne.getNom());
            existingPersonne.setEmail(updatedPersonne.getEmail());
            existingPersonne.setPassword(updatedPersonne.getPassword());
            existingPersonne.setNumTel(updatedPersonne.getNumTel());
            existingPersonne.setRoles(updatedPersonne.getRoles());

            return personneRepository.save(existingPersonne);
        } else {
            // Handle not found case
            throw new PersonneService.PersonneNotFoundException("Personne not found with id: " + id);
        }
    }
    public Optional<Personne> findByEmail(String email) {
        return personneRepository.findByEmail(email);
    }
    public Optional<Personne> findByLoginAndPassword(String email, String password) {
        return personneRepository.findByEmailAndPassword(email, password);
    }


    public PersonneDto login(LoginDto credentialsDto) {
       Personne personneOptional = personneRepository.findByEmail(
                credentialsDto.getEmail()).orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));


            if(passwordEncoder.matches(credentialsDto.getPassword(), personneOptional.getPassword())){
            return PersonneMapper.mapToDto(personneOptional);
        } else {
            throw new AppException("Invalid credentials", HttpStatus.UNAUTHORIZED);
        }
    }

    public PersonneDto register(SignUpDto personne) {
        Optional<Personne> existingPersonne =personneRepository.findByEmail(personne.getEmail());

        if (existingPersonne.isPresent()) {
            throw new AppException("Email already exists", HttpStatus.BAD_REQUEST);
        }

     Personne personne1 =PersonneMapper.mapToPersonne(personne);
        personne1.setPassword(passwordEncoder.encode(personne.getPassword()));

        Personne savedPersonne = savePersonne(personne1);

        return PersonneMapper.mapToDto(savedPersonne);
    }


    public static class PersonneNotFoundException extends RuntimeException {
        public PersonneNotFoundException(String message) {
            super(message);
        }
    }



}
