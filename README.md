
Reto-Backend Java
# Reto-Backend-Cesar-Fernandez

Breve descripción de la aplicación.

## Configuración

Antes de iniciar la aplicación, asegúrate de configurar las siguientes variables de entorno para la conexión a la base de datos:

- `spring.datasource.url=jdbc:${DATABASE_URL}`
- `spring.datasource.username=${DATABASE_USERNAME}`
- `spring.datasource.password=${DATABASE_PASSWORD}`

## Autenticación

Para acceder a los endpoints del CRUD, primero debes obtener un token a través del endpoint de autenticación:
http
POST /api/auth/login

 Se han creado dos usuarios en memoria para probar:
---

- **Administrador**
  - Usuario: `admin`
  - Contraseña: `password`
  - Rol: ADMIN (puede crear, eliminar o modificar usuarios)

- **Usuario**
  - Usuario: `user`
  - Contraseña: `password`
  - Rol: USER (puede solo leer)

Swagger
La aplicación también proporciona una interfaz de Swagger para obtener información sobre los endpoints y realizar pruebas. Puedes acceder a la interfaz de Swagger en la URL /swagger-ui.html.

Despliegue
La aplicación está desplegada en los servicios de Railway y se puede acceder a ella en la siguiente URL:

https://delightful-healing-production.up.railway.app

Uso
Para utilizar la aplicación, sigue los siguientes pasos:

Configura las variables de conexión a la base de datos.
Obtiene un token de autenticación realizando una petición POST a la URL /api/auth/login.
Utiliza el token para acceder a los endpoints del CRUD.
Espero que esta información te sea útil. ¡Disfruta utilizando la aplicación!
