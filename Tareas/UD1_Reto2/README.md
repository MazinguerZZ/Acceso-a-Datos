# Práctica R2-UD1 — XML, JSON y conversión entre formatos

> **Objetivo:** afianzar el trabajo con XML y JSON y la conversión entre estos dos formatos. Superar el criterio de evaluación del RA1: *"e) Se han utilizado clases para realizar conversiones entre diferentes formatos de ficheros."*

---

## Introducción

Se propone implementar pruebas basadas en los tutoriales del aula virtual, adaptándolas a vuestras propias clases. El objetivo es adquirir soltura con el manejo de XML y JSON, su paso a/desde objetos, y entender su funcionamiento y posibilidades.

**Grupos:** de dos a cuatro integrantes. Documentad quién se ha ocupado de cada parte y poned el trabajo en común para que todos conozcáis el conjunto.

> **Nota:** en todos los ejercicios, en vez de usar las clases básicas de los ejemplos (POJOs genéricos), usad las que elegisteis en el reto anterior.

---

## Apartado 1 — Serialización y deserialización con XStream

Sigue el tutorial XStream de Laura Lozano y realiza sus actividades propuestas.

> **Nota de paquete:** no uses `transparencias` como nombre de paquete. Usa `pruebasXStream`.

Se recomienda hacer todos los ejemplos del tutorial para familiarizarse con la librería antes de abordar las actividades.

### 1.1 — Serialización básica a fichero

- A partir de una clase tipo `Libro.java` (POJO), genera un XML para un objeto de dicha clase y restáuralo.
- Guarda el XML en un fichero de texto y reconstruye el objeto desde ese fichero.
- Puedes hacerlo en dos clases con sus propios `main`. Ej: `GuardaLibro.java` y `RecuperaLibro.java`.

### 1.2 — Aliases de clases y atributos

Repite lo anterior pero usando aliases de clases y atributos para generar un XML similar al de la página 10 de las transparencias.

Referencia: http://x-stream.github.io/alias-tutorial.html

### 1.3 — Persistencia a fichero

Implementa la actividad de la página 13 de las transparencias.

> **Reflexión:** ¿es más cómodo el método descrito en la página 12 que lo que hiciste en el apartado 1.1?

### 1.4 — Ejercicios del fichero `Ejercicios1.pdf`

Lee los ejercicios del fichero adjunto e implementa las variaciones que añadan algún detalle adicional a lo anterior.

### 1.5 — Personalización avanzada con XStream *(para «Bien» y «Excelente»)*

A partir de los ejercicios de `Ejercicios2.pdf` (exceptuando los dos últimos):

- Pruebas de personalización de XML con atributos de etiquetas.
- Colecciones de objetos y personalización de su XML (generación y recuperación).
- Demuestra que la regeneración de un objeto **falla** si no se usan los mismos aliases y/o converters que en su generación.

---

## Apartado 2 — Conversión XML ↔ JSON pasando por objetos

Puedes basarte en los dos últimos ejercicios de `Ejercicios2.pdf`.

### 2.1 — Generación de JSON con XStream

Adapta `PruebaXStreamJSON.java` para:

- Usar una clase distinta a la del ejemplo (cambia el paquete a `pruebasJson`).
- Guardar en un archivo de texto plano el JSON resultante usando `JsonHierarchicalStreamDriver`.

### 2.2 — Lectura del JSON y reconstrucción del objeto

Lee el fichero JSON generado en el paso anterior y reconstruye el objeto. Elige al menos una de estas opciones:

| Opción | Herramienta |
|--------|-------------|
| A | Driver `JettisonMappedXmlDriver` con XStream |
| B | Librería **GSON** (ver referencias) |
| C | Librería **Stleary JSON-java** (ver material de Manuel Barroso) |

### 2.3 — Comparativa de opciones

Compara al menos dos de las opciones del apartado 2.2 reflexionando sobre su facilidad o dificultad de uso.

---

## Apartado 3 — Lectura y generación de XML con DOM *(para «Bien» y «Excelente»)*

- Lee alguno de los ficheros XML generados en el Apartado 1 usando las **librerías DOM de Java** (no requieren JARs externos), siguiendo el tutorial de Miguel Sutil del aula virtual.
- Genera ficheros XML con DOM que puedan ser leídos por las clases de reconstrucción de objetos del Apartado 1.

> **Reflexión:** problemas encontrados, qué es posible y qué no, con qué sistema resulta más cómodo trabajar, y ventajas e inconvenientes de cada enfoque.

---

## Apartado 4 — Datos masivos con Mockaroo *(para «Excelente»)*

1. Genera conjuntos grandes de datos con https://www.mockaroo.com/ (XML o JSON).
2. Realiza la lectura y conversión de XML a JSON o viceversa.
3. Intercambia ficheros con otros compañeros, proporcionando la descripción de los POJOs.
4. Usa arrays y/u objetos anidados con `{}` y `[]`.

---

## Calificación

| Nivel | Requisitos |
|-------|------------|
| **Suficiente** | Apartados 1 (sin el 1.5) y 2 con sus variantes, documentados e interpretados. |
| **Bien** | Lo anterior más el apartado 1.5 y el apartado 3 **o** el 4. |
| **Excelente** | Todos los apartados correctamente desarrollados y documentados. |

---

## Entrega

Documento **`R2-UD1.pdf`** con:
- Descripción del trabajo realizado y quién se ocupó de cada parte.
- **Conclusiones individuales** de cada miembro del grupo.

---

## Notas de configuración — XStream

### Añadir la librería al proyecto

1. Descarga *XStream Core Only* desde http://x-stream.github.io/download.html.
2. Colócalo en una carpeta `lib/` al mismo nivel que `src/` y `bin/`.
3. Añádelo al *Build Path* con **"Add JARs"** (no *Add External JARs*) para que quede dentro del proyecto.
4. Añádelo a **ModulePath** si usas módulos, o a **ClassPath** si no.

### Configuración de módulos

Si usas módulos, añade al fichero `module-info.java`:
```java
module pruebasXStream {
    requires xstream;
    opens pruebasXStream to xstream;
    exports pruebasXStream;
}
```

### Permisos de deserialización

El error de permisos al deserializar se arregla así:
```java
xstream.addPermission(AnyTypePermission.ANY);
```

### Arreglo para `PersistenceFromXML`

El ejemplo original no funciona. Corrección:
```java
XStream xstream = new XStream(new DomDriver());
xstream.addPermission(AnyTypePermission.ANY);
PersistenceStrategy strategy = new FilePersistenceStrategy(new File("."), xstream);
```

> Tras esto puede quejarse de que `Autor` no tiene constructor vacío — eso ya lo sabéis solucionar.

---

## Referencias

### XStream
- Aliases: https://x-stream.github.io/alias-tutorial.html
- Anotaciones: https://x-stream.github.io/annotations-tutorial.html
- Descarga: http://x-stream.github.io/download.html

### JSON
- Stleary JSON-java: https://github.com/stleary/JSON-java
- Guía del usuario GSON: *(ver aula virtual)*

### Solución de problemas
- Problema con módulos: https://stackoverflow.com/questions/69912952/eclipse-maven-xstream-unable-to-make-field-accessible-module-does-not-opens
- Permisos XStream (`ForbiddenClassException`): https://stackoverflow.com/questions/30812293/com-thoughtworks-xstream-security-forbiddenclassexception
