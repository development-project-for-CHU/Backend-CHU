package com.chu.appgestionpatientchu.repository.partieSpecialise;


import com.chu.appgestionpatientchu.domain.PrescriptionTherapeutique;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PrescriptionTherapeutiqueRepository extends JpaRepository<PrescriptionTherapeutique, Long> {

    Page findByIsDeletedFalse(Pageable page);

    Optional<PrescriptionTherapeutique> findPrescriptionTherapeutiqueById(Long id);
    List<PrescriptionTherapeutique> findByName(String name );
    List<PrescriptionTherapeutique> findByAddedAt(LocalDate addedAt);
    List<PrescriptionTherapeutique> findByNameAndAddedAt(String name , LocalDate addedAt);

}
