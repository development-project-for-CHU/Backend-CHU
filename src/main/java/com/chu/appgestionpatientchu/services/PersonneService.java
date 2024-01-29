package com.chu.appgestionpatientchu.services;



import com.chu.appgestionpatientchu.domain.Patient;
import com.chu.appgestionpatientchu.domain.Personne;
import com.chu.appgestionpatientchu.repository.PersonneRepository;
import com.chu.appgestionpatientchu.utils.enums.Genders;
import com.chu.appgestionpatientchu.utils.enums.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonneService {


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



    public static class PersonneNotFoundException extends RuntimeException {
        public PersonneNotFoundException(String message) {
            super(message);
        }
    }


    public Map<Roles, Long> countAdminAndChefDeServiceAndMedcinAndInfirmier() {
        List<Personne> allPersonne = personneRepository.findAll();


        Map<Roles, Long> rolesCountMap = allPersonne.stream()
                .collect(Collectors.groupingBy(Personne::getRoles, Collectors.counting()));

        rolesCountMap.putIfAbsent(Roles.ADMIN, 0L);
        rolesCountMap.putIfAbsent(Roles.CHEF_SERVICE, 0L);
        rolesCountMap.putIfAbsent(Roles.MEDECIN, 0L);
        rolesCountMap.putIfAbsent(Roles.INFIRMIER, 0L);

        return rolesCountMap;
    }



}
