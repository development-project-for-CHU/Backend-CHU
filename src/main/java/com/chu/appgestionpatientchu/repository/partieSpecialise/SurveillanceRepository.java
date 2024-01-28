package com.chu.appgestionpatientchu.repository.partieSpecialise;

import com.chu.appgestionpatientchu.domain.Surveillance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface SurveillanceRepository  extends JpaRepository<Surveillance, Long> {

    Page findByIsDeletedFalse(Pageable page);

    Optional<Surveillance> findSurveillanceById(Long id);
    List<Surveillance> findByNomSurveillance(String nomSurveillance );
    List<Surveillance> findByAddedAt(LocalDate addedAt);
    List<Surveillance> findByNomSurveillanceAndAddedAt(String nomSurveillance , LocalDate addedAt);

}
