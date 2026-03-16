# Práctica R1-UD1 — Gestión de ficheros en Java

> **Objetivo:** entender la diferencia entre las distintas formas de acceso a los ficheros y las ventajas e inconvenientes de cada enfoque. Superar los criterios de evaluación del Resultado de Aprendizaje 1 del módulo.

---

## Resultado de aprendizaje

**"Desarrolla aplicaciones que gestionan información almacenada en ficheros identificando el campo de aplicación de los mismos y utilizando clases específicas."**

| Criterio | Descripción | Estado |
|----------|-------------|--------|
| a) | Clases para la gestión de ficheros y directorios | *(ya visto en 1º)* |
| b) | Ventajas e inconvenientes de las distintas formas de acceso | |
| c) | Clases para recuperar información almacenada en ficheros | |
| d) | Clases para almacenar información en ficheros | |
| e) | Conversiones entre diferentes formatos de ficheros | *(próxima práctica)* |
| f) | Previsión y gestión de excepciones | |
| g) | Pruebas y documentación de las aplicaciones desarrolladas | |

---

## Introducción

Se propone implementar las pruebas de concepto del siguiente apartado para refrescar conocimientos del curso anterior, ampliarlos y entender las distintas formas de acceder a los ficheros.

**Grupos:** de dos a cuatro alumnos.

---

## Prueba 1 — Clase `Properties`

1. Crea el objeto `Properties` e inserta un pequeño conjunto de pares clave/valor con `setProperty`.
2. Guarda el objeto en un fichero usando `store()` y `storeToXML()`, pasándole un `FileOutputStream`.
3. Examina los ficheros creados.
4. Recupera los datos con `load()` y `loadFromXML()`, pasándole un `FileInputStream`.

> **Reflexión:** ¿Os serviría la clase `Properties` para guardar una colección de objetos tipo *Libro*? ¿Cómo lo haríais? Documentadlo.

---

## Prueba 2 — CSV con `PrintWriter` y `BufferedReader`

1. Crea una colección de objetos de tu interés (libros, coches, etc.).
2. Guarda y recupera la colección a/desde ficheros CSV.
3. Intercambia ficheros con un compañero y comprueba que su programa los lee correctamente.

### Cuestiones a probar

- ¿Trata bien los acentos y las eñes? Prueba a guardar con varias codificaciones distintas.
- ¿Qué pasa si generas el CSV con un procesador de textos (Word/Writer)?
- ¿Qué ocurre si un campo contiene una coma? ¿Podrías solucionarlo?

> **Reflexión:** ¿Mejoraría en algo usar ficheros binarios, bien sea con `DataOutputStream` o con `ObjectOutputStream`? Razonad la respuesta.

### ⚠️ Problema CSV en Windows / Excel

Versiones modernas de Excel no interpretan la coma como separador de columnas, sino el **punto y coma**. Si el CSV no se abre correctamente, sustituye `,` por `;`.

La causa suele estar en la configuración regional de Windows. Solución detallada: https://www.tictacsoluciones.com/blog/como-cambiar-delimitador-csv/

---

## Prueba 3 — Serialización

Realiza una prueba de concepto de serialización a y desde fichero usando `ObjectOutputStream` / `ObjectInputStream`.

---

## Prueba 4 — `RandomAccessFile`

Estudia el manejo de ficheros mediante `RandomAccessFile` para acceso aleatorio, opcionalmente combinado con registros de tamaño fijo.

### Ejercicio 4.1 — Array de enteros con acceso aleatorio

1. Define un `int[]` o `ArrayList<Integer>` de 20 posiciones.
2. Al iniciar, comprueba si existe `datos.bin`:
   - **Si existe:** lee 20 enteros con `DataInputStream.readInt()` y rellena la estructura.
   - **Si no existe:** créalo, escribe 20 ceros con `DataOutputStream.writeInt()` y rellena la estructura con ceros.
3. Abre el fichero en modo `rwd` con `RandomAccessFile`.
4. Bucle interactivo:
   - Muestra el contenido del array.
   - Pide una posición a modificar (negativo para salir).
   - Pide el nuevo valor.
   - Actualiza **tanto la estructura Java como el fichero**, escribiendo **solo esa posición**.

> **Recuerda:** un `int` ocupa 4 bytes en la representación nativa de Java. La posición en disco es `posición × 4`.

### Ejercicio 4.2 — Leer cabecera de un fichero BMP

1. Crea un archivo `.bmp` sin compresión con Paint, Pinta, etc.
2. Lee el ancho y el alto de la imagen según el [formato Windows Bitmap](https://es.wikipedia.org/wiki/Windows_bitmap) (los bytes están en orden little-endian; puedes usar `Integer.reverseBytes()`).
3. Usa `RandomAccessFile` para ir a esos bytes, modifica el tamaño y observa qué le ocurre a la imagen.
4. *(Añadido opcional)* Repite el ejercicio con `ImageIO.read()`, pero demuestra que también lo puedes hacer con `RandomAccessFile`.

### Ejercicio 4.3 — Apuntes de Fernando Berzal

Realiza los ejercicios de prueba de concepto del apartado *RandomAccessFile* de los apuntes de Berzal. Compara con la clase `RegistroContacto` de los códigos de ejemplo: analiza cómo hace la empaquetación en registros y si hay diferencias de tamaño.

### Ejercicio 4.4 — Colección con registros de tamaño fijo *(opcional)*

Guarda, lee y modifica un solo elemento de tu colección en un fichero usando `RandomAccessFile` y registros de tamaño fijo.

---

## Ampliación — Medición de tiempos *(opcional)*

Mide el tiempo que tarda en guardar un millón de registros sobreescribiendo un archivo completo frente a modificar un solo registro con `RandomAccessFile`, tanto al principio como al final del fichero.

> Ten en cuenta que muchos factores influyen en el tiempo de E/S: cachés, tamaños de bloque en lectura/escritura de disco, etc.

---

## Notas sobre registros de tamaño fijo

- `writeUTF` añade una marca de 2 bytes para el tamaño; es mejor evitarlo en registros fijos.
- Si un `String` ocupa menos del tamaño de registro, añade un carácter de fin (p. ej. `\0`) que no se use en los datos.
- `write(getBytes())` escribe en UTF-8 (letras acentuadas = 2 bytes) y permite especificar un rango `(offset, length)`.
- `writeBytes(String)` escribe en ISO-8859-1; para leerlo: `new String(buffer, StandardCharsets.ISO_8859_1)`.
- Con `String.format("%-10s", str)` puedes rellenar con espacios hasta una longitud fija.
- `writeChars(String)` usa UTF-16, 2 bytes por carácter; se lee luego con `readChar()`.
- Puedes comprobar el tamaño del fichero con `File`, `Files`, `Paths` o con `available()` nada más abrir el `DataInputStream`.

### Modos de apertura de `RandomAccessFile`

| Modo | Descripción |
|------|-------------|
| `"r"` | Solo lectura. Cualquier escritura lanza `IOException`. |
| `"rw"` | Lectura y escritura. Si no existe, lo crea. |
| `"rws"` | Como `rw`, pero cada actualización de contenido **y metadatos** se escribe síncronamente en disco. |
| `"rwd"` | Como `rw`, pero cada actualización de **contenido** se escribe síncronamente en disco (menos operaciones de E/S que `rws`). |

---

## Calificación

| Nivel | Requisitos |
|-------|------------|
| **Suficiente** | Documentación e interpretación de los apartados 1–4 y el ejercicio 6.1 o 6.2. Ejecución correcta de las modificaciones y preguntas del profesor en clase. |
| **Bien** | Lo anterior más el apartado 5. Ejecución correcta de las modificaciones y preguntas del profesor en clase. |
| **Excelente** | Todo lo anterior con todas las partes opcionales, la ampliación y el ejercicio 6.3. Ejecución correcta de las modificaciones y preguntas del profesor en clase. |

---

## Entrega

Documento **`R1-UD1.pdf`** con:
- Enlace al código (público en *cloud.educa.madrid.org*, Git u otros).
- Texto de **conclusiones individuales** por cada miembro del grupo.

---

## Referencias

- https://datos.codeandcoke.com/apuntes:ficheros
- https://es.wikipedia.org/wiki/Windows_bitmap
- https://docs.oracle.com/javase/8/docs/api/java/io/RandomAccessFile.html
- https://docs.oracle.com/javase/8/docs/api/java/util/Formatter.html
- https://www.tictacsoluciones.com/blog/como-cambiar-delimitador-csv/
