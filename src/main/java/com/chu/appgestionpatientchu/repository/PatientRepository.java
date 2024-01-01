package com.chu.appgestionpatientchu.repository;

import com.chu.appgestionpatientchu.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {
   Patient findByIpp(Long patientipp);
}
