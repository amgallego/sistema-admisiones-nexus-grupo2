# Nexus

[Plataforma universitaria para la gestión digital de admisiones, oferta académica y control de matrículas]

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

**Qué se ha desarrollado (Fase Actual):** - **Modelado de Datos:** Diseño e implementación del diagrama entidad-relación que soporta el sistema en **PostgreSQL**.
- **Persistencia de Datos:** Configuración completa de la capa de acceso a datos mediante el **Repository Pattern** (JPA).
- **Entidades de Dominio:** Creación de clases Java para Carreras, Usuarios (Roles) y Solicitudes de inscripción.
- **Conectividad:** Configuración exitosa del `application.properties` para la comunicación con PostgresQL y validación de esquemas.

**Qué NO se va a desarrollar en esta versión (Fuera de alcance):** - Interfaces de usuario finales (Frontend avanzado/Bootstrap).
- Lógica de negocio para la generación de horarios o gestión de docentes.
- Integración con pasarelas de pago externas.
- Microservicios o despliegue automatizado en la nube (Cloud).

## Tecnologías y Herramientas (Tech Stack)

### Backend (Spring Boot 3.x / Java 17)
**Dependencias principales:**
- **Spring Data JPA**: Para la gestión de la persistencia y mapeo objeto-relacional (ORM).
- **PostgreSQL Driver**: Conector oficial para la comunicación con la base de datos.
- **Spring Web**: Para la creación de servicios RESTful (opcional/en progreso).
- **Lombok**: Para la reducción de código repetitivo (Boilerplate) en las entidades.

## Integrantes del Equipo

| Nombre                | Rol principal            | Usuario GitHub |
|-----------------------|--------------------------|------------|
| David Quiroz Gonzalez | Líder Frontend / Backend | @Strikys12 |
| Ana Marcela Gallego   | Frontend Lead            | @Amgallego |
| Miguel Angel Muñoz    | Backend / Base de datos  | @MiguelM1004 |
| Ana María Zapata      | UI Designer / QA         | @AnamZapa  |

---