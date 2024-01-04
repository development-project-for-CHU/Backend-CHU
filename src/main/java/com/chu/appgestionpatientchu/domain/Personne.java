package com.chu.appgestionpatientchu.domain;

import org.apache.commons.lang3.RandomStringUtils;
import com.chu.appgestionpatientchu.utils.enums.Roles;
import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.Size;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "Personne")

public class Personne {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    private String username;
    private String email;
    @Column(nullable = false)
    @Size(max = 100)
    private String password;
    private  String numTel;
    private Roles roles ;

    public void generateRandomPassword() {
        this.password = RandomStringUtils.randomAlphanumeric(15);
    }

}


