package com.chu.appgestionpatientchu.repository.partieCommune;

import com.chu.appgestionpatientchu.domain.Allergie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AllergieRepository extends JpaRepository<Allergie , Long> {
    Page findByIsDeletedFalse(Pageable page);

    Optional<Allergie> findAllergieByIdAndIsDeletedFalse(Long id);
    List<Allergie> findByNomAllergieAndIsDeletedFalse(String nomAllergie );
    List<Allergie> findByAddedAtAndIsDeletedFalse(LocalDate addedAt);
    List<Allergie> findByNomAllergieAndAddedAtAndIsDeletedFalse(String nomAllergie, LocalDate addedAt);
}

