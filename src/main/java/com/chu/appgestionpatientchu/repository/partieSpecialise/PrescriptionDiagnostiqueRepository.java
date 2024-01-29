package com.chu.appgestionpatientchu.repository.partieSpecialise;



import com.chu.appgestionpatientchu.domain.ExamenClinique;
import com.chu.appgestionpatientchu.domain.PrescriptionDiagnostique;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PrescriptionDiagnostiqueRepository extends JpaRepository<PrescriptionDiagnostique, Long> {

    Page findByIsDeletedFalse(Pageable page);


    Optional<PrescriptionDiagnostique> findPrescriptionDiagnostiqueByIdAndIsDeletedFalse(Long id);
    List<PrescriptionDiagnostique> findByNameAndIsDeletedFalse(String name );
    List<PrescriptionDiagnostique> findByAddedAtAndIsDeletedFalse(LocalDate addedAt);
    List<PrescriptionDiagnostique> findByNameAndAddedAtAndIsDeletedFalse(String name, LocalDate addedAt);


}
