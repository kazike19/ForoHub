# ForoHub API
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)

![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)

![Spring Security](https://img.shields.io/badge/Spring_Security-6DB33F?style=for-the-badge&logo=spring-security&logoColor=white)

![Spring Data JPA](https://img.shields.io/badge/Spring_Data_JPA-6DB33F?style=for-the-badge&logo=spring&logoColor=white)

![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)

![Maven](https://img.shields.io/badge/Apache_Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)


## Descripción del Proyecto

ForoHub es una API RESTful desarrollada con Spring Boot 3 que simula un foro de discusión. Permite a los usuarios registrarse, crear tópicos de discusión, y participar en ellos publicando respuestas. La API está diseñada siguiendo principios REST y buenas prácticas de seguridad y validación de datos.

Este proyecto forma parte de la formación de Alura Latam y Oracle One.

---

## 🌟Funcionalidades Principales

* **Autenticación y Autorización (JWT):** Acceso seguro a los recursos de la API.
* **Gestión de Tópicos:** Crear, listar, ver detalles, actualizar y eliminar tópicos de discusión.
* **Gestión de Respuestas:** Publicar, listar, ver detalles, actualizar y eliminar respuestas a los tópicos.
* **Validación de Datos:** Asegura la integridad de la información entrante.
* **Control de Duplicidad:** Previene la creación y actualización de tópicos con títulos, mensajes y cursos duplicados.

## 🛠️ Tecnologías Utilizadas

* **Java 17+:** Lenguaje de programación principal.
* **Spring Boot 3.x:** Framework para el desarrollo de la aplicación.
* **Spring Data JPA:** Para la persistencia de datos y la interacción con la base de datos.
* **Spring Security (JWT):** Para la seguridad y autentificacion de usuarios.
* **MySQL:** Como base de datos
* **Maven:** Herramienta de gestión de proyectos y dependencias.ias

---

## 🚀Configuración y Ejecución

Para levantar el proyecto en tu máquina local, sigue estos pasos:

### 1. Clona el Repositorio

```bash
git clone https://github.com/kazike19/ForoHub.git
cd ForoHub
```

### 2. Configura la Base de Datos

Crea una base de datos MySQL. Puedes usar un nombre como forohub_db.

Abre el archivo `src/main/resources/application.properties` y configura las credenciales de tu base de datos:

```properties
# Configuracion del servidor
server.port=9099

# Configuracion de la base de datos
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://{DB_HOST}/forohub
spring.datasource.username=tu_usuario_mysql
spring.datasource.password=tu_password_mysql
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect

# Configuracion de Token
api.security.token.secret = ${JWT_SECRET::123456789}
```

> ### Nota: Realizar esto una vez clonado el repositorio

### 3. Ejecuta la Aplicación

Puedes ejecutar la aplicación directamente desde tu IDE IntelliJ.

La api estara funcionando en `http://localhost:9099`.

### Endpoints de la API

A continuación, se detallan los endpoints disponibles en la API de ForoHub, junto con ejemplos de los JSONs que puedes enviar en tus peticiones.

**Base URL:** `http://localhost:9099`

#### Registro de usuarios
**- Registro de usuarios nuevos** `http://localhost:9099/auth/registrar`

Este endpoint permite registrar un usuario en la base de datos.

- Request Body (JSON):
```json
{
	"nombre": "Jessica Prueba",
	"email": "jessicaprueba@foro.hub",
	"contrasena": "123456"
}
```

- Response (JSON - 201 Created)):
```json
{
    "id": 3,
    "nombre": "Jessica Prueba",
    "email": "jessicaprueba@foro.hub"
}
```

##### Autenticación
**- Autenticar un usuario y obtener un token JWT** `http://localhost:9099/auth/login`

Este endpoint permite a un usuario autenticarse y recibir un JSON Web Token (JWT) que deberá incluir en los headers de las subsiguientes peticiones a los endpoints protegidos.

- Request Body (JSON):
```json
{
    "email": "usuario@example.com",
    "contrasena": "tu_password_segura"
}
```

- Response (JSON - Ejemplo de Token):
```json
{
    "token": "ejemplo_de_jwt_aqui"
}
```

##### Tópicos
**- Crear un nuevo tópico** `POST /topico`
Crea un nuevo tópico de discusión en el foro.
Requiere autenticación.
- Request Body (JSON):
```json
{
	"idUsuario": 3,
    "titulo": "Prueba",
    "mensaje": "Esto es una prueba",
    "curso": {
        "nombre": "Prueba",
        "categoria": "GENERAL"
    }
}
```
- Response (JSON - 200 OK):
```json
{
    "id": 15,
    "titulo": "Prueba",
    "mensaje": "Esto es una prueba",
    "fecha": "2025-07-28T19:41:28.5333878",
    "status": true,
    "autor": {
        "id": 3,
        "nombre": "Jessica Prueba",
        "email": "jessicaprueba@foro.hub"
    },
    "curso": {
        "id": 7,
        "nombre": "Texto para pruebas en video para borrar",
        "categoria": "GENERAL"
    },
    "respuestas": []
}
```

**- Listar todos los tópicos** `GET /topico`

Obtiene una lista paginada de todos los tópicos.

Requiere autenticación.
- **Parámetros de Query (Opcionales):**
	- `?page=0`: Número de página (por defecto 0).
	- `?size=10`: Cantidad de elementos por página (por defecto 10).

- Response (JSON - 200 OK):
```json
{
    "content": [
        {
            "id": 1,
            "titulo": "¿Cuál es la mejor forma de aprender Spring Boot en 2025?",
            "mensaje": "Estoy empezando en el desarrollo backend con Java",
            "fecha": "2025-07-21T15:09:32",
            "status": true,
            "autor": {
				"id": 3,
				"nombre": "Jessica Prueba",
				"email": "jessicaprueba@foro.hub"
        },
            "curso": "Backend Java"
        },
        {
            "id": 2,
            "titulo": "Prueba de posteo",
            "mensaje": "Esto es una prueba",
            "fecha": "2025-07-28T17:58:29",
            "status": true,
            "autor": {
				"id": 3,
				"nombre": "Jessica Prueba",
				"email": "jessicaprueba@foro.hub",
                "curso": "Desarrollo Frontend"
        }
}
```

**- Obtener detalle de un tópico con sus respuestas** `GET /topico/{id}`
Obtiene los detalles de un tópico específico, incluyendo todas las respuestas asociadas a él.
Requiere autenticación.
**URL: GET**`http://localhost:9099/topico/1/respuesta` (reemplaza 1 con el ID del tópico que deseas)
- Response (JSON - 200 OK):
```json
{
    "id": 1,
    "titulo": "¿Cuál es la mejor forma de aprender Spring Boot en 2025?",
    "mensaje": "Estoy empezando en el desarrollo backend con Java",
    "fecha": "2025-07-21T15:09:32",
    "status": true,
    "autor": {
        "id": 3,
        "nombre": "Jessica Prueba",
        "email": "jessicaprueba@foro.hub"
    },
    "curso": {
        "id": 3,
        "nombre": "Backend",
        "categoria": "ESPECIAL"
    },
    "respuestas": [
        {
            "id": 1,
            "mensaje": "Te recomiendo empezar con los fundamentos de Java y luego un curso de Spring Boot.",
            "topico": {
                "id": 1,
                "titulo": "¿Cuál es la mejor forma de aprender Spring Boot en 2025?"
            },
            "fechaCreacion": "2025-07-22",
            "autor": {
                "id": 5,
                "nombre": "Pedro",
                "email": "pedro@example.com"
            }
        },
        {
            "id": 2,
            "mensaje": "No olvides practicar mucho con proyectos pequeños para aplicar lo aprendido.",
            "topico": {
                "id": 1,
                "titulo": "¿Cuál es la mejor forma de aprender Spring Boot en 2025?"
            },
            "fechaCreacion": "2025-07-23",
            "autor": {
                "id": 6,
                "nombre": "Ana",
                "email": "ana@example.com"
            }
        }
    ]
}
```


**- Actualizar un tópico existente** `PUT /topico/{id}` 

Actualiza el título, mensaje o curso de un tópico. Se valida la unicidad (título, mensaje y curso combinados) para evitar duplicados. 

Requiere autenticación.

**URL: GET** `PUT http://localhost:9099/topico/1` (reemplaza 1 con el ID del tópico que deseas)
- Request Body (JSON - Campos opcionales)::
```json
{
    "titulo": "Nuevo título actualizado",
    "mensaje": "Mensaje revisado del tópico.",
    "idCurso": 3
}
```

- Response (JSON - 200 OK):
```json
{
    "id": 1,
    "titulo": "Nuevo título actualizado",
    "mensaje": "Mensaje revisado del tópico.",
    "fecha": "2025-07-21T15:42:58",
    "status": true,
    "autor": {
        "id": 2,
        "nombre": "Jessica2",
        "email": "jessica2@foro.hub"
    },
    "curso": {
        "id": 3,
        "nombre": "Backend pero en prueba 2",
        "categoria": "ESPECIAL"
    },
}
```
- Error Response (JSON - 409 Conflict si hay duplicidad):
```json
 {
    "timestamp": "2025-07-28T18:45:00.000+00:00",
    "status": 409,
    "error": "Conflict",
    "path": "/topico/1",
    "message": "Ya existe un tópico con el mismo título, mensaje y curso."
}
```

**- Eliminar un tópico** `DELETE /topico/{id}`

Elimina lógicamente un tópico (cambia su estado a inactivo). 

Requiere autenticación.

**URL: DELETE** `http://localhost:9099/topico/1` (reemplaza 1 con el ID del tópico a eliminar)

- **Response (204 No Content):**
    Una respuesta exitosa no devolverá ningún cuerpo, solo un status 204 No Content.

##### Respuestas

**- Crear una nueva respuesta a un tópico** `POST /respuestas`

Crea una nueva respuesta para un tópico existente. 

Requiere autenticación.

- Request Body (JSON - Campos opcionales)::
```json
{
    "mensaje": "¡Excelente punto, estoy de acuerdo con tu análisis!",
    "idTopico": 1,
    "idAutor": 2
}
```

- Response (JSON - 201 Created):
```json
{
    "id": 123,
    "mensaje": "¡Excelente punto, estoy de acuerdo con tu análisis!",
    "topico": {
        "id": 1,
        "titulo": "¿Cuál es la mejor forma de aprender Spring Boot en 2025?"
    },
    "fechaCreacion": "2025-07-28",
    "autor": {
        "id": 2,
        "nombre": "Jessica2",
        "email": "jessica2@foro.hub"
    }
}
```


**- Listar respuestas por ID de tópico** `GET /respuestas/topico/{id}`
Obtiene una lista paginada de respuestas para un tópico específico.

Requiere autenticación.

- Response (JSON - 200 OK):
```json
{
    "content": [
        {
            "id": 101,
            "mensaje": "Te recomiendo empezar con los fundamentos de Java y luego un curso de Spring Boot.",
            "topico": {
                "id": 1,
                "titulo": "¿Cuál es la mejor forma de aprender Spring Boot en 2025?"
            },
            "fechaCreacion": "2025-07-22",
            "autor": {
                "id": 5,
                "nombre": "Pedro",
                "email": "pedro@example.com"
            }
        },
        {
            "id": 102,
            "mensaje": "No olvides practicar mucho con proyectos pequeños para aplicar lo aprendido.",
            "topico": {
                "id": 1,
                "titulo": "¿Cuál es la mejor forma de aprender Spring Boot en 2025?"
            },
            "fechaCreacion": "2025-07-23",
            "autor": {
                "id": 6,
                "nombre": "Ana",
                "email": "ana@example.com"
            }
}
```

**- Actualizar una respuesta existente** `PUT /respuestas/{id}`

Actualiza el mensaje de una respuesta. 

Requiere autenticación.

**URL: PUT** `http://localhost:9099/respuestas/1` (reemplaza 1 con el ID de la respuesta a actualizar)
- Request Body (JSON):

```json
{
    "mensaje": "Mi mensaje ha sido actualizado para reflejar la información más reciente. ¡Gracias!"
}
```

- Response (JSON - 200 OK):

```json
{
    "id": 1,
    "mensaje": "Mi mensaje ha sido actualizado para reflejar la información más reciente. ¡Gracias!",
    "topico": {
        "id": 1,
        "titulo": "¿Cuál es la mejor forma de aprender Spring Boot en 2025?"
    },
    "fechaCreacion": "2025-07-22",
    "autor": {
        "id": 5,
        "nombre": "Pedro",
        "email": "pedro@example.com"
    }
}
```


**- Eliminar una respuesta** `DELETE /respuestas/{id}`
Elimina una respuesta de la base de datos.
Requiere autenticación.
- **URL: DELETE** `http://localhost:9099/respuestas/1` (reemplaza 1 con el ID del tópico a eliminar)

- **Response (204 No Content):**
    Una respuesta exitosa no devolverá ningún cuerpo, solo un status 204 No Content.


## Estructura del Proyecto
```
├── README.md
├── src/
├─── main/
│       ├── controller/
│       │   ├── AuthController.java
│       │   ├── RespuestaController.java
│       │   ├── TopicoController.java
│       │   └── UsuarioController.java
│       │   
│       ├── domain/
│       │   ├── Curso/
│       │   │   ├── Categoria.java
│       │   │   ├── CursoModel.java
│       │   │   ├── CursoRepository.java
│       │   │   ├── CursoService.java
│       │   │   ├── DatosCurso.java
│       │   │   └── DatosDetallesCurso.java
│       │   │
│       │   ├── Respuesta/
│       │   │   ├── DatosActualizarRespuesta.java
│       │   │   ├── DatosDetalleRespuesta.java
│       │   │   ├── DatosRegistroRespuesta.java
│       │   │   ├── RespuestaModel.java
│       │   │   ├── RespuestaRepository.java
│       │   │   ├── RespuestaService.java
│       │   │   └── RespuestaServiceImplementacion.java
│       │   │
│       │   ├── Topico/
│       │   │   ├── ActualizarTopico.java
│       │   │   ├── DatosActualizarTopico.java
│       │   │   ├── DatosDetalleTopico.java
│       │   │   ├── DatosDetalleTopicoResumen.java
│       │   │   ├── DatosDePosteoTopico.java
│       │   │   ├── EliminarTopico.java
│       │   │   ├── PosteoDeTopicos.java
│       │   │   ├── TopicoModel.java
│       │   │   ├── TopicoRepository.java
│       │   │   ├── TopicoService.java
│       │   │   └── TopicoServiceImplementacion.java
│       │   │
│       │   ├── Usuario/
│       │   │   ├── AuthService.java
│       │   │   ├── DatosAuth.java
│       │   │   ├── DatosDetalleUsuario.java
│       │   │   ├── DatosDetalleUsuarioResumen.java
│       │   │   ├── DatosRegistroUsuario.java
│       │   │   ├── RegistroUsuarioService.java
│       │   │   ├── UsuarioModel.java
│       │   │   └── UsuarioRepository.java
│       │   └── ValidacionExcepcion.
│       │   
│       └─── infra/
│           ├── exceptions/
│           │   └── GestorDeErrores.java
│           └── security/
│               ├── DatosTokenJWT.java
│               ├── SecurityConfig.java
│               ├── SecurityFilter.java
│               └── TokenService.java
│
├─────── LiteraaluraApplication.java
│
└─── resources/
        └── db.migration/
            └── V1__create-tables-usuario-curso-topicos-respuesta.sql

```

## Demostración en Video

Puedes ver una breve demostración del funcionamiento de la aplicación en el siguiente enlace:

[Demostración de ForoHub](https://drive.google.com/file/d/1Wbg6oqDOstgtsNgxecLU5V2pCnP7Fc0y/view?usp=drive_link)
