# Práctica R1-UD3 — ORM con JPA y EclipseLink

> **Objetivo:** usar la implementación de referencia de JPA EclipseLink para entender cómo funciona el mapeo ORM. Superar los criterios de evaluación del RA3: *"Gestiona la persistencia de los datos identificando herramientas de mapeo objeto relacional (ORM) y desarrollando aplicaciones que las utilizan."*

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

## Contexto — ¿Qué es JPA?

**JPA** (*Java Persistence API*) es el estándar de Java para persistencia de objetos. Es una especificación con varias implementaciones; la de referencia es **EclipseLink**, aunque la más extendida es **Hibernate**. Antiguamente residía en `javax.persistence`; actualmente está en el paquete `jakarta.persistence`.

Características clave:

- Válida tanto para bases de datos relacionales como orientadas a objetos.
- Resuelve el **desfase objeto-relacional**.
- Mapeo transparente del CRUD mediante **anotaciones y metadatos**.
- Consultas tipo SQL sobre objetos mediante **JPQL**.
- Requiere, además del driver JDBC concreto, los JARs de JPA (a mano o mediante Maven).

---

## Introducción

Se propone implementar un CRUD sobre una base de datos relacional MySQL/MariaDB usando JPA con EclipseLink. La interfaz de usuario puede ser de texto o reutilizar una interfaz de los retos anteriores.

**Grupos:** de dos alumnos.

---

## Desarrollo

### Apartado 1 — Prueba de concepto

Elige una base de datos de retos anteriores, del curso pasado o de las propuestas del tema 2, y desarrolla (o reutiliza) una aplicación que mediante EclipseLink realice un **CRUD sobre una entidad**.

- Realiza el mapeo a objetos Java con las anotaciones de JPA.
- En este apartado **no especifiques `@Table` ni `@Column`**: deja que EclipseLink cree las tablas con los nombres por defecto.

### Apartado 2 — Prueba completa

Explora las distintas estrategias de generación de esquema y personaliza el mapeo:

- Prueba los valores de `jakarta.persistence.schema-generation.database.action`:

| Valor | Efecto |
|-------|--------|
| `none` | No toca el esquema |
| `create` | Crea las tablas |
| `drop-and-create` | Borra y vuelve a crear |
| `drop` | Solo borra |

- Usa `@Table` y `@Column` para que los nombres se correspondan **exactamente** con los de la base de datos original.

### Apartado 3 — Relaciones `OneToMany` *(para «Bien» y «Excelente»)*

Amplía el apartado 1 para que el CRUD incluya **más de una entidad relacionadas** entre sí con relaciones `@OneToMany`.

### Apartado 4 — Relaciones `ManyToMany` *(para «Excelente»)*

Añade al menos una relación `@ManyToMany` entre entidades.

### Apartado 5 — Herramientas gráficas para `persistence.xml` *(para «Excelente»)*

Investiga y compara maneras de generar el archivo `persistence.xml` y la conexión a base de datos automáticamente desde un entorno gráfico. Algunas opciones:

- **NetBeans:** *New File → Persistence → Persistence Unit…*
- **Eclipse for Web Development** con DTP (*Data Tools Platform*): https://javabeat.net/eclipselink-jpa-installation-configuration/
- **Eclipse estándar:** *Help → Install New Software → Database Development*
- Cualquier otra herramienta con Maven.

---

## Calificación

| Nivel | Requisitos |
|-------|------------|
| **Suficiente** | Apartados 1 y 2 correctamente implementados y documentados. |
| **Bien** | Apartados 1, 2 y 3 correctamente implementados y documentados. |
| **Excelente** | Todos los apartados correctamente implementados y documentados. |

---

## Entrega

Documento **`R1-UD3.pdf`** con:

- Enlace al código e información sobre cómo probarlo.
- **Conclusiones individuales** de cada miembro del grupo.

---

## Configuración del proyecto

### Opción A — JARs manuales

Descarga EclipseLink e incorpora al proyecto:

- `eclipselink.jar`
- `jakarta.activation-api.jar`
- `jakarta.persistence-api.jar`
- Driver JDBC de MariaDB/MySQL

### Opción B — Maven
```xml
<dependency>
    <groupId>org.eclipse.persistence</groupId>
    <artifactId>eclipselink</artifactId>
    <version>4.0.4</version>
</dependency>
```

---

## Archivo `persistence.xml`

Ubicación: `META-INF/persistence.xml` dentro de `src/main/resources` (Maven) o `src` (sin Maven). Si se coloca en otra ubicación, añadir el recurso en el `pom.xml`.

Basarse en el tutorial https://www.arquitecturajava.com/jpa-un-ejemplo-desde-cero/ **cambiando `javax` por `jakarta`**.
```xml
<property name="jakarta.persistence.schema-generation.database.action" value="create" />
```

---

## Entidades JPA — Recordatorio
```java
@Entity
public class MiEntidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // getters y setters...
    // constructor vacío obligatorio
}
```

- Constructor **explícito sin argumentos** obligatorio.
- Ya **no es necesario** implementar `Serializable` ni el `serialVersionUID`.
- `@Table` y `@Column` solo si se quieren nombres distintos a los predefinidos.

---

## Operaciones con `EntityManager`
```java
EntityManagerFactory emf = Persistence.createEntityManagerFactory("miUnidad");
EntityManager em = emf.createEntityManager();
```

| Operación | Método |
|-----------|--------|
| **Create** | `em.persist(objeto)` |
| **Read** | `em.find(Clase.class, id)` |
| **Update** | `em.merge(objeto)` |
| **Delete** | `em.remove(objeto)` |
| **List all** | `em.createQuery("SELECT e FROM Entidad e", Entidad.class).getResultList()` |

Otros métodos útiles: `clear`, `contains`, `detach`, `flush`.

Referencias:
- https://www.geeksforgeeks.org/jpa-crud/
- https://www.arquitecturajava.com/jpa-entitymanager-metodos/
- https://wiki.eclipse.org/EclipseLink/Examples/JPA/EMAPI

---

## Mapeo de relaciones — Notas importantes

- Usar `mappedBy` con el nombre que tiene en la **otra entidad** la referencia a la nuestra. Se puede omitir si se usa el nombre estándar, pero entonces EclipseLink crea una tabla extra para la relación.
- No hace falta `@JoinColumn` si se usan nombres estándar de entidades.
- Usar `List` en vez de `Set` si se quiere ordenación en el código.
- `cascade` no es solo para borrados: aplica también a inserciones y modificaciones.

---

## Referencias

- Tutorial JPA desde cero: https://www.arquitecturajava.com/jpa-un-ejemplo-desde-cero/
- Guía Jakarta Persistence: https://jakarta.ee/learn/starter-guides/how-to-store-and-retrieve-data-using-jakarta-persistence/
- Estrategias de generación de esquema: https://docs.oracle.com/javaee/7/tutorial/persistence-intro005.htm
- EclipseLink EntityManager API: https://wiki.eclipse.org/EclipseLink/Examples/JPA/EMAPIs
