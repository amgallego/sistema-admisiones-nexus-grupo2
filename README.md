Aquí tienes el README.md completo, siguiendo exactamente la estructura del formato que me enviaste e incorporando toda la información técnica, los integrantes y la configuración de la base de datos que hemos trabajado.

Markdown
# Nexus

Plataforma universitaria para la gestión digital de admisiones, oferta académica y control de matrículas.

## Introducción / Contexto

- **Descripción del problema:** Los procesos de inscripción y matrícula universitaria suelen estar fragmentados, generando procesos manuales lentos y falta de visibilidad para el aspirante sobre su estado de admisión.
- **Justificación:** La digitalización de estos flujos mejora la experiencia del estudiante, centraliza la información para la administración y permite un control eficiente del crecimiento institucional.
- **Dominio:** Gestión Académica y Procesos de Admisión Universitaria.

## Objetivos

**Objetivo General** Diseñar e implementar una plataforma web que permita gestionar de manera eficiente el proceso de inscripción, admisión y matrícula de estudiantes, facilitando la interacción entre aspirantes, estudiantes y personal administrativo.

**Objetivos Específicos** - Implementar un listado dinámico de carreras con visualización detallada de pensum y descripción.
- Desarrollar un módulo de inscripción que permita el registro de aspirantes y la persistencia de sus datos.
- Construir un panel administrativo para la gestión, aprobación y rechazo de solicitudes de ingreso.
- Habilitar un portal de estudiante para que los usuarios admitidos inicien sesión y gestionen su proceso de matrícula inicial.
- Establecer un sistema de autenticación y control de acceso basado en roles (Admin/Estudiante).

## Alcance del Proyecto (Scope)

**Qué se va a desarrollar:** - **Modelado de Datos:** Diseño e implementación del diagrama entidad-relación en PostgreSQL.
- **Persistencia de Datos:** Configuración de la capa de acceso a datos mediante el Repository Pattern (JPA).
- **Entidades de Dominio:** Creación de clases Java para `Curso`, `Usuario`, `UsuarioAdministrativo` y `Request` (Solicitudes).
- **Relaciones Bidireccionales:** Vinculación de usuarios con sus cursos y solicitudes de inscripción.

**Qué NO se va a desarrollar en esta versión (fuera de alcance):** - Interfaces de usuario finales (Frontend avanzado/Bootstrap).
- Lógica de negocio para la generación de horarios o gestión de docentes.
- Integración con pasarelas de pago externas.
- Microservicios o despliegue automatizado en la nube (Cloud).

## Tecnologías y Herramientas (Tech Stack)

- **Backend**: Spring Boot 3.x, Java 17, Spring Data JPA, Hibernate.
- **Frontend**: [Pendiente de definición según el Tech Stack inicial].
- **Base de datos**: PostgreSQL (Prisma Cloud en producción / DBeaver para gestión).
- **Otras herramientas**: Git, GitHub, Lombok, Postman.

## Integrantes del Equipo

| Nombre                  | Rol principal              | Usuario GitHub     |
|-------------------------|----------------------------|--------------------|
| David Quiroz Gonzalez   | Backend / Base de datos    | @Strikys12         |
| Ana Marcela Gallego     | UI Designer                | @Amgallego         |
| Miguel Angel Muñoz      | Frontend Lead              | @MiguelM1004       |
| Ana María Zapata        | Líder Frontend/Backend     | @AnamZapa          |



## Diagrama de Clases del Dominio (v1)

![Diagrama de Dominio v1](docs/diagrama-dominio-v1.png)  
*Diagrama inicial del modelo de dominio – versión 1. Se han implementado relaciones bidireccionales entre Usuario, Curso y Request utilizando `@ToString.Exclude` para evitar recursividad.*



## Instrucciones de Instalación y Ejecución (para desarrolladores)

### 1. Clonar el repositorio


git clone https://github.com/AnamZapa/sistema-admisiones-nexus-grupo2.git

### 2. Entrar al directorio


cd sistema-admisiones-nexus-grupo2

### 3. Configurar base de datos en `src/main/resources/application.properties


**Configuración para PostgreSQL (Prisma Cloud):**
properties
spring.datasource.url=jdbc:postgresql://db.prisma.io:5432/postgres?sslmode=require
spring.datasource.username=afbe3660994f8300a5512274c39fad928208254ccd7406e9207217deb1a01ee6
spring.datasource.password=sk_NpmOqthHk6IVj2Ey4t79K
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

### 4.  Ejecutar desde el IDE: Run -> NexusApplication.java