# Práctica R2-UD2 — Bases de datos embebidas y transacciones

> **Objetivo:** conocer las características y uso de las bases de datos embebidas y el concepto de transacción. Superar los criterios de evaluación del RA2: *"Desarrolla aplicaciones que gestionan información almacenada en bases de datos relacionales identificando y utilizando mecanismos de conexión."*

---

## Criterios de evaluación

| Criterio | Descripción |
|----------|-------------|
| b) | Se han utilizado gestores de bases de datos embebidos e independientes |
| j) | Se han gestionado las transacciones |

---

## Introducción

Se propone realizar pruebas cambiando en aplicaciones ya existentes el acceso a una base de datos independiente por una base de datos embebida. Por otro lado, realizar una prueba de concepto de transacciones.

**Grupos:** de dos o tres alumnos.

---

## Desarrollo — Propuestas por orden de dificultad

### 1. 🗄️ Bases de datos embebidas

En alguna aplicación del reto anterior, o en las pequeñas pruebas de concepto de los *Contadores* adjuntos, sustituye el acceso a MySQL por una de las siguientes bases de datos embebidas en modo fichero local:

- **SQLite**
- **Apache Derby**
- **H2**
- **HSQLDB**

Hazlo **dos veces**, cada una con una base de datos distinta.

### 2. 📦 Proyecto portable

Exporta uno de tus proyectos del apartado 1 a un `.jar` y demuestra que con ese jar, el jar de la librería de base de datos y el archivo o carpeta de base de datos es suficiente para copiarlo a otra máquina con un JRE y ejecutarlo:
```bash
java -jar vuestroarchivo.jar
```

> Con la base de datos en esa carpeta, usando ruta relativa o absoluta. Haz que la configuración de ruta del fichero la tome de un **archivo de configuración `.ini`** (`PropertiesFile`). Todos los IDEs tienen la opción de exportar a *runnable jar* o similares.

### 3. ⚡ Introducción a las transacciones

Ejecuta las pruebas de concepto de los *Contadores* adjuntos. Por cada uno, ejecútalo varias veces de manera simultánea (la forma más simple es dar al *play* varias veces seguidas) y analiza su comportamiento:

- En **MySQL**.
- En una **base de datos embebida**.

Compara el código de al menos uno que cuente bien con uno que cuente mal.

---

## Calificación

| Nivel | Requisitos |
|-------|------------|
| **Suficiente** | Apartado 1 funcional y bien documentado. |
| **Bien** | Lo anterior más el apartado 2 bien documentado. |
| **Excelente** | Lo anterior más el apartado 3 bien razonado. |

---

## Entrega

Documento **`R2-UD2.pdf`** con:

- Enlace al código e información sobre cómo probarlo.
- **Conclusiones individuales** de cada miembro del grupo.

---

## Herramientas

- IDE de Java
- Bases de datos: SQLite, Apache Derby, H2, HSQLDB

---

## Notas de configuración

### Ubicación de los JARs (Ubuntu 24.04 con paquetería)

Los JARs de las bases de datos embebidas instaladas mediante paquetería están en `/usr/share/java`:

| Base de datos | JAR |
|---------------|-----|
| Apache Derby | `derby.jar`, `derbyclient.jar` |
| H2 | `h2.jar` |
| HSQLDB | `hsqldb.jar` |
| SQLite | `sqlite-jdbc.jar` |

> La creación previa de las bases de datos en este tipo de instalación (paquetería) puede requerirse según el motor.

### URLs de conexión
```properties
# Apache Derby
jdbc:derby:/home/...

# SQLite
jdbc:sqlite:/home/...

# HSQLDB
jdbc:hsqldb:file:/home/...;shutdown=true
```

> ⚠️ En **HSQLDB**, añade siempre `;shutdown=true` al final de la URL para asegurar el cierre limpio de la base de datos.
