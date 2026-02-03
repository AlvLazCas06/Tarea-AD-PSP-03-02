# Spring Security con JWT

Implementación de Spring Security con autenticación JWT usando la librería jjwt.

## Configuración

### Usuarios Pre-cargados

El sistema crea automáticamente dos usuarios al iniciar:

1. **Usuario normal**
   - Username: `user`
   - Password: `password`
   - Rol: `USER`

2. **Administrador**
   - Username: `admin`
   - Password: `admin`
   - Roles: `ADMIN`, `USER`

## Endpoints

### 1. Endpoint Público (Sin autenticación)

**GET** `/public/hello`

No requiere autenticación.

```bash
curl -X GET http://localhost:8080/public/hello
```

Respuesta:
```json
{
  "message": "Este es un endpoint público",
  "description": "No requiere autenticación para acceder"
}
```

### 2. Login (Obtener Token)

**POST** `/auth/login`

```bash
curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "user",
    "password": "password"
  }'
```

Respuesta:
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9...",
  "username": "user",
  "message": "Login exitoso"
}
```

### 3. Endpoint Protegido (Requiere autenticación)

**GET** `/secured/hello`

Requiere token JWT en el header Authorization.

```bash
curl -X GET http://localhost:8080/secured/hello \
  -H "Authorization: Bearer {TU_TOKEN_JWT}"
```

Respuesta:
```json
{
  "message": "Este es un endpoint protegido",
  "description": "Requiere autenticación JWT para acceder",
  "user": "user"
}
```

### 4. Endpoint de Administrador (Requiere rol ADMIN)

**GET** `/admin/hello`

Requiere token JWT y rol ADMIN.

```bash
curl -X GET http://localhost:8080/admin/hello \
  -H "Authorization: Bearer {TU_TOKEN_ADMIN}"
```

Respuesta:
```json
{
  "message": "Este es un endpoint de administrador",
  "description": "Solo accesible para usuarios con rol ADMIN",
  "user": "admin"
}
```

## Estructura del Proyecto

```
src/main/java/com/salesianostriana/dam/security/
├── model/
│   ├── User.java              # Entidad de usuario con UserDetails
│   ├── UserRole.java          # Enum con roles (USER, ADMIN)
│   ├── Product.java
│   └── Category.java
├── repository/
│   ├── UserRepository.java    # Repositorio JPA para usuarios
│   ├── ProductRepository.java
│   └── CategoryRepository.java
├── service/
│   ├── UserService.java       # UserDetailsService personalizado
│   ├── AuthService.java       # Servicio de autenticación
│   ├── ProductService.java
│   └── CategoryService.java
├── security/
│   ├── JwtService.java        # Servicio para generación/validación JWT
│   ├── JwtAuthenticationFilter.java  # Filtro para interceptar requests
│   └── SecurityConfig.java    # Configuración de seguridad
├── controller/
│   ├── AuthController.java    # Controlador de autenticación
│   └── DemoController.java    # Controlador con endpoints de ejemplo
├── dto/
│   ├── LoginRequest.java      # DTO para petición de login
│   └── AuthResponse.java      # DTO para respuesta de autenticación
├── init/
│   └── DataInitializer.java   # Inicialización de datos de prueba
└── SecurityApplication.java
```

## Configuración JWT

En `application.properties`:

```properties
# JWT Configuration
jwt.secret=404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970
jwt.expiration=86400000  # 24 horas en milisegundos
```

## Cómo funciona

1. **Login**: El usuario envía sus credenciales a `/auth/login`
2. **Token**: El servidor valida las credenciales y devuelve un token JWT
3. **Acceso**: El cliente incluye el token en el header `Authorization: Bearer {token}` para acceder a endpoints protegidos
4. **Validación**: El filtro `JwtAuthenticationFilter` intercepta cada petición, valida el token y establece la autenticación en el SecurityContext

## Base de Datos H2

Acceder a la consola H2: http://localhost:8080/h2-console

- JDBC URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: (vacío)

## Ejecución

```bash
./mvnw spring-boot:run
```

O desde Windows:
```bash
mvnw.cmd spring-boot:run
```
