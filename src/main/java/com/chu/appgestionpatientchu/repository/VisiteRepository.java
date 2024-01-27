package com.chu.appgestionpatientchu.repository;

import com.chu.appgestionpatientchu.domain.Patient;
import com.chu.appgestionpatientchu.domain.Visite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface VisiteRepository extends JpaRepository<Visite,Long> {
    Optional<Visite> findById(Long id);

}
