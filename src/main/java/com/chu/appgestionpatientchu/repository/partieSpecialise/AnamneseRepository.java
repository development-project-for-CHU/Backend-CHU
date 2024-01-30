package com.chu.appgestionpatientchu.repository.partieSpecialise;



import com.chu.appgestionpatientchu.domain.Allergie;
import com.chu.appgestionpatientchu.domain.Anamnese;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AnamneseRepository extends JpaRepository<Anamnese, Long> {

    Page findByIsDeletedFalse(Pageable page);


    Optional<Anamnese> findAnamneseByIdAndIsDeletedFalse(Long id);
    List<Anamnese> findByNameAndIsDeletedFalse(String name );
    List<Anamnese> findByAddedAtAndIsDeletedFalse(LocalDate addedAt);
    List<Anamnese> findByNameAndAddedAtAndIsDeletedFalse(String name, LocalDate addedAt);



}
