package com.chu.appgestionpatientchu.repository.partieCommune;


import com.chu.appgestionpatientchu.domain.MedicationEncours;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MedicationEncoursRepository extends JpaRepository<MedicationEncours, Long> {

    Page findByIsDeletedFalse(Pageable page);

    Optional<MedicationEncours> findMedicationEncoursByIdAndIsDeletedFalse(Long id);
    List<MedicationEncours> findByNameAndIsDeletedFalse(String name );
    List<MedicationEncours> findByAddedAtAndIsDeletedFalse(LocalDate addedAt);
    List<MedicationEncours> findByNameAndAddedAtAndIsDeletedFalse(String name, LocalDate addedAt);


}
