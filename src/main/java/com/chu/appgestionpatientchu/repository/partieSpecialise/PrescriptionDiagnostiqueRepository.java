package com.chu.appgestionpatientchu.repository.partieSpecialise;



import com.chu.appgestionpatientchu.domain.PrescriptionDiagnostique;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PrescriptionDiagnostiqueRepository extends JpaRepository<PrescriptionDiagnostique, Long> {

    Page findByIsDeletedFalse(Pageable page);

    Optional<PrescriptionDiagnostique> findPrescriptionDiagnostiqueById(Long id);
    List<PrescriptionDiagnostique> findByName(String name );
    List<PrescriptionDiagnostique> findByAddedAt(LocalDate addedAt);
    List<PrescriptionDiagnostique> findByNameAndAddedAt(String name , LocalDate addedAt);
}
