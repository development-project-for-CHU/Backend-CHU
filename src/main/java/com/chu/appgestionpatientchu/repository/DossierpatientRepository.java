package com.chu.appgestionpatientchu.repository;

import com.chu.appgestionpatientchu.domain.DossierPatient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DossierpatientRepository extends JpaRepository<DossierPatient,Long> {
    Optional<DossierPatient> findById(Long id);
}
