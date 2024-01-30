package com.chu.appgestionpatientchu.repository.partieSpecialise;



import com.chu.appgestionpatientchu.domain.DiagnostiqueNiveauSuperieur;
import com.chu.appgestionpatientchu.domain.ExamenClinique;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ExamenCliniqueRepository extends JpaRepository<ExamenClinique, Long> {

    Page findByIsDeletedFalse(Pageable page);


    Optional<ExamenClinique> findExamenCliniqueByIdAndIsDeletedFalse(Long id);
    List<ExamenClinique> findByNameAndIsDeletedFalse(String name );
    List<ExamenClinique> findByAddedAtAndIsDeletedFalse(LocalDate addedAt);
    List<ExamenClinique> findByNameAndAddedAtAndIsDeletedFalse(String name, LocalDate addedAt);


}
