#!/bin/bash

echo "==================================="
echo "Pruebas de Spring Security con JWT"
echo "==================================="
echo ""

# 1. Test endpoint público
echo "1. Probando endpoint público (sin autenticación)..."
curl -X GET http://localhost:8080/public/hello
echo -e "\n"

# 2. Login con usuario normal
echo "2. Login con usuario normal (user/password)..."
USER_RESPONSE=$(curl -s -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"user","password":"password"}')
echo "$USER_RESPONSE"
USER_TOKEN=$(echo "$USER_RESPONSE" | grep -o '"token":"[^"]*' | sed 's/"token":"//')
echo -e "\n"

# 3. Login con administrador
echo "3. Login con administrador (admin/admin)..."
ADMIN_RESPONSE=$(curl -s -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"admin"}')
echo "$ADMIN_RESPONSE"
ADMIN_TOKEN=$(echo "$ADMIN_RESPONSE" | grep -o '"token":"[^"]*' | sed 's/"token":"//')
echo -e "\n"

# 4. Test endpoint protegido con token de usuario
echo "4. Accediendo a endpoint protegido con token de usuario..."
curl -X GET http://localhost:8080/secured/hello \
  -H "Authorization: Bearer $USER_TOKEN"
echo -e "\n"

# 5. Test endpoint admin con token de admin
echo "5. Accediendo a endpoint admin con token de administrador..."
curl -X GET http://localhost:8080/admin/hello \
  -H "Authorization: Bearer $ADMIN_TOKEN"
echo -e "\n"

# 6. Test endpoint protegido sin token (debe fallar)
echo "6. Intentando acceder a endpoint protegido sin token (debe fallar)..."
curl -X GET http://localhost:8080/secured/hello
echo -e "\n"

# 7. Test endpoint admin con token de usuario normal (debe fallar)
echo "7. Intentando acceder a endpoint admin con token de usuario normal (debe fallar)..."
curl -X GET http://localhost:8080/admin/hello \
  -H "Authorization: Bearer $USER_TOKEN"
echo -e "\n"

echo "==================================="
echo "Pruebas completadas"
echo "==================================="
