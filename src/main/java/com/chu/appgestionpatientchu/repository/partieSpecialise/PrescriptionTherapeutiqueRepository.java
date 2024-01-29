package com.chu.appgestionpatientchu.repository.partieSpecialise;


import com.chu.appgestionpatientchu.domain.Anamnese;
import com.chu.appgestionpatientchu.domain.PrescriptionTherapeutique;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PrescriptionTherapeutiqueRepository extends JpaRepository<PrescriptionTherapeutique, Long> {

    Page findByIsDeletedFalse(Pageable page);


    Optional<PrescriptionTherapeutique> findPrescriptionTherapeutiqueByIdAndIsDeletedFalse(Long id);
    List<PrescriptionTherapeutique> findByNameAndIsDeletedFalse(String name );
    List<PrescriptionTherapeutique> findByAddedAtAndIsDeletedFalse(LocalDate addedAt);
    List<PrescriptionTherapeutique> findByNameAndAddedAtAndIsDeletedFalse(String name, LocalDate addedAt);


}
