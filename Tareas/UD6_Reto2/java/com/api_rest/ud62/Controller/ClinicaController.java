package com.api_rest.ud62.Controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api_rest.ud62.Entity.Clinica;
import com.api_rest.ud62.Service.ClinicaService;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/clinicas")
public class ClinicaController {

    private final ClinicaService service;

    public ClinicaController(ClinicaService service) {
        this.service = service;
    }
    
    @PostMapping
    public ResponseEntity<Clinica> crear(@RequestBody Clinica clinica) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.save(clinica));
    }

    @GetMapping
    public ResponseEntity<List<Clinica>> listar() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Clinica> obtener(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PutMapping("/{id}")
    public ResponseEntity<Clinica> actualizar(
            @PathVariable Long id,
            @RequestBody Clinica clinica) {

        if (!service.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        clinica.setId(id);
        return ResponseEntity.ok(service.save(clinica));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Clinica> modificarParcial(
            @PathVariable Long id,
            @RequestBody Map<String, Object> cambios) {

        return service.findById(id)
                .map(c -> {
                    if (cambios.containsKey("direccion")) {
                        c.setDireccion((String) cambios.get("direccion"));
                    }
                    if (cambios.containsKey("telefono")) {
                        c.setTelefono((String) cambios.get("telefono"));
                    }
                    return ResponseEntity.ok(service.save(c));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable Long id) {
        if (!service.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

