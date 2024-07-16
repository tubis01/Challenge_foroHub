# Challenge_foroHub
![image](https://github.com/user-attachments/assets/7981fd6f-a7bb-4a0e-b633-62567fe55a75)


## Descripción

Challenge_foroHub es una aplicación de foro desarrollada con Spring Boot, que permite a los usuarios crear, modificar, y discutir sobre diversos tópicos. La aplicación gestiona tópicos, respuestas, usuarios, y cursos, proporcionando una plataforma interactiva para la comunidad.

## Características

- **Gestión de Tópicos**: Los usuarios pueden registrar nuevos tópicos, actualizarlos, marcarlos como resueltos, y eliminarlos.
- **Respuestas a Tópicos**: Los usuarios pueden responder a tópicos existentes, promoviendo la discusión y el intercambio de información.
- **Gestión de Usuarios**: La aplicación permite la creación y gestión de usuarios.
- **Gestión de Cursos**: Los cursos pueden ser creados y gestionados, permitiendo asociar tópicos a cursos específicos.
- **Seguridad**: Implementación de seguridad básica con Spring Security, incluyendo autenticación y autorización.

## Tecnologías Utilizadas

- Java
- Spring Boot
- Maven
- SQL

### Endpoints

La aplicación expone varios endpoints para la gestión de tópicos, usuarios, y cursos:

- **Tópicos**:
  - Listar todos los tópicos: `GET /topicos/listar`
  - Obtener un tópico por ID: `GET /topicos/{id}`
  - Registrar un tópico: `POST /topicos/registrar`
  - Actualizar un tópico: `PUT /topicos/actualizar/{id}`
  - Marcar un tópico como resuelto: `PUT /topicos/resuelto/{id}`
  - Eliminar un tópico: `DELETE /topicos/eliminar/{id}`
    
    - **Usuarios**:
  - Registrar un nuevo usuario: `POST /usuarios/registrar`
  - Obtener detalles de un usuario por ID: `GET /usuarios/{id}`
  - Actualizar un usuario: `PUT /usuarios/actualizar/{id}`
  - Eliminar un usuario: `DELETE /usuarios/eliminar/{id}`
  - Listar todos los usuarios: `GET /usuarios/listar`

    - **Cursos**:
  - Crear un nuevo curso: `POST /cursos/crear`
  - Obtener detalles de un curso por ID: `GET /cursos/{id}`
  - Actualizar un curso: `PUT /cursos/actualizar/{id}`
  - Eliminar un curso: `DELETE /cursos/eliminar/{id}`
  - Activar un curso: `PATCH /cursos/activar/{id}`
  - Desactivar un curso: `PATCH /cursos/desactivar/{id}`
  - Listar todos los cursos: `GET /cursos/listar`
