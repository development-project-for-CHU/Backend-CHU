package com.chu.appgestionpatientchu.repository.partieSpecialise;



import com.chu.appgestionpatientchu.domain.Anamnese;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AnamneseRepository extends JpaRepository<Anamnese, Long> {

    Page findByIsDeletedFalse(Pageable page);

    Optional<Anamnese> findAnamneseById(Long id);
    List<Anamnese> findByNomAnamnese(String nomAnamnese );
    List<Anamnese> findByAddedAt(LocalDate addedAt);
    List<Anamnese> findByNomAnamneseAndAddedAt(String nomAnamnese , LocalDate addedAt);


}
