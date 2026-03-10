package com.api_rest.ud62.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api_rest.ud62.Entity.Clinica;

public interface ClinicaRepository extends JpaRepository<Clinica, Long> {
    
}