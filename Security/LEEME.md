# 🔐 Spring Security con JWT - Proyecto Completo

## 🎯 Implementación Completada ✅

Este proyecto implementa **Spring Security con autenticación JWT** utilizando la librería **jjwt 0.13.0**.

---

## 📚 Documentación Disponible

### 🚀 Para Empezar Rápidamente
- **[GUIA_RAPIDA.md](GUIA_RAPIDA.md)** - Inicio rápido con comandos básicos

### 📖 Documentación Detallada
- **[README_JWT.md](README_JWT.md)** - Documentación completa del proyecto
- **[IMPLEMENTACION_COMPLETADA.md](IMPLEMENTACION_COMPLETADA.md)** - Resumen de la implementación
- **[RESUMEN_FINAL.md](RESUMEN_FINAL.md)** - Estado final del proyecto

### 💡 Ejemplos y Pruebas
- **[EJEMPLOS_PRACTICOS.md](EJEMPLOS_PRACTICOS.md)** - Ejemplos paso a paso con diferentes herramientas
- **[test-requests.http](test-requests.http)** - Archivo de pruebas HTTP para IDE
- **[test-endpoints.sh](test-endpoints.sh)** - Script bash de pruebas automatizado

---

## ⚡ Inicio Rápido (3 pasos)

### 1️⃣ Ejecutar la aplicación
```bash
./mvnw spring-boot:run
```

### 2️⃣ Hacer login
```bash
curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"user","password":"password"}'
```

### 3️⃣ Usar el token
```bash
curl http://localhost:8080/secured/hello \
  -H "Authorization: Bearer {TU_TOKEN}"
```

---

## 🔑 Usuarios Pre-configurados

| Usuario | Contraseña | Roles |
|---------|-----------|-------|
| `user` | `password` | USER |
| `admin` | `admin` | ADMIN, USER |

---

## 🌐 Endpoints Disponibles

### Público (Sin autenticación)
- `GET /public/hello` - Endpoint de prueba público
- `POST /auth/login` - Login y obtención de token

### Protegido (Requiere JWT)
- `GET /secured/hello` - Endpoint protegido para usuarios autenticados
- `GET /admin/hello` - Endpoint solo para administradores

### Utilidades
- `GET /h2-console` - Consola de base de datos H2

---

## 🏗️ Estructura del Proyecto

```
src/main/java/.../security/
├── 📂 controller/      → Controladores REST
│   ├── AuthController.java
│   └── DemoController.java
│
├── 📂 dto/             → DTOs de request/response
│   ├── LoginRequest.java
│   └── AuthResponse.java
│
├── 📂 model/           → Entidades JPA
│   ├── User.java
│   ├── UserRole.java
│   ├── Product.java
│   └── Category.java
│
├── 📂 repository/      → Repositorios JPA
│   ├── UserRepository.java
│   ├── ProductRepository.java
│   └── CategoryRepository.java
│
├── 📂 security/        → Configuración de seguridad
│   ├── JwtService.java
│   ├── JwtAuthenticationFilter.java
│   └── SecurityConfig.java
│
├── 📂 service/         → Servicios de negocio
│   ├── UserService.java
│   ├── AuthService.java
│   ├── ProductService.java
│   └── CategoryService.java
│
└── 📂 init/            → Inicialización de datos
    └── DataInitializer.java
```

---

## 🛠️ Tecnologías Utilizadas

- ☕ **Java 21**
- 🍃 **Spring Boot 4.0.2**
- 🔒 **Spring Security**
- 🔑 **jjwt 0.13.0** (io.jsonwebtoken)
- 💾 **Spring Data JPA**
- 🗄️ **H2 Database**
- 🎯 **Lombok**
- 📦 **Maven**

---

## ✨ Características Implementadas

### Seguridad
- ✅ Autenticación JWT con tokens firmados
- ✅ Expiración de tokens (24 horas)
- ✅ Validación automática en cada petición
- ✅ Control de acceso basado en roles (RBAC)
- ✅ Passwords encriptados con BCrypt
- ✅ Filtro personalizado de autenticación

### Arquitectura
- ✅ Patrón Repository-Service-Controller
- ✅ DTOs para requests y responses
- ✅ Separación de responsabilidades
- ✅ Configuración centralizada
- ✅ Manejo de excepciones

### Base de Datos
- ✅ H2 en memoria para desarrollo
- ✅ JPA/Hibernate para persistencia
- ✅ Usuarios pre-cargados al inicio
- ✅ Consola H2 habilitada

---

## 🧪 Cómo Probar

### Opción 1: Usando curl (Terminal)
```bash
# Ver ejemplos en GUIA_RAPIDA.md
./mvnw spring-boot:run
```

### Opción 2: Script automatizado
```bash
chmod +x test-endpoints.sh
./test-endpoints.sh
```

### Opción 3: IDE con HTTP Client
- Abrir `test-requests.http` en IntelliJ IDEA o VS Code
- Ejecutar cada petición con el botón "Send Request"

### Opción 4: Postman
- Importar las peticiones desde `EJEMPLOS_PRACTICOS.md`

---

## 📊 Estado del Proyecto

- ✅ **Compilación:** BUILD SUCCESS
- ✅ **Dependencias:** Todas resueltas
- ✅ **Seguridad:** JWT implementado y funcional
- ✅ **Endpoints:** Públicos y protegidos operativos
- ✅ **Base de datos:** H2 configurada
- ✅ **Usuarios:** Pre-cargados automáticamente
- ✅ **Documentación:** Completa

---

## 🎓 Conceptos Cubiertos

1. **JWT (JSON Web Token)**
   - Generación de tokens
   - Validación de tokens
   - Extracción de información

2. **Spring Security**
   - Configuración de SecurityFilterChain
   - Filtros personalizados
   - AuthenticationProvider
   - UserDetailsService

3. **Control de Acceso**
   - Rutas públicas vs protegidas
   - Autorización basada en roles
   - @PreAuthorize

4. **Arquitectura REST**
   - Endpoints RESTful
   - DTOs
   - Respuestas JSON

---

## 📞 Soporte

Si tienes dudas, consulta:
1. **GUIA_RAPIDA.md** - Para comandos básicos
2. **EJEMPLOS_PRACTICOS.md** - Para ejemplos detallados
3. **README_JWT.md** - Para documentación completa

---

## 🔗 Enlaces Útiles

- [Documentación Spring Security](https://docs.spring.io/spring-security/reference/)
- [jjwt GitHub](https://github.com/jwtk/jjwt)
- [JWT.io - Decodificador de tokens](https://jwt.io)

---

## ✅ Checklist de Verificación

- [x] Proyecto compila sin errores
- [x] Aplicación arranca correctamente
- [x] Login funciona (retorna token)
- [x] Endpoint público accesible sin token
- [x] Endpoint protegido requiere token válido
- [x] Control de acceso por roles funciona
- [x] Tokens expiran correctamente
- [x] Usuarios pre-cargados en BD
- [x] Documentación completa

---

## 🎉 ¡Proyecto Listo!

Todo está configurado y funcionando. Puedes comenzar a usar la aplicación siguiendo la **GUIA_RAPIDA.md**.

**¡Disfruta de tu implementación de Spring Security con JWT!** 🚀
