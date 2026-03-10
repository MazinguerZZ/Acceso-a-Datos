package com.api_rest.ud62.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.api_rest.ud62.Entity.Clinica;
import com.api_rest.ud62.Repository.ClinicaRepository;

@Service
public class ClinicaService {

    private final ClinicaRepository clinicaRepository;

    public ClinicaService(ClinicaRepository clinicaRepository) {
        this.clinicaRepository = clinicaRepository;
    }

    public List<Clinica> findAll() {
        return clinicaRepository.findAll();
    }

    public Optional<Clinica> findById(Long id) {
        return clinicaRepository.findById(id);
    }

    public Clinica save(Clinica clinica) {
        return clinicaRepository.save(clinica);
    }

    public boolean existsById(Long id) {
        return clinicaRepository.existsById(id);
    }

    public void deleteById(Long id) {
        clinicaRepository.deleteById(id);
    }
}
