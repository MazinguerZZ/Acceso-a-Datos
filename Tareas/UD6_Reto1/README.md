# Práctica R1-UD6 — APIs RESTful

> **Objetivo:** aprender sobre las API RESTful creando y probando tu propia API con distintos métodos y herramientas.

**Elige una temática de tu interés.** Puedes empezar con un solo tipo de entidad y pocos atributos.

---

## Calificación

| Nivel | Requisitos |
|-------|------------|
| **Suficiente** | Apartados 1 y 2 bien documentados y mostrados en clase. |
| **Bien** | Apartados 1 y 2 personalizados (entidades en castellano, distintas a las del tutorial) más el apartado 3. |
| **Excelente** | Todos los apartados bien documentados y mostrados en clase. |

---

## Apartado 1 — API REST con `json-server`

Sigue el tutorial: https://desarrolloweb.com/articulos/crear-api-rest-json-server.html

> ⚠️ Si hay problemas de sintaxis por la versión de Node.js en el equipo de clase, avisa al profesor. Se recomienda la **opción 3** de https://www.digitalocean.com/community/tutorials/how-to-install-node-js-on-ubuntu-22-04

### Pruebas a realizar

Usa Postman, HTTPie, `curl`, `POST` (lwp-request) o cualquier alternativa. Para cada petición documenta el **código de retorno** y los **datos devueltos**:

| # | Petición | Qué observar |
|---|----------|--------------|
| 1 | `GET` lista de entidades | Código y cuerpo |
| 2 | `GET` de una entidad existente | Código y cuerpo |
| 3 | `GET` de una entidad inexistente (id inválido) | Código de error |
| 4 | `POST` de una entidad nueva | Código y entidad creada |
| 5 | `POST` de una entidad ya existente | Comportamiento |
| 6 | `DELETE` de una entidad existente | Código y confirmación |
| 7 | `DELETE` de una entidad inexistente | Código de error |
| 8 | `PUT` de una entidad existente | Código y entidad modificada |
| 9 | `PUT` de una entidad inexistente | Comportamiento |
| 10 | `PATCH` de una entidad existente | Código y cambio parcial |
| 11 | `PATCH` con incoherencia entre id del path y del JSON | Comportamiento |

> Tras cada petición, comprueba el listado completo de entidades para verificar que los cambios son coherentes.

### Comandos de referencia con `POST` (lwp-request)
```bash
# GET
POST -c "application/json" http://localhost:3000/peliculas

# PUT
POST -m PUT -c "application/json" http://localhost:3000/peliculas/10

# DELETE
POST -m DELETE http://localhost:3000/peliculas/2

# -U muestra cabeceras de envío
# -E muestra cabeceras de respuesta
```

> **Nota:** si te atascas con la personalización, vuelve al ejemplo del tutorial en inglés y comprueba que funciona antes de continuar.

---

## Apartado 2 — API REST con Spring Boot y `RestRepository`

Tutorial: https://spring.io/guides/gs/accessing-data-rest/

Implementa el mismo API del apartado anterior con Spring Boot:

1. Crea una clase para la entidad (ej: `Pelicula.java`).
2. Crea un interfaz repositorio que extienda `RestRepository`.
3. Activa las dependencias **Spring Data JPA** y **H2** — se creará una base de datos en memoria automáticamente.

### Carga inicial de datos

Crea el archivo `src/main/resources/import.sql` con sentencias `INSERT`. Usa todo en minúsculas; los cambios de mayúscula en Java se traducen a `_` en SQL:
```sql
-- Entidad Persona con atributos firstName y lastName
INSERT INTO person(first_name, last_name) VALUES('Javier', 'Puche');
INSERT INTO person(first_name, last_name) VALUES('Agustín', 'Aguilera');
```

Para que el id sea autogenerado, márcalo en la entidad así (sin incluirlo en los `INSERT`):
```java
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
```

### Exponer el id en las respuestas JSON

Por defecto `RestRepository` no muestra el id. Para activarlo, etiqueta la clase `main` con `implements RepositoryRestConfigurer` y añade:
```java
@Override
public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
    config.exposeIdsFor(Pelicula.class);
}
```

Añade también el getter correspondiente para el id en la entidad.

### Realiza las mismas pruebas que en el apartado 1

Elabora una **tabla comparativa** de las respuestas entre `json-server` y `RestRepository`:

| Petición | json-server (código / cuerpo) | RestRepository (código / cuerpo) |
|----------|-------------------------------|----------------------------------|
| `GET` OK — 1 objeto | | |
| `GET` OK — todos los objetos | | |
| `GET` id inexistente | | |
| `POST` OK sin especificar id | | |
| `POST` con id en path | | |
| `POST` con id en JSON | | |
| `POST` de objeto existente | | |
| `DELETE` OK | | |
| `DELETE` entidad inexistente | | |
| `PUT` OK | | |
| `PUT` id inexistente | | |
| `PUT` con id distinto en JSON | | |
| `PATCH` OK | | |
| `PATCH` con id distinto en JSON | | |

> En los logs de arranque de Spring Boot encontrarás el acceso a la **consola H2** para inspeccionar la base de datos en memoria.

---

## Apartado 3 — Prueba de APIs públicas *(para «Bien» y «Excelente»)*

Prueba con Postman (u otra herramienta) alguna de las APIs públicas compartidas en el foro del aula virtual. Comenta las diferencias de comportamiento respecto a los apartados anteriores:

- ¿Devuelven los mismos códigos HTTP?
- ¿Qué ocurre con las operaciones de escritura (`POST`, `PUT`, `DELETE`)?
- ¿Hay autenticación? ¿Paginación? ¿Otros mecanismos?

---

## Apartado 4 — Resumen sobre APIs RESTful *(para «Excelente»)*

Lee los materiales y referencias del tema y elabora un **resumen escrito** sobre qué es un API RESTful. Incluye:

- Principios REST (stateless, recursos, verbos HTTP, códigos de respuesta…).
- Diferencias entre REST y otras arquitecturas (SOAP, GraphQL…).
- Cualquier referencia adicional que estimes interesante.

> 🎤 Se entrega el resumen y se realiza una **presentación en clase** a los compañeros.

---

## Entrega

Documento **`R1-UD6.pdf`** con:

- Capturas demostrativas del paso a paso.
- Tabla comparativa del apartado 2.
- Conclusiones sobre lo aprendido.
- Enlace al código fuente.

**Demostración en clase del funcionamiento.**

---

## Herramientas para pruebas

### Clientes gráficos

- **Postman** *(no requiere registro)*
- Thunderclient
- Bruno: https://www.usebruno.com/
- Apidog: https://apidog.com/
- JSONcrack: https://jsoncrack.com/

### Línea de comandos — `curl`
```bash
# POST
curl -i -X POST -H "Content-Type:application/json" \
  -d '{"firstName":"Frodo","lastName":"Baggins"}' \
  http://localhost:8080/people

# PUT
curl -i -X PUT -H "Content-Type:application/json" \
  -d '{"firstName":"Bilbo","lastName":"Baggins"}' \
  http://localhost:8080/people/1

# PATCH
curl -i -X PATCH -H "Content-Type:application/json" \
  -d '{"firstName":"Bilbo Jr."}' \
  http://localhost:8080/people/1

# DELETE
curl -i -X DELETE http://localhost:8080/people/1
```

### Línea de comandos — `POST` (lwp-request)
```bash
POST -c "application/json" http://localhost:8080/paises
POST -m PUT -c "application/json" http://localhost:8080/paises/australia
POST -m DELETE http://localhost:8080/paises/australia
# -U cabeceras de envío  |  -E cabeceras de respuesta
```

### Pruebas de carga — Apache Bench (`ab`)
```bash
ab -T application/x-www-form-urlencoded -n 100 -c 5 -p post.txt http://localhost:8080/entidad
```

El archivo `post.txt` contiene los parámetros en una sola línea: `param1=valor1&param2=valor2`

### Otras alternativas

- HTTPie: https://httpie.io/
- Hurl: https://hurl.dev/

---

## Referencias

- Tutorial json-server: https://desarrolloweb.com/articulos/crear-api-rest-json-server.html
- Tutorial Spring Data REST: https://spring.io/guides/gs/accessing-data-rest/
- Exponer id en RestRepository: https://stackoverflow.com/questions/44046659/return-ids-in-json-response-from-spring-data-rest
- Peticiones POST con Apache Bench: https://stackoverflow.com/questions/29731023/make-a-post-request-using-ab-apache-benchmarking-on-a-django-server
- Parámetros en HTTP POST: https://stackoverflow.com/questions/14551194/how-are-parameters-sent-in-an-http-post-request
