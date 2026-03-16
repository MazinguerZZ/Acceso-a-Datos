# Práctica R1-UD2 — Persistencia en bases de datos relacionales con JDBC

> **Objetivo:** implementar persistencia de datos de una aplicación Java en una base de datos relacional. Superar los criterios de evaluación del RA2: *"Desarrolla aplicaciones que gestionan información almacenada en bases de datos relacionales identificando y utilizando mecanismos de conexión."*

---

## Criterios de evaluación

| Criterio | Descripción |
|----------|-------------|
| c) | Se ha utilizado el conector idóneo en la aplicación |
| d) | Se ha establecido la conexión |
| e) | Se ha definido la estructura de la base de datos |
| f) | Se han desarrollado aplicaciones que modifican el contenido de la BD |
| g) | Se han definido los objetos destinados a almacenar el resultado de las consultas |
| h) | Se han desarrollado aplicaciones que efectúan consultas |
| i) | Se han eliminado los objetos una vez finalizada su función |

---

## Introducción

Se propone implementar un CRUD sobre una base de datos relacional MySQL/MariaDB (o similar) desde una aplicación Java. Se recomienda apoyarse en alguna IA o partir de un proyecto ya hecho.

**Grupos:** de dos o tres alumnos.

---

## Desarrollo — Opciones por orden de dificultad

### Opción 1 — Completar `ModeloAlumnosJDBC`

Implementa los métodos vacíos de la clase `ModeloAlumnosJDBC` en el proyecto `gestionAlumnos` para implementar el CRUD de la entidad `Alumno`.

### Opción 2 — Migrar persistencia de ficheros a base de datos

Sustituye la persistencia en ficheros de uno de los proyectos adjuntos por persistencia en base de datos:

- `AgendaGUI`
- `FicheroAgendaV1`
- `GestorAlumnosFinal`

> **Sugerencia para `FicheroAgendaV1`:** crea una clase `BD_Agenda.java` que sustituya a `FicheroAgenda.java` ofreciendo los mismos métodos pero implementados sobre una base de datos MariaDB:
>
> ```java
> leeContactos()
> escribeRegistro(Contacto contacto)
> borraRegistro(Contacto contacto)
> ```
>
> En `BD_Agenda` no necesitarás ningún mapa ni lista adicional, solo el acceso a la BD y unas consultas bien planificadas. La clase `prueba_BD_Agenda` podrá ser idéntica a `PruebaFicheroAgenda` cambiando únicamente la instancia de `FicheroAgenda` por `BD_Agenda` — solo con ese cambio debería funcionar todo el código. Un paso más sería crear una interfaz para facilitar dicho intercambio de clases.

### Opción 3 — Aplicación propia con CRUD

Construye una aplicación básica en Java (CLI o GUI) que realice un CRUD sobre:

- Alguna de las bases de datos de ejemplo del aula virtual.
- Bases de datos del curso pasado.
- Una base de datos que diseñes sobre algún interés particular.

### Opción 4 — Aplicación con múltiples tablas *(«Excelente»)*

Cualquiera de las opciones anteriores pero con:

- Más de una tabla.
- Al menos una relación entre ellas.
- Un campo de fecha.
- Funcionalidad de búsqueda.
- Creación de las tablas desde la aplicación en la primera ejecución.
- Importación de datos desde ficheros CSV.

---

## Requisitos comunes a todas las opciones

### Usar siempre `PreparedStatement`

Nunca uses `Statement` directamente. Usa siempre `PreparedStatement` por seguridad frente a inyección SQL.
```java
PreparedStatement ps = connection.prepareStatement("SELECT * FROM alumnos WHERE id = ?");
ps.setInt(1, idAlumno);
ResultSet rs = ps.executeQuery();
```

### Separación del acceso a datos

Todo el código de acceso a la base de datos debe estar en **una sola clase**. Define una **interfaz** que sirva de enlace entre el resto de la aplicación y tu implementación concreta de la persistencia (patrón DAO).

### Actualización sobre cursor (`ResultSet` actualizable)

En las actualizaciones que requieran un `SELECT` previo, en vez de ejecutar una segunda query con `UPDATE`, modifica directamente sobre el cursor obtenido:
```java
PreparedStatement consulta = connection.prepareStatement(
    sqlConsulta,
    ResultSet.FETCH_FORWARD,
    ResultSet.CONCUR_UPDATABLE
);
ResultSet res = consulta.executeQuery();

res.updateInt(2, cuenta);
res.updateRow();
```

---

## Calificación

| Nivel | Requisitos |
|-------|------------|
| **Suficiente** | Opción 1 o 2, bien documentada. |
| **Bien** | Opción 3 bien documentada, o las opciones 1 y 2 añadiendo búsquedas a una de ellas y comparando el código entre ambas. |
| **Excelente** | Opción 4, incluyendo creación de tablas desde la aplicación en la primera ejecución e importación de datos desde CSV. |

---

## Entrega

Documento **`R1-UD2.pdf`** con:

- Enlace al código e información sobre cómo probarlo/desplegarlo.
- **Conclusiones individuales** de cada miembro del grupo.

---

## Notas de configuración

### Conector JDBC

Añade el conector MySQL/MariaDB a tu proyecto (en `lib/`, con *Add JARs*) y asegúrate de incluirlo en el *Build Path*.

### Error de clave pública RSA

Si obtienes el error:
```
RSA public key is not available client side (option serverRsaPublicKeyFile not set)
```

Añade el parámetro a la URL de conexión:
```java
String url = "jdbc:mysql://localhost:3306/mibd?allowPublicKeyRetrieval=true";
```

### Mejoras recomendadas

- Saca la configuración de conexión (host, puerto, usuario, contraseña) a un **fichero externo** (texto plano o `Properties`), no la dejes hardcodeada.
- **Reutiliza la conexión** a la base de datos en vez de abrirla y cerrarla en cada operación. Ver: https://www.linkedin.com/posts/siro-cornejo-raez_desarrolloweb-pymes-performance-activity-7387035346653560832-EldB/

---

## Referencias

- JDBC y `PreparedStatement`: https://www.arquitecturajava.com/jdbc-prepared-statement-y-su-manejo/
- Patrón DAO con JDBC: *DAO-y-JDBC-Marcos* (aula virtual)
