package com.chu.appgestionpatientchu.repository.partieCommune;

import com.chu.appgestionpatientchu.domain.Allergie;
import com.chu.appgestionpatientchu.domain.DiagnostiqueConnue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DiagnostiqueConnueRepository extends JpaRepository<DiagnostiqueConnue , Long> {

    Page findByIsDeletedFalse(Pageable page);

    Optional<DiagnostiqueConnue> findDiagnostiqueConnueByIdAndIsDeletedFalse(Long id);
    List<DiagnostiqueConnue> findByNomDiagnostiqueConnueAndIsDeletedFalse(String nomDiagnostiqueConnue );
    List<DiagnostiqueConnue> findByAddedAtAndIsDeletedFalse(LocalDate addedAt);
    List<DiagnostiqueConnue> findByNomDiagnostiqueConnueAndAddedAtAndIsDeletedFalse(String nomDiagnostiqueConnue, LocalDate addedAt);


}
