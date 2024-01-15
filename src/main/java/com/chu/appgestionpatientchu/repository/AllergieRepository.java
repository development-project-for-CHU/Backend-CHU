package com.chu.appgestionpatientchu.repository;


import com.chu.appgestionpatientchu.domain.Allergie;
import com.chu.appgestionpatientchu.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AllergieRepository extends JpaRepository<Allergie,Long> {

}
