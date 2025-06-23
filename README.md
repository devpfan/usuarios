# 📘 Microservicio de Usuarios – Fluxem Barbería

**Proyecto:** Barbería  
**Empresa:** Fluxem  
**Módulo:** Usuarios  
**Stack:** Java 21, Spring Boot 3.5.3, PostgreSQL, Gradle, JWT, Swagger

---

## 🎯 Objetivo
Este microservicio gestiona la autenticación, autorización y administración de usuarios y roles. Es parte de una arquitectura de microservicios y será consumido por otros módulos como citas, ventas y clientes.

---

## 📦 Estructura del Proyecto

```
com.fluxem.barberia.usuarios
├── config               # Configuración general (CORS)
├── controller           # Controladores REST (Usuarios, Roles, Auth)
├── dto                  # Clases de entrada/salida (DTOs)
├── domain               # 
    ├── entity           # Entidades JPA (Usuario, Rol)
├── repository           # Interfaces JPA (UsuarioRepository, RolRepository)
├── security             # JWT, filtros y configuración de Spring Security
├── service
│   ├── impl             # Implementaciones de servicios
└── exception            # (Opcional) Manejo centralizado de errores
```

---

## 🧱 Base de Datos
PostgreSQL con las siguientes tablas:

```sql
usuarios(id_usuario, nombre, correo, telefono, contraseña_hash, estado, fecha_creacion)
roles(id_rol, nombre, descripcion)
usuario_rol(id_usuario, id_rol)
```

Relación: muchos-a-muchos entre `usuarios` y `roles`.

---

## 🔐 Seguridad

- **Autenticación:** JWT
- **Autorización:** Spring Security con filtros personalizados
- **Login:** `/auth/login` devuelve token JWT
- **Protección de endpoints:** mediante roles (`ROLE_ADMIN`, `ROLE_USER`, etc.)
- **Contraseñas:** encriptadas con BCrypt

---

## 🛠️ Funcionalidades del microservicio

| Funcionalidad                  | Endpoint                  | Método |
|-------------------------------|---------------------------|--------|
| Registrar nuevo usuario       | `/api/usuarios`           | POST   |
| Listar todos los usuarios     | `/api/usuarios`           | GET    |
| Obtener usuario por ID        | `/api/usuarios/{id}`      | GET    |
| Eliminar usuario              | `/api/usuarios/{id}`      | DELETE |
| Listar todos los roles        | `/api/roles`              | GET    |
| Obtener rol por nombre        | `/api/roles/{nombre}`     | GET    |
| Login con JWT                 | `/auth/login`             | POST   |

> Todos los endpoints requieren token JWT, excepto `/auth/login`.

---

## 🧪 Swagger / OpenAPI

Disponible en:
- Swagger UI: `http://localhost:8081/swagger-ui.html`
- JSON API Docs: `http://localhost:8081/v3/api-docs`

Incluye:
- Documentación por endpoint
- Pruebas desde navegador con JWT

---

## ⚙️ Configuraciones clave

### `application.yml`:
- Puerto: `8081`
- Conexión a PostgreSQL
- Propiedades JWT (`secret`, `expiration`)
- Swagger configurado
- CORS habilitado globalmente

---

## 📩 Headers para consumo externo

```http
Authorization: Bearer <token_jwt>
Content-Type: application/json
```

---

## 📌 Dependencias principales (Gradle)

```groovy
implementation 'org.springframework.boot:spring-boot-starter-web'
implementation 'org.springframework.boot:spring-boot-starter-security'
implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
implementation 'org.postgresql:postgresql'
implementation 'io.jsonwebtoken:jjwt-api:0.11.5'
implementation 'org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0'
```

---

