# Práctica R2-UD3 — ORM con JPA e Hibernate

> **Objetivo:** usar la implementación de JPA Hibernate para realizar mapeos objeto-relacional y consultas sobre los datos. Superar los criterios de evaluación del RA3: *"Gestiona la persistencia de los datos identificando herramientas de mapeo objeto relacional (ORM) y desarrollando aplicaciones que las utilizan."*

---

## Criterios de evaluación

| Criterio | Descripción |
|----------|-------------|
| a) | Se ha instalado la herramienta ORM |
| b) | Se ha configurado la herramienta ORM |
| c) | Se han definido configuraciones de mapeo |
| d) | Se han aplicado mecanismos de persistencia a los objetos |
| e) | Se han desarrollado aplicaciones que modifican y recuperan objetos persistentes |
| f) | Se han desarrollado aplicaciones que realizan consultas usando SQL |
| g) | Se han gestionado las transacciones |

---

## Introducción

Se propone implementar un CRUD sobre una base de datos relacional MySQL/MariaDB usando JPA con **Hibernate** como implementación. La interfaz puede ser de texto o reutilizar una de los proyectos anteriores.

**Grupos:** de dos o tres alumnos.

---

## Desarrollo

### Apartado 1 — Migrar de EclipseLink a Hibernate

Sustituye en el proyecto del reto anterior las dependencias de EclipseLink por las de Hibernate. Documenta si funciona y qué cambios has necesitado realizar.

Cambio obligatorio en `persistence.xml`:
```xml
<provider>org.hibernate.jpa.HibernatePersistenceProvider</provider>
```

> ⚠️ Hibernate obliga a usar **tipos wrapped** en lugar de primitivos: `Integer` en vez de `int`, `Float` en vez de `float`, etc.

### Apartado 2 — Proyecto desde cero *(alternativa al 1)*

Como alternativa, crea un proyecto nuevo con Maven realizando igualmente un CRUD sobre:

- La misma base de datos del reto anterior.
- Una base de datos de ejemplos del curso pasado.
- Una de las hojas de ejercicios del aula virtual.

### Apartado 3 — Consultas JPQL

Implementa consultas para **todos** los siguientes casos:

| # | Tipo de consulta |
|---|-----------------|
| 1 | Listado de todas las entidades seleccionando **solo algunos campos** |
| 2 | Listado **filtrado** por algún criterio (`WHERE`) |
| 3 | Listado **ordenado** por algún criterio |
| 4 | Búsqueda por criterios **exactos y aproximados** (`LIKE`) |
| 5 | Elementos cuyo campo numérico esté en un **rango** (`BETWEEN`) |
| 6 | **Agregaciones:** sumas, medias, conteo con criterio (`SUM`, `AVG`, `COUNT`) |
| 7 | Algún **`JOIN`** entre entidades *(para «Bien» y «Excelente»)* |
| + | Consultas más elaboradas a criterio del grupo *(para «Bien» y «Excelente»)* |

Puedes buscar inspiración en los ejercicios del aula virtual y en las referencias de JPQL al final del documento.

### Apartado 4 — Patrón DAO o Repository *(opcional — «Excelente»)*

Implementa un patrón **DAO** o **Repository** y realiza una prueba de concepto de los apartados anteriores a través de él.

Referencias:
- https://reactiveprogramming.io/blog/es/patrones-arquitectonicos/dao
- https://www.arquitecturajava.com/dao-vs-repository-y-sus-diferencias/

### Apartado 5 — Ingeniería inversa *(opcional — «Excelente»)*

Genera automáticamente el código de las entidades Java a partir de una base de datos existente siguiendo el tutorial de José María *"Configuración de Hibernate en Eclipse 2023.pdf"*.

#### Notas importantes para este apartado

- La versión actual de JBoss que se localizará es la **4.29.1**; deja las opciones por defecto, acepta la licencia y confía en la descarga. Eclipse tardará mucho en reiniciarse — ten paciencia.
- El arquetipo `maven-archetype-quickstart` no crea `src/main/resources` visible; prueba a crearlo sin la `s` y luego renombrarlo con *Refactor → Rename*.
- ⚠️ La creación del proyecto Maven se queda al **33%** esperando una respuesta **por consola** — no en la GUI.
- Si usas el driver de MariaDB desde Maven:
  - `driver_class`: `org.mariadb.jdbc.Driver`
  - URL: `jdbc:mariadb://localhost/...`
  - El dialect de Hibernate sigue siendo el de MySQL.
- El `hibernate.cfg.xml` generado queda en `src/main/java`; muévelo a `src/main/resources` sobreescribiendo el anterior (o añade los mappings al existente).
- Para usar `EntityManager` en vez de `Session`, migra el proyecto o crea manualmente el `persistence.xml` con referencias a los `.hbm.xml`:
```xml
  <class>database.Alumno.hbm.xml</class>
```

### Apartado 6 — Alternativas a la ingeniería inversa *(opcional — «Excelente»)*

Busca e investiga alternativas más sencillas al apartado 5:

- **NetBeans** — integración nativa más directa.
- **JPA-Buddy** para IntelliJ: https://jpa-buddy.com/documentation/reverse-engineering/
- **Jeddict**: https://jeddict.github.io/
- **Dali Tools Plugin** para Eclipse: https://www.baeldung.com/jpa-tools-overview
- **Eclipse Web Development Edition**: https://eclipse.dev/webtools/dali/docs/3.2/user_guide/tasks006.htm

---

## Calificación

| Nivel | Requisitos |
|-------|------------|
| **Suficiente** | Apartado 1 o 2 bien documentado y en el apartado 3 las queries 1 a 6. |
| **Bien** | Lo anterior más la query 7 y otras consultas más elaboradas en el apartado 3. |
| **Excelente** | Lo anterior más una prueba de concepto (por componente del grupo) de los apartados 4, 5 o 6. |

---

## Entrega

Documento **`R2-UD3.pdf`** con:

- Enlace al código e información sobre cómo probarlo.
- **Conclusiones individuales** de cada miembro del grupo.

---

## Configuración del proyecto Maven

### Dependencia Hibernate
```xml
<!-- https://mvnrepository.com/artifact/org.hibernate.orm/hibernate-core -->
<dependency>
    <groupId>org.hibernate.orm</groupId>
    <artifactId>hibernate-core</artifactId>
    <version>6.6.4.Final</version>
</dependency>
```

### Driver MariaDB
```xml
<!-- https://mvnrepository.com/artifact/org.mariadb.jdbc/mariadb-java-client -->
<dependency>
    <groupId>org.mariadb.jdbc</groupId>
    <artifactId>mariadb-java-client</artifactId>
    <version>3.5.1</version>
</dependency>
```

### `persistence.xml`

Ubicación: `src/main/resources/META-INF/persistence.xml`
```xml
<provider>org.hibernate.jpa.HibernatePersistenceProvider</provider>
```

---

## Lecturas previas recomendadas

- Introducción a Hibernate de Manuel Barroso *(aula virtual)*
- Hibernate completo — curso IFC03CM17 *(aula virtual)*
- JPA vs Hibernate: https://www.arquitecturajava.com/jpa-vs-hibernate/
- Diferencias JPA / Hibernate / EclipseLink: https://thorben-janssen.com/difference-jpa-hibernate-eclipselink/

---

## Referencias

### Hibernate y EntityManager
- https://www.baeldung.com/hibernate-entitymanager
- Session vs EntityManager: https://www.baeldung.com/java-entitymanagerfactory-vs-sessionfactory

### JPQL
- https://en.wikipedia.org/wiki/Jakarta_Persistence_Query_Language
- https://thorben-janssen.com/jpql/
- Funciones JPQL: https://certidevs.com/tutorial-hibernate-jpql-funciones
- Referencia HQL/JPQL: https://docs.jboss.org/hibernate/orm/5.2/userguide/html_single/chapters/query/hql/HQL.html

### JPA Criteria API
- https://www.arquitecturajava.com/jpa-criteria-api-un-enfoque-diferente/
- https://www.baeldung.com/hibernate-criteria-queries
- https://jakarta.ee/learn/docs/jakartaee-tutorial/current/persist/persistence-criteria/persistence-criteria.html

### Migración `hibernate.cfg.xml` → `persistence.xml`
- https://www.arquitecturajava.com/flexibilidad-en-jpa-persistence-xml-y-orm-xml/
- https://stackoverflow.com/questions/52390480/how-to-load-persistence-xml
