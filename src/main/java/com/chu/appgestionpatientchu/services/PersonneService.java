package com.chu.appgestionpatientchu.services;



import com.chu.appgestionpatientchu.domain.Personne;
import com.chu.appgestionpatientchu.repository.PersonneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonneService {


    @Autowired
    private final PersonneRepository personneRepository;

    public void savePersonne(Personne personne) {

        personneRepository.save(personne);
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

            existingPersonne.setUsername(updatedPersonne.getUsername());
            existingPersonne.setEmail(updatedPersonne.getEmail());
            existingPersonne.setPassword(updatedPersonne.getPassword());
            existingPersonne.setNumero(updatedPersonne.getNumero());
            existingPersonne.setRoles(updatedPersonne.getRoles());

            return personneRepository.save(existingPersonne);
        } else {
            // Handle not found case
            throw new PersonneService.PersonneNotFoundException("Personne not found with id: " + id);
        }
    }



    public static class PersonneNotFoundException extends RuntimeException {
        public PersonneNotFoundException(String message) {
            super(message);
        }
    }



}
