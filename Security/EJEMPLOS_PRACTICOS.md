# 🚀 Ejemplos Prácticos - Spring Security JWT

## 📝 Ejemplos Paso a Paso

### Ejemplo 1: Probar Endpoint Público

```bash
# Este endpoint no requiere autenticación
curl -X GET http://localhost:8080/public/hello
```

**Respuesta esperada:**
```json
{
  "message": "Este es un endpoint público",
  "description": "No requiere autenticación para acceder"
}
```

---

### Ejemplo 2: Login y Obtener Token

#### Login con Usuario Normal
```bash
curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"user","password":"password"}'
```

**Respuesta esperada:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiaWF0IjoxNzA5NDc...",
  "username": "user",
  "message": "Login exitoso"
}
```

#### Login con Administrador
```bash
curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"admin"}'
```

**Respuesta esperada:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTcwOTQ3...",
  "username": "admin",
  "message": "Login exitoso"
}
```

---

### Ejemplo 3: Acceder a Endpoint Protegido

```bash
# Reemplaza {TOKEN} con el token obtenido en el login
curl -X GET http://localhost:8080/secured/hello \
  -H "Authorization: Bearer {TOKEN}"
```

**Ejemplo con token real:**
```bash
curl -X GET http://localhost:8080/secured/hello \
  -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiaWF0IjoxNzA5NDc..."
```

**Respuesta esperada:**
```json
{
  "message": "Este es un endpoint protegido",
  "description": "Requiere autenticación JWT para acceder",
  "user": "user"
}
```

---

### Ejemplo 4: Acceder a Endpoint de Admin

```bash
# Solo funciona con token de usuario con rol ADMIN
curl -X GET http://localhost:8080/admin/hello \
  -H "Authorization: Bearer {TOKEN_ADMIN}"
```

**Respuesta esperada (con token de admin):**
```json
{
  "message": "Este es un endpoint de administrador",
  "description": "Solo accesible para usuarios con rol ADMIN",
  "user": "admin"
}
```

**Si intentas con token de usuario normal:**
```json
{
  "error": "Forbidden",
  "message": "Access Denied",
  "status": 403
}
```

---

### Ejemplo 5: Flujo Completo Automatizado

```bash
#!/bin/bash

echo "=== FLUJO COMPLETO DE AUTENTICACIÓN JWT ==="

# 1. Probar endpoint público
echo -e "\n1. Probando endpoint público..."
curl -X GET http://localhost:8080/public/hello
echo -e "\n"

# 2. Login y guardar token
echo "2. Haciendo login..."
RESPONSE=$(curl -s -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"user","password":"password"}')

echo "$RESPONSE"

# Extraer token (requiere jq o grep)
TOKEN=$(echo "$RESPONSE" | grep -o '"token":"[^"]*' | sed 's/"token":"//')

echo -e "\nToken obtenido: ${TOKEN:0:50}...\n"

# 3. Usar el token para acceder a endpoint protegido
echo "3. Accediendo a endpoint protegido con token..."
curl -X GET http://localhost:8080/secured/hello \
  -H "Authorization: Bearer $TOKEN"

echo -e "\n\n=== FIN DEL FLUJO ==="
```

---

### Ejemplo 6: Casos de Error

#### Sin Token (Error 403)
```bash
curl -X GET http://localhost:8080/secured/hello
```

**Respuesta:**
```json
{
  "error": "Forbidden",
  "status": 403
}
```

#### Token Inválido (Error 403)
```bash
curl -X GET http://localhost:8080/secured/hello \
  -H "Authorization: Bearer token_invalido"
```

#### Credenciales Incorrectas (Error 401)
```bash
curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"user","password":"incorrecta"}'
```

---

### Ejemplo 7: Probar con Postman

#### 1. Login
- **Método:** POST
- **URL:** `http://localhost:8080/auth/login`
- **Headers:**
  - `Content-Type: application/json`
- **Body (raw JSON):**
```json
{
  "username": "user",
  "password": "password"
}
```

#### 2. Copiar el token de la respuesta

#### 3. Acceder a endpoint protegido
- **Método:** GET
- **URL:** `http://localhost:8080/secured/hello`
- **Headers:**
  - `Authorization: Bearer {PEGA_TU_TOKEN_AQUI}`

---

### Ejemplo 8: Probar con JavaScript (Fetch API)

```javascript
// 1. Login
async function login() {
  const response = await fetch('http://localhost:8080/auth/login', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({
      username: 'user',
      password: 'password'
    })
  });
  
  const data = await response.json();
  const token = data.token;
  
  // Guardar token
  localStorage.setItem('jwt_token', token);
  
  return token;
}

// 2. Usar el token
async function getSecuredData() {
  const token = localStorage.getItem('jwt_token');
  
  const response = await fetch('http://localhost:8080/secured/hello', {
    method: 'GET',
    headers: {
      'Authorization': `Bearer ${token}`
    }
  });
  
  const data = await response.json();
  console.log(data);
}

// Ejecutar
login().then(() => getSecuredData());
```

---

### Ejemplo 9: Probar con Python (requests)

```python
import requests

# 1. Login
def login():
    url = 'http://localhost:8080/auth/login'
    payload = {
        'username': 'user',
        'password': 'password'
    }
    response = requests.post(url, json=payload)
    data = response.json()
    return data['token']

# 2. Usar el token
def get_secured_data(token):
    url = 'http://localhost:8080/secured/hello'
    headers = {
        'Authorization': f'Bearer {token}'
    }
    response = requests.get(url, headers=headers)
    return response.json()

# Ejecutar
token = login()
print(f"Token obtenido: {token[:50]}...")

data = get_secured_data(token)
print(f"Datos: {data}")
```

---

## 🔍 Verificación de Tokens

### Decodificar Token JWT (sin verificar firma)

Puedes usar [jwt.io](https://jwt.io) para decodificar y visualizar el contenido del token.

**Estructura del Token:**
```
Header:
{
  "alg": "HS256"
}

Payload:
{
  "sub": "user",
  "iat": 1709470000,
  "exp": 1709556400
}
```

---

## ⏱️ Expiración de Tokens

Los tokens expiran en **24 horas** (86400000 ms).

Para cambiar la expiración, edita `application.properties`:
```properties
jwt.expiration=3600000  # 1 hora
jwt.expiration=86400000 # 24 horas
jwt.expiration=604800000 # 7 días
```

---

## 🛡️ Mejores Prácticas

1. **Nunca** compartas tu secret key
2. **Siempre** usa HTTPS en producción
3. **Guarda** el token de forma segura (HttpOnly cookies, localStorage)
4. **Implementa** refresh tokens para sesiones largas
5. **Valida** siempre el token en el backend

---

## 📚 Recursos Adicionales

- [Documentación jjwt](https://github.com/jwtk/jjwt)
- [Spring Security Reference](https://docs.spring.io/spring-security/reference/)
- [JWT.io](https://jwt.io)
