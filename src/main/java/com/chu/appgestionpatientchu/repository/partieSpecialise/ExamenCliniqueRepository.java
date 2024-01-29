package com.chu.appgestionpatientchu.repository.partieSpecialise;



import com.chu.appgestionpatientchu.domain.ExamenClinique;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ExamenCliniqueRepository extends JpaRepository<ExamenClinique, Long> {

    Page findByIsDeletedFalse(Pageable page);

    Optional<ExamenClinique> findExamenCliniqueById(Long id);
    List<ExamenClinique> findByName(String name );
    List<ExamenClinique> findByAddedAt(LocalDate addedAt);
    List<ExamenClinique> findByNameAndAddedAt(String name , LocalDate addedAt);
}
