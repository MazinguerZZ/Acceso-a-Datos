package goya.dam2.adat.ud6;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personajes")
public class ControllerPersonaje {

    private final RepositoryPersonaje repo;

    public ControllerPersonaje(RepositoryPersonaje repo) {
        this.repo = repo;
    }

    // POST: Crear nuevo personaje (sin ID en el body)
    @PostMapping
    public ResponseEntity<Personaje> crear(@RequestBody Personaje personaje) {
        // Rechazar si viene con ID (ID debe ser generado automáticamente)
        if (personaje.getId() != null) 
            return ResponseEntity.badRequest().build();
        
        // Verificar que no exista otro personaje con el mismo nombre
        Personaje existe = repo.findByNombre(personaje.getNombre()).orElse(null);
        if (existe != null) 
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        
        // Guardar y devolver el personaje creado
        Personaje creado = repo.save(personaje);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    // POST con ID: No permitido (debe usarse POST sin ID para crear)
    @PostMapping("/{id}")
    public ResponseEntity<Void> crearConId(@PathVariable Long id) {
        // Siempre rechaza porque no se permite crear con ID específico
        return ResponseEntity.badRequest().build();
    }

    // GET: Obtener todos los personajes
    @GetMapping
    public ResponseEntity<List<Personaje>> listar() {
        // Devuelve lista completa de personajes
        return ResponseEntity.ok((List<Personaje>) repo.findAll());
    }

    // GET por ID: Obtener un personaje específico
    @GetMapping("/{id}")
    public ResponseEntity<Personaje> obtener(@PathVariable Long id) {
        // Busca por ID, devuelve 404 si no existe
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // PUT: Actualización completa de un personaje
    @PutMapping("/{id}")
    public ResponseEntity<Void> actualizar(@PathVariable Long id, @RequestBody Personaje personaje) {
        // Si viene ID en el body, debe coincidir con el ID de la URL
        if (personaje.getId() != null && !personaje.getId().equals(id)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        
        // Verificar que el personaje a actualizar exista
        if (!repo.existsById(id)) 
            return ResponseEntity.notFound().build();
        
        // Validar que el nuevo nombre no esté duplicado (excluyendo el actual)
        Personaje mismo = repo.findByNombre(personaje.getNombre()).orElse(null);
        if (mismo != null && !mismo.getId().equals(id)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        
        // Asignar ID y guardar cambios
        personaje.setId(id);
        repo.save(personaje);
        return ResponseEntity.noContent().build();
    }

    // PATCH: Actualización parcial de un personaje
    @PatchMapping("/{id}")
    public ResponseEntity<Void> modificarParcial(@PathVariable Long id, @RequestBody Personaje cambios) {
        // En PATCH no se permite enviar ID en el body
        if (cambios.getId() != null) {
            return ResponseEntity.badRequest().build();
        }
        
        // Buscar el personaje existente
        Personaje existente = repo.findById(id).orElse(null);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        
        // Si se envía nombre, validar que no esté duplicado
        if (cambios.getNombre() != null) {
            Personaje mismo = repo.findByNombre(cambios.getNombre()).orElse(null);
            if (mismo != null && !mismo.getId().equals(id)) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
            existente.setNombre(cambios.getNombre());
        }
        
        // Si se envía descripción, actualizarla
        if (cambios.getDescripción() != null) {
            existente.setDescripción(cambios.getDescripción());
        }
        
        // Guardar cambios parciales
        repo.save(existente);
        return ResponseEntity.noContent().build();
    }

    // DELETE: Eliminar un personaje por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable Long id) {
        // Verificar que el personaje existe antes de borrar
        if (!repo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}