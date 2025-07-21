# Sistema de Gestión Académica

Una API RESTful robusta para la gestión integral de datos académicos en instituciones educativas.

## Descripción del Proyecto

Este proyecto implementa una API REST con Spring Boot, diseñada para centralizar y automatizar la administración académica. Permite la gestión de usuarios (alumnos, profesores, administradores), la organización de cursos y categorías, y el manejo detallado de inscripciones de alumnos en categorías específicas de cursos. Una funcionalidad clave es la capacidad para que los profesores registren y actualicen las calificaciones de los alumnos.

## Características Principales

* **Gestión de Usuarios:** Creación, lectura, actualización y eliminación de perfiles de Alumnos, Profesores y Administradores.
* **Catálogo Académico:** Administración de Cursos y Categorías de aprendizaje.
* **Inscripciones Detalladas:** Registro de la relación alumno-curso-categoría, incluyendo fechas de inicio de aprendizaje y notas iniciales.
* **Gestión de Calificaciones:** Los profesores pueden consultar a sus alumnos y actualizar sus notas (nota1, nota2, nota3) en las categorías que imparten.
* **API RESTful:** Interfaz de programación clara y eficiente para la comunicación con aplicaciones frontend (web, móvil).
* **Seguridad:** Integración de Spring Security para autenticación y autorización (roles).
* **Persistencia de Datos:** Utilización de Spring Data JPA y Hibernate para la gestión de la base de datos relacional.

## Tecnologías Utilizadas

* **Backend:** Java 17, Spring Boot 3.x
* **Frameworks:** Spring Data JPA, Spring Security
* **Base de Datos:** MySQL
* **Construcción:** Maven
* **Otras Librerías:** Lombok, MapStruct, JWT impl

## Cómo Empezar

Sigue estos pasos para levantar y ejecutar el proyecto localmente:

### Prerequisitos

* Java Development Kit (JDK) 17 o superior.
* MySQL instalada y en ejecución.
* Maven instalado.

### Instalación y Ejecución

1.  **Clonar el Repositorio:**
    ```bash
    git clone [https://github.com/sandrocordova99/Proyecto-Spring-Backend.git](https://github.com/sandrocordova99/Proyecto-Spring-Backend.git)
    cd Proyecto-Spring-Backend
    ```

2.  **Configurar la Base de Datos:**
    * Crea una base de datos con el nombre `cibertec_db` (puedes usar otro nombre si lo prefieres, pero ajústalo en el `application.properties`).
    * Abre el archivo `src/main/resources/application.properties`.
    * Actualiza las propiedades de conexión a tu base de datos con tus credenciales:
        ```properties
        spring.datasource.url=jdbc:mysql://localhost:3306/cibertec_db
        spring.datasource.username=tu_usuario_db
        spring.datasource.password=tu_password_db
        # spring.jpa.hibernate.ddl-auto=update # Descomenta esta línea si quieres que Hibernate cree o actualice las tablas automáticamente
        ```

3.  **Construir el Proyecto:**
    ```bash
    mvn clean install
    ```

4.  **Ejecutar la Aplicación:**
    * Desde tu IDE (IntelliJ, Eclipse, VS Code): Ejecuta la clase principal `IntegradorApplication.java` (o el nombre de tu clase principal con el método `main`).
    * Desde la terminal (después de construir):
        ```bash
        java -jar target/integrador-0.0.1-SNAPSHOT.jar
        ```
        *(Nota: el nombre del archivo `.jar` puede variar ligeramente, verifica el nombre exacto en tu carpeta `target` después de `mvn clean install`.)*

## Documentación de la API (Postman)

Todos los endpoints de la API están documentados en una Colección de Postman interactiva, que incluye ejemplos de solicitudes y respuestas.

* **Importar la Colección a Postman:**
    Puedes importar la colección directamente a tu Postman. El archivo `API_Cibertec_Collection.json` se encuentra en la raíz de este repositorio.

## Contacto

* **Email:** sandrocordova99@hotmail.com
* **Teléfono:** 923323888
