# 🎉 ¡Bienvenido al Proyecto Spring Security con JWT!

## ✅ Implementación Completada

Tu proyecto de Spring Security con autenticación JWT ha sido implementado exitosamente.

---

## 🚀 ¿Por Dónde Empezar?

### Para Principiantes
👉 Empieza con **[GUIA_RAPIDA.md](GUIA_RAPIDA.md)** - Te llevará de 0 a funcionando en minutos.

### Para Desarrolladores
👉 Lee **[README_JWT.md](README_JWT.md)** - Documentación técnica completa del proyecto.

### Para Probar la Aplicación
👉 Abre **[EJEMPLOS_PRACTICOS.md](EJEMPLOS_PRACTICOS.md)** - Ejemplos con curl, Postman, JavaScript, Python, etc.

### Para Ver el Estado del Proyecto
👉 Consulta **[INFORME_FINAL.txt](INFORME_FINAL.txt)** - Resumen ejecutivo completo.

---

## ⚡ Inicio Ultra Rápido (30 segundos)

```bash
# 1. Ejecuta la aplicación
./mvnw spring-boot:run

# 2. En otra terminal, haz login
curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"user","password":"password"}'

# 3. Copia el token y prueba un endpoint protegido
curl http://localhost:8080/secured/hello \
  -H "Authorization: Bearer TU_TOKEN_AQUI"
```

---

## 📚 Índice de Documentación

| Archivo | Descripción | Para Quién |
|---------|-------------|------------|
| **[GUIA_RAPIDA.md](GUIA_RAPIDA.md)** | Inicio rápido en 3 pasos | 👨‍💻 Todos |
| **[README_JWT.md](README_JWT.md)** | Documentación técnica completa | 📖 Desarrolladores |
| **[EJEMPLOS_PRACTICOS.md](EJEMPLOS_PRACTICOS.md)** | Ejemplos con diferentes herramientas | 🧪 Testers |
| **[INFORME_FINAL.txt](INFORME_FINAL.txt)** | Resumen ejecutivo del proyecto | 👔 Managers |
| **[IMPLEMENTACION_COMPLETADA.md](IMPLEMENTACION_COMPLETADA.md)** | Checklist de implementación | ✅ Auditores |
| **[RESUMEN_FINAL.md](RESUMEN_FINAL.md)** | Estado y estructura del proyecto | 🏗️ Arquitectos |
| **[test-requests.http](test-requests.http)** | Pruebas para HTTP Client | 🔧 IDEs |
| **[test-endpoints.sh](test-endpoints.sh)** | Script bash de pruebas | 🐧 Terminal |

---

## 🔑 Credenciales de Acceso

| Usuario | Contraseña | Roles | Para Qué |
|---------|-----------|-------|----------|
| `user` | `password` | USER | Probar acceso normal |
| `admin` | `admin` | ADMIN, USER | Probar acceso administrativo |

---

## 🌐 Endpoints Disponibles

### Sin Autenticación ✅
- `GET /public/hello` - Endpoint público de prueba
- `POST /auth/login` - Obtener token JWT

### Con Autenticación 🔒
- `GET /secured/hello` - Requiere token válido
- `GET /admin/hello` - Requiere token + rol ADMIN

### Utilidades 🛠️
- `GET /h2-console` - Consola base de datos H2

---

## 📦 ¿Qué Incluye Este Proyecto?

✅ Autenticación JWT completa
✅ Control de acceso por roles
✅ Endpoints públicos y protegidos
✅ Usuarios pre-cargados
✅ Base de datos H2 en memoria
✅ Documentación completa
✅ Ejemplos de uso
✅ Scripts de prueba

---

## 🎯 Casos de Uso Cubiertos

### 1. Usuario Normal
```bash
# Login
curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"user","password":"password"}'

# Acceder a endpoint protegido ✅
curl http://localhost:8080/secured/hello \
  -H "Authorization: Bearer {TOKEN}"

# Intentar acceder a endpoint admin ❌
curl http://localhost:8080/admin/hello \
  -H "Authorization: Bearer {TOKEN}"
# Resultado: 403 Forbidden
```

### 2. Administrador
```bash
# Login
curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"admin"}'

# Acceder a endpoint protegido ✅
curl http://localhost:8080/secured/hello \
  -H "Authorization: Bearer {TOKEN_ADMIN}"

# Acceder a endpoint admin ✅
curl http://localhost:8080/admin/hello \
  -H "Authorization: Bearer {TOKEN_ADMIN}"
```

---

## 🛠️ Herramientas de Prueba

### Opción 1: Terminal (curl)
Los ejemplos de arriba funcionan directamente en terminal.

### Opción 2: Postman
Importa las peticiones desde `EJEMPLOS_PRACTICOS.md`

### Opción 3: IDE (IntelliJ/VS Code)
Abre el archivo `test-requests.http` y ejecuta las peticiones.

### Opción 4: Script Automatizado
```bash
chmod +x test-endpoints.sh
./test-endpoints.sh
```

---

## 📖 Arquitectura del Proyecto

```
Cliente
   │
   ├─► POST /auth/login ──────► AuthController
   │                              │
   │                              ├─► AuthService
   │                              │     └─► JwtService (genera token)
   │                              │
   │                              └─► UserService (valida credenciales)
   │
   └─► GET /secured/hello ──────► JwtAuthenticationFilter
                                    │
                                    ├─► Valida token JWT
                                    │
                                    ├─► SecurityConfig (verifica acceso)
                                    │
                                    └─► DemoController (si OK)
```

---

## ⚙️ Configuración JWT

Los tokens JWT están configurados con:
- **Algoritmo**: HMAC-SHA256
- **Expiración**: 24 horas (86400000 ms)
- **Secret**: Configurado en `application.properties`

Para modificar la expiración, edita:
```properties
jwt.expiration=3600000  # 1 hora
```

---

## 🐛 Solución de Problemas

### Error: "No se puede conectar a localhost:8080"
```bash
# Asegúrate de que la aplicación esté corriendo
./mvnw spring-boot:run
```

### Error: 403 Forbidden
```bash
# Verifica que estés enviando el token correctamente
curl http://localhost:8080/secured/hello \
  -H "Authorization: Bearer TU_TOKEN"
#                    ^^^^^^ Nota el espacio después de "Bearer"
```

### Error: 401 Unauthorized
```bash
# Tu token puede haber expirado (24h) o ser inválido
# Haz login nuevamente para obtener un token fresco
```

---

## 📞 ¿Necesitas Ayuda?

1. **Para comandos básicos**: `GUIA_RAPIDA.md`
2. **Para detalles técnicos**: `README_JWT.md`
3. **Para ejemplos**: `EJEMPLOS_PRACTICOS.md`
4. **Para el estado del proyecto**: `INFORME_FINAL.txt`

---

## ✨ Próximos Pasos Sugeridos

1. ✅ Prueba todos los endpoints
2. ✅ Revisa el código de seguridad
3. ✅ Explora la base de datos H2
4. 💡 Agrega más endpoints protegidos
5. 💡 Implementa refresh tokens
6. 💡 Añade más roles personalizados
7. 💡 Crea un frontend que consuma la API

---

## 🎓 Aprendizaje

Este proyecto te enseña:
- ✅ Autenticación JWT
- ✅ Spring Security
- ✅ Control de acceso basado en roles
- ✅ Filtros personalizados
- ✅ Arquitectura REST
- ✅ Mejores prácticas de seguridad

---

## 🎉 ¡Todo Listo!

Tu proyecto está **100% funcional** y listo para usar.

**👉 Comienza aquí: [GUIA_RAPIDA.md](GUIA_RAPIDA.md)**

---

*Última actualización: 3 de Febrero de 2026*
*Estado: ✅ Completado y Verificado*
