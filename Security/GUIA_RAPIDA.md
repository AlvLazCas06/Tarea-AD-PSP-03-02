# 🔐 Spring Security JWT - Guía Rápida

## 🚀 Inicio Rápido

### 1. Ejecutar la aplicación
```bash
./mvnw spring-boot:run
```
O en Windows:
```bash
mvnw.cmd spring-boot:run
```

### 2. Usuarios disponibles
- **Usuario**: user / password (Rol: USER)
- **Admin**: admin / admin (Roles: ADMIN, USER)

---

## 📋 Prueba de Endpoints

### ✅ Endpoint Público (Sin autenticación)
```bash
curl http://localhost:8080/public/hello
```

### 🔑 Login
```bash
# Usuario normal
curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"user","password":"password"}'

# Administrador
curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"admin"}'
```

### 🔒 Endpoint Protegido
```bash
curl http://localhost:8080/secured/hello \
  -H "Authorization: Bearer TU_TOKEN_AQUI"
```

### 👑 Endpoint Admin
```bash
curl http://localhost:8080/admin/hello \
  -H "Authorization: Bearer TU_TOKEN_ADMIN_AQUI"
```

---

## 📊 Resumen de Endpoints

| Endpoint | Método | Autenticación | Rol | Descripción |
|----------|--------|---------------|-----|-------------|
| `/public/hello` | GET | ❌ No | - | Endpoint público |
| `/auth/login` | POST | ❌ No | - | Login y obtención de token |
| `/secured/hello` | GET | ✅ Sí | USER/ADMIN | Endpoint protegido |
| `/admin/hello` | GET | ✅ Sí | ADMIN | Endpoint solo admin |

---

## 🧪 Ejecutar Pruebas Automáticas

```bash
chmod +x test-endpoints.sh
./test-endpoints.sh
```

---

## 🗄️ Base de Datos H2

Accede a la consola H2 en: http://localhost:8080/h2-console

- **JDBC URL**: jdbc:h2:mem:testdb
- **Usuario**: sa
- **Contraseña**: (vacío)

---

## 📂 Archivos de Documentación

- `README_JWT.md` - Documentación completa
- `IMPLEMENTACION_COMPLETADA.md` - Resumen de implementación
- `test-requests.http` - Pruebas con cliente HTTP
- `test-endpoints.sh` - Script de pruebas automatizado

---

## ✨ Características Implementadas

✅ Autenticación JWT con jjwt
✅ Endpoint público sin autenticación
✅ Endpoint protegido con JWT
✅ Control de acceso por roles (USER, ADMIN)
✅ Usuarios pre-cargados
✅ Base de datos H2 en memoria
✅ Configuración de Spring Security
✅ Filtro de autenticación JWT
✅ Documentación completa

---

## 🔧 Tecnologías

- Spring Boot 4.0.2
- Spring Security
- jjwt 0.13.0
- H2 Database
- Java 21

---

## 💡 Ejemplo Completo

```bash
# 1. Login
TOKEN=$(curl -s -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"user","password":"password"}' \
  | grep -o '"token":"[^"]*' | sed 's/"token":"//')

# 2. Usar el token
curl http://localhost:8080/secured/hello \
  -H "Authorization: Bearer $TOKEN"
```

---

## ❓ Problemas Comunes

### Token inválido
- Verifica que el token esté completo
- Asegúrate de usar "Bearer " antes del token
- El token expira en 24 horas

### Acceso denegado
- Verifica que el usuario tenga el rol necesario
- El endpoint `/admin/hello` requiere rol ADMIN

### Error 403
- Verifica que estés enviando el token en el header correcto
- Formato: `Authorization: Bearer {token}`
