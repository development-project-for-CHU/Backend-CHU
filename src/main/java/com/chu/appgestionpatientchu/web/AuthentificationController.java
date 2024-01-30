//package com.chu.appgestionpatientchu.web;
//
//
//import com.chu.appgestionpatientchu.domain.Personne;
//import com.chu.appgestionpatientchu.dto.LoginDto;
//import com.chu.appgestionpatientchu.services.PersonneService;
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RequiredArgsConstructor
//@RequestMapping("/api/personnes")
//@RestController
//public class AuthentificationController {
//    @Autowired
//    private final PersonneService personneService;
//
//
//    @PostMapping("/login")
//    public ResponseEntity<Personne> login(@RequestBody @Valid LoginDto credentialsDto) {
//        Personne personne = personneService.login(credentialsDto);
//        return ResponseEntity.ok(personne);
//    }
//
//
//    @PostMapping("/register")
//    public ResponseEntity<Personne> register(@RequestBody @Valid Personne personne) {
//        Personne registeredPersonne = personneService.register(personne);
//        return ResponseEntity.ok(registeredPersonne);
//    }
//
//}
