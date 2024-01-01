package com.chu.appgestionpatientchu.repository;



import com.chu.appgestionpatientchu.domain.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository

public interface PersonneRepository extends JpaRepository<Personne,Long> {

    Optional<Personne> findById(Long id);
}
