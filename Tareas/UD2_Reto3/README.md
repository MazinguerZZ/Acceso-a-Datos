# Práctica R3-UD2 — Transacciones y procedimientos almacenados

> **Objetivo:** conocer las características y uso de las transacciones y los procedimientos almacenados. Superar los criterios de evaluación del RA2: *"Desarrolla aplicaciones que gestionan información almacenada en bases de datos relacionales identificando y utilizando mecanismos de conexión."*

---

## Criterios de evaluación

| Criterio | Descripción |
|----------|-------------|
| j) | Se han gestionado las transacciones |
| k) | Se han ejecutado procedimientos almacenados en la base de datos |

---

## Introducción

Se propone realizar pruebas sobre el código adjunto cambiando ciertos parámetros de ejecución para analizar sus resultados, y realizar una prueba de concepto de llamada a procedimiento almacenado.

**Grupos:** de dos o tres alumnos.

---

## Desarrollo

### 1. 🔄 Transacciones — Simulador de banco

Analiza la ejecución del simulador de banco adjunto contra MySQL cambiando las constantes de la clase `HiloTransferencias` en cada ejecución:

- `RETIRA_EN_DOS_PASOS`
- `TRANSACCIÓN`
- `REORDENA_QUERIES` *(solo cuando las dos anteriores son `true`)*

#### Combinaciones a testear

| `DOS_PASOS` | `TRANSACCIÓN` | `REORDENA` | Resultado a observar |
|:-----------:|:-------------:|:----------:|----------------------|
| `false` | `false` | `*` | |
| `false` | `true` | `*` | |
| `true` | `false` | `*` | |
| `true` | `true` | `false` | |
| `true` | `true` | `true` | |

> `*` indica que el valor de esa variable no es relevante para esa combinación.

#### Situaciones a observar y explicar

Para cada combinación, observa en qué condiciones se producen las siguientes situaciones e intenta explicar **por qué**:

1. **Descuadres temporales** en el balance.
2. **Descubiertos** en las cuentas.
3. **Interbloqueos** en la base de datos.

> **Nota:** si no se produjeran dichos problemas en ningún caso, prueba aumentando el número de hilos. Si solo se producen con algunas combinaciones, anota cuáles no generan ninguna de las tres situaciones.

---

### 2. 🗃️ Procedimiento almacenado

Sustituye el método `Transfiere` de la clase `Banco` por una **única llamada a un procedimiento almacenado** en la base de datos que realice la misma funcionalidad.

- Analiza los resultados respecto a los del apartado anterior.
- Compara el comportamiento con y sin procedimiento almacenado.

> **Nota:** en este apartado no hace falta tener en cuenta las tres variables del apartado 1.

---

### 3. 🔀 Otras bases de datos *(elige una opción)*

**Opción A — Procedimientos en otro motor:**
Investiga la declaración de procedimientos almacenados en alguna otra base de datos (PostgreSQL, SQLite, H2…) y realiza la prueba de concepto.

**Opción B — Transacciones con bases de datos embebidas:**
Repite el apartado 1 usando bases de datos embebidas (SQLite, Derby, H2 o HSQLDB) en lugar de MySQL.

---

### 4. 📝 Documentación del código

Realiza una documentación detallada del código adjunto a la luz de la información obtenida en el apartado 1. Incluye **comentarios pertinentes** en el código que expliquen:

- El propósito de cada clase y método relevante.
- El efecto de cada constante configurable.
- Los riesgos de concurrencia observados y cómo las transacciones los mitigan.

---

### 5. 🔢 Contadores *(del reto anterior)*

Ejecuta el código de los contadores del reto anterior contra MySQL y analiza las diferencias en sus comportamientos. Justifica los resultados en base al código de cada contador.

---

## Calificación

| Nivel | Requisitos |
|-------|------------|
| **Suficiente** | Apartados 1 y 2 bien documentados. |
| **Bien** | Lo anterior más el apartado 3 **o** el 4 bien documentado. |
| **Excelente** | Cuatro apartados bien documentados. |

---

## Entrega

Documento **`R3-UD2.pdf`** con:

- Documentación y análisis de las pruebas realizadas.
- Capturas o logs que evidencien cada situación observada.
- **Conclusiones individuales** de cada miembro del grupo.

---

## Herramientas

- IDE de Java
- MySQL / MariaDB
- Opcionalmente: SQLite, Apache Derby, H2, HSQLDB
