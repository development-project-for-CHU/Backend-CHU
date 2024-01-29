package com.chu.appgestionpatientchu.repository.partieCommune;


import com.chu.appgestionpatientchu.domain.Specificite;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface SpecificiteRepository extends JpaRepository<Specificite, Long> {


    Page findByIsDeletedFalse(Pageable page);

    Optional<Specificite> findSpecificiteByIdAndIsDeletedFalse(Long id);
    List<Specificite> findByNameAndIsDeletedFalse(String name );
    List<Specificite> findByAddedAtAndIsDeletedFalse(LocalDate addedAt);
    List<Specificite> findByNameAndAddedAtAndIsDeletedFalse(String name, LocalDate addedAt);


}
