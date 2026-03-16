# Práctica R2-UD6 — API REST con `@RestController`

> **Objetivo:** reimplementar la API REST del reto anterior sin `RestRepository`, creando manualmente los endpoints con `@RestController` para tener mayor control sobre las peticiones y respuestas.

---

## Introducción

En lugar de dejar que `RestRepository` genere los endpoints automáticamente, los implementaremos nosotros usando:

- `@RestController` *(dependencia Spring Web)*
- `@GetMapping`, `@PostMapping`, `@DeleteMapping`, `@PutMapping`, `@PatchMapping`

Esto nos da control total sobre los códigos HTTP devueltos y la lógica de cada operación.

**Tutorial de referencia:** https://spring.io/guides/tutorials/rest

> ⚠️ Podemos **omitir la gestión de excepciones** con `@RestControllerAdvice`. Nos limitaremos a detectar las situaciones en el código y devolver los códigos HTTP pertinentes manualmente.

---

## Apartado 4.1 — Implementación básica con `@RestController`

Personaliza el tutorial de referencia (castellaniza los nombres o usa otra entidad distinta a la del tutorial) y obseva los códigos de respuesta que produce.

En un primer momento puedes centrarte en que cada método **haga su cometido** y reciba/devuelva los objetos pertinentes — Spring se encargará de la conversión a JSON:

### Recibir y devolver JSON
```java
// Devolver JSON: basta con devolver un objeto desde el método del mapeo.
// @ResponseBody está implícito en @RestController.
@GetMapping("/{id}")
public Personaje getById(@PathVariable Long id) { ... }

// Recibir JSON: usar @RequestBody, Spring lo convierte al objeto.
@PostMapping
public Personaje create(@RequestBody Personaje personaje) { ... }
```

---

## Apartado 4.2 — Respuestas con `ResponseEntity`

Adapta el apartado anterior para que los métodos del controlador devuelvan objetos `ResponseEntity<T>`, encapsulando tanto el **código HTTP** como el **cuerpo JSON** de la respuesta.

Referencias:
- https://www.arquitecturajava.com/responseentity-spring-y-rest/
- https://www.baeldung.com/spring-response-entity

### Patrones de uso de `ResponseEntity`
```java
// Respuestas SIN cuerpo
ResponseEntity.notFound().build()
ResponseEntity.badRequest().build()
ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR)

// Respuestas CON cuerpo
ResponseEntity.ok(objeto)
ResponseEntity.accepted().body(objeto)
ResponseEntity.created(uri).body(objeto)
ResponseEntity<Empleado>(empleado, HttpStatus.CREATED)
```

### Ejemplo completo
```java
@PostMapping
public ResponseEntity<Personaje> save(@RequestBody Personaje personaje) {
    if (repositorio.existsById(personaje.getId())) {
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
    return ResponseEntity.status(HttpStatus.CREATED).body(servicio.guardar(personaje));
}
```

---

## Validaciones a implementar en ambos apartados

### No permitir insertar elementos duplicados

Elige los campos que marquen la identidad del objeto. Conviene implementar `equals()` y `hashCode()` en la entidad para comparaciones correctas.

> ¿Debe `POST` rechazar siempre duplicados? Consulta: https://restfulapi.net/rest-put-vs-post/

### Controlar `PUT` y `PATCH` sobre elementos inexistentes

Decide si devuelves `404 Not Found` o si en su lugar creas el recurso.

> ¿Debe `PUT` crear el recurso si no existe? Consulta: https://stackoverflow.com/questions/56240547/should-http-put-create-a-resource-if-it-does-not-exist

---

## Estructura de paquetes recomendada

Divide el proyecto en paquetes con responsabilidades claras. Los métodos del controlador invocan a los del servicio, y es en el servicio donde se usan los repositorios:
```
src/
└── main/java/com/tuapp/
    ├── model/          ← Entidades JPA (@Entity)
    ├── repository/     ← Interfaces que extienden JpaRepository
    ├── service/        ← Lógica de negocio, instancia los repositorios
    └── controller/     ← @RestController, mapeos HTTP, usa el service
```

---

## Códigos HTTP de referencia

| Situación | Código recomendado |
|-----------|-------------------|
| Recurso obtenido correctamente | `200 OK` |
| Recurso creado | `201 Created` |
| Actualización sin contenido de respuesta | `204 No Content` |
| Petición malformada | `400 Bad Request` |
| Recurso no encontrado | `404 Not Found` |
| Conflicto (recurso ya existe) | `409 Conflict` |
| Error interno del servidor | `500 Internal Server Error` |

---

## Si te ves perdido/a — tutoriales de introducción

Antes de abordar esta práctica, repasa estos tutoriales en orden:

1. https://spring.io/guides/gs/rest-service/
2. https://spring.io/guides/gs/accessing-data-mysql
3. https://spring.io/guides/gs/accessing-data-rest/
4. https://spring.io/guides/tutorials/rest/

---

## Referencias

- Métodos HTTP: https://restfulapi.net/http-methods/
- REST API Tutorial: https://www.restapitutorial.com/lessons/httpmethods.html
- `ResponseEntity` en Spring: https://www.baeldung.com/spring-response-entity
- Spring HATEOAS: https://spring.io/guides/gs/rest-hateoas
- Jakarta Persistence con Spring: https://jakarta.ee/learn/starter-guides/how-to-store-and-retrieve-data-using-jakarta-persistence/
