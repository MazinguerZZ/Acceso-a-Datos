# Cómo crear un proyecto JPA que usa Hibernate como proveedor de persistencia

## Pasos:

0. Instalar software Data Tools Platform para poder añadir perfiles de conexión a BD
- Help -> Install New Software 
- Works with: seleccionar primer site
- Seleccionar grupo "Database Development"

1.  Crear proyecto Maven

	1.1. Datos iniciales
	- GroupId: es.iesgoya (o algo similar)
	- ArtifacId: ProyectoJpaHib (o algo similar)
	- Package: es.iesgoya.proyectojpahib (se trata de la concatenación de groupId y artifactId, pero todo en minúscula).
	- Cambiar build-path para que use Java 1.8 en lugar de Java 1.5

	1.2. Añadir dependencias
	- Si hibernate 5.2 o anterior: Incluir dependencias "hibernate-entitymanager". A partir de hibernate 5.3 se debe usar dependencias "hibernate-core". 
	- También incluir dependencias "mysql-connector-java".

2. Añadir características JPA (perspectiva JPA)
- Properties en proyecto -> Project Facets -> Convert to faceted form...
- Marcar Java y JPA. 
- Pestaña Runtime asegurarse de que Java 1.8
- Pulsar enlace "Further configuration required":
	* Platform: Seleccionar "Hibernate (JPA 2.1)" y 
	* JPA Implementation: Seleccionar "Disable Library Configuration"
	* Connection: Add connection -> Crear conexión que llamaremos p.ej. "connProyectoJpaHib"

- Ya es un proyecto JPA-> Cambiar a perspectiva JPA
- Window -> Show View -> Data Source Explorer
- Comprobar que en la lista de conexiones aparece la que acabamos de crear 

3. Crear clases modelo o entidad a partir de las tablas de la BD
- File -> New -> Other -> JPA -> JPA Entities from Tables
	* Con la conexión añadida en el punto anterior seleccionada (connProyectoJpaHib), activar conexión pulsando botón
	* Seleccionar tablas de la base de datos -> Next
	* Detectará las asociaciones -> Next
	* Customize defaults:
		- key generator: identity
		- Entity access: Field
		- Associations fetch: Default
		- Collection properties type: java.util.List

4. Configurar unidad de persistencia
- Hacer doble-click en persistence.xml

	4.1. Clases gestionadas
	- Pestaña General, añadimos nuestras clases generadas o comprobamos que estén añadidas, y podemos marcar la opción de Exclude unlisted classes
	- Comprobar en la pestaña Source cómo van reflejándose los cambios

	4.2. Conexión
	- Pestaña Connection
	- Transaction type: Resource Local
	- JDBC connection properties: Populate from connection (seleccionar la conexión "connProyectoJpaHib")

	4.3. Opciones de Hibernate
	- Pestaña Hibernate
		* Database dialect: MySQL5 (InnoDB)
		* Driver class: com.mysql.jdbc.Driver
	- Pestaña Properties
		Añadir:
		* hibernate.hbm2ddl.auto: update
		* hibernate.show_sql: true
		* hibernate.format_sql: true

5. Clase aplicación
- Implementar las tareas utilizando las clases EntityManagerFactory y EntityManager.

6. Crear clases DAO
- Es una buena práctica generar clases DAO para implementar métodos CRUD (crear, leer, actualizar y borrar) y otros de utilidad con la entidad en cuestión.
- Los podemos crear a mano o con la ayuda de las Hibernate Tools pero para ello tenemos que:

	6.1. Revisar la consola de configuración que se nos habrá generado y comprobar que está seleccionado el radiobutton JPA y que la versión de Hibernate seleccionada es la 5.2

	6.2. Crear una configuración de ejecución (Hibernate Code Generation Configuration):
	- Ponerle un nombre descriptivo: configProyectoJpaHib
	- Pestaña Main:
		* Console configuration: ProyectoJpaHib
		* Output directory: Navegar hasta la carpeta donde queremos que genere las clases
	- Pestaña Exporters:
		* General settings: Marcar "Generate EJB3 annotations" (si no se marca, se generarán los DAO usando SessionFactory en lugar de EntityManager)
		* Exporters: Marcar solamente "DAO code"

	Pulsar Run y nos creará clases xxxxxxHome.java con los métodos que implementan operaciones básicas con BD. 