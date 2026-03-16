# Práctica UD4-UD5 — Bases de datos NoSQL desde Java

> **Objetivo:** cubrir los contenidos de los temas 4 y 5 sobre bases de datos NoSQL, acceso desde Java/Spring Boot y conocimiento de distintos motores NoSQL.

**Individual o en grupos de dos alumnos.**

---

## Calificación

| Nivel | Requisitos |
|-------|------------|
| **Suficiente** | Apartado 1 **o** apartado 2. |
| **Bien** | Apartados 1 **y** 2. |
| **Excelente** | Los tres apartados, con el apartado 3 documentado. |

---

## Apartado 1 — MongoDB desde Spring Boot

> ⚠️ Antes de empezar, comprueba con el profesor que tienes **MongoDB instalado y funcionando** en tu máquina.

Realiza los siguientes tutoriales **cambiando los nombres de clases y atributos en inglés por equivalentes en castellano** (u otros de tu elección).

### 1.1 — Acceso básico a MongoDB

Tutorial: https://spring.io/guides/gs/accessing-data-mongodb

Prueba de concepto que al iniciar (`CommandLineRunner`) ejecuta dos inserciones y algunas consultas básicas.

### 1.2 — API REST sobre MongoDB

Tutorial: https://spring.io/guides/gs/accessing-mongodb-data-rest

Usa `RestRepository` para exponer un API REST completo sobre el repositorio MongoDB.

> Si lo haces **individualmente**, basta con completar **uno** de los dos tutoriales.

### 1.3 — Prueba desde `mongosh`

Siguiendo el PDF *"Curso_MongoDB.pdf"* de OpenWebinars:

1. Crea algún objeto adicional directamente desde la consola `mongosh`.
2. Recupéralo desde tu programa Java y verifica que se obtiene correctamente.

---

## Apartado 2 — Investigación y presentación de una base de datos NoSQL

Elige una base de datos de las referencias del tema y elabora un **documento resumen de aproximadamente dos páginas** con:

- Características principales del motor elegido.
- Modelo de datos que utiliza (documentos, grafos, clave-valor, columnar…).
- Casos de uso ideales.
- Diferencias clave respecto a bases de datos SQL relacionales.

> 🎤 Lo presentaréis en clase durante **10 minutos** el día de la entrega o al siguiente.

---

## Apartado 3 — Prueba de concepto con otra base de datos NoSQL *(«Excelente»)*

Elige una de las siguientes bases de datos, instálala y realiza una prueba de acceso desde Java. Si usas Spring Boot, tienes guías de referencia para cada opción:

| Base de datos | Guía / Referencia |
|---------------|-------------------|
| **Neo4j** *(grafos)* | https://spring.io/guides/gs/accessing-data-neo4j |
| **Cassandra** *(columnar)* | https://spring.io/guides/gs/accessing-data-cassandra |
| **Redis** *(clave-valor)* | https://spring.io/guides/gs/spring-data-reactive-redis |
| **Redis** *(alternativa)* | https://www.baeldung.com/spring-data-redis-tutorial |

> **Nota sobre la instalación:**
> - **Neo4j y Cassandra** requieren instalación previa del motor en tu máquina.
> - **Redis** no requiere instalación local si usas un **contenedor Docker** con la base de datos ya configurada.

Documenta la prueba de concepto realizada: configuración, entidades mapeadas, operaciones probadas y conclusiones.
