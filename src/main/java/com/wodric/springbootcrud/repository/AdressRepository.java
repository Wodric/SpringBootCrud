package com.wodric.springbootcrud.repository;

import com.wodric.springbootcrud.model.Adress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdressRepository extends JpaRepository<Adress, Long> {
    List<Adress> findByPatientId(Long patientId);
    Optional<Adress> findByIdAndPatientId(Long id, Long patientId);
}
