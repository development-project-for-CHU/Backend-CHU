package com.chu.appgestionpatientchu.repository.partieSpecialise;

import com.chu.appgestionpatientchu.domain.PrescriptionTherapeutique;
import com.chu.appgestionpatientchu.domain.Surveillance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface SurveillanceRepository  extends JpaRepository<Surveillance, Long> {

    Page findByIsDeletedFalse(Pageable page);


    Optional<Surveillance> findSurveillanceByIdAndIsDeletedFalse(Long id);
    List<Surveillance> findByNameAndIsDeletedFalse(String name );
    List<Surveillance> findByAddedAtAndIsDeletedFalse(LocalDate addedAt);
    List<Surveillance> findByNameAndAddedAtAndIsDeletedFalse(String name, LocalDate addedAt);



}
