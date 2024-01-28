package com.chu.appgestionpatientchu.repository.partieSpecialise;


import com.chu.appgestionpatientchu.domain.DiagnostiqueNiveauSuperieur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DiagnostiqueNiveauSuperieurRepository extends JpaRepository<DiagnostiqueNiveauSuperieur, Long> {

    Page findByIsDeletedFalse(Pageable page);

    Optional<DiagnostiqueNiveauSuperieur> findDiagnostiqueNiveauSuperieurById(Long id);
    List<DiagnostiqueNiveauSuperieur> findByNomDiagnostiqueNiveauSuperieur(String nomDiagnostiqueNiveauSuperieur );
    List<DiagnostiqueNiveauSuperieur> findByAddedAt(LocalDate addedAt);
    List<DiagnostiqueNiveauSuperieur> findByNomDiagnostiqueNiveauSuperieurAndAddedAt(String nomDiagnostiqueNiveauSuperieur , LocalDate addedAt);


}
