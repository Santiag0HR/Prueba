# Gestión de Productos con Spring Boot y Oracle

Esta aplicación proporciona una API REST para gestionar productos en una base de datos Oracle mediante un procedimiento almacenado (`sp_insertAndListProducts`). Permite insertar nuevos productos y obtener una lista actualizada de todos los productos registrados, con manejo de errores como claves primarias duplicadas.

## Requisitos
- **Java**: 17 o superior
- **Spring Boot**: 3.x
- **Oracle Database**: Con la tabla `productos` y el procedimiento `sp_insertAndListProducts`
- **Maven**: Para construir y ejecutar el proyecto
- **Dependencias**:
    - `spring-boot-starter-data-jpa`
    - `ojdbc11` (driver de Oracle)
    - `spring-boot-starter-test` (para pruebas con JUnit 5 y Mockito)

## Configuración
1. Configura la conexión a la base de datos en `application.properties`:
   ```properties
   spring.datasource.url=jdbc:oracle:thin:@localhost:1522:free
   spring.datasource.username=[usuario]
   spring.datasource.password=[contraseña]
   spring.jpa.show-sql=true
   spring.jpa.hibernate.ddl-auto=none

### Explicación
- **Estructura**: Todo está contenido en un solo archivo `README.md`, desde la introducción hasta las pruebas unitarias.
- **Casos de Uso**: Se documentan los dos principales con ejemplos claros de entrada y salida en JSON.
- **Pruebas Unitarias**: El código completo se incluye con descripciones detalladas de cada caso, mostrando cómo se prueban los escenarios de éxito y error.
- **Instrucciones**: Se proporcionan pasos para configurar y ejecutar tanto la aplicación como las pruebas.

### Personalización
- **Paquetes**: Ajusta `com.example.prueba` si tu proyecto usa un paquete diferente.
- **Esquema**: Cambia `TU_SCHEMA` por el esquema real de tu base de datos.
- **Rutas**: Verifica que `/api/productos` coincida con tu controlador.

Guarda este contenido en `README.md` en la raíz de tu proyecto. ¿Necesitas agregar más detalles o casos de uso adicionales? Por ejemplo, podrías incluir un caso para parámetros inválidos si lo deseas.

## Casos de Uso

| Caso de Uso                | Descripción                                                                 | Endpoint          | Entrada (JSON)                                                                      | Salida (JSON)                                                                                                                                                                                                                                          | Código HTTP |
|----------------------------|-----------------------------------------------------------------------------|-------------------|-------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-------------|
| Inserción Exitosa          | Inserta un nuevo producto y devuelve la lista actualizada de productos.     | `POST /api/productos` | ```json {"id": 2, "nombre": "ProductoNuevo", "fechaRegistro": "18/03/2025"} ```     | ```json {"productos": [{"idProducto": 2, "nombre": "ProductoNuevo", "fechaRegistro": "20250318"}, {"idProducto": 1, "nombre": "ProductoExistente", "fechaRegistro": "20250318"}], "codigoRespuesta": 0, "mensajeRespuesta": "Ejecución con éxito"} ``` | 200 OK      |
| Error por ID Duplicado     | Intenta insertar un producto con un ID existente, manejando el error SQL.   | `POST /api/productos` | ```json {"id": 1, "nombre": "ProductoDuplicado", "fechaRegistro": "18/03/2025"} ``` | ```json {"productos": [], "codigoRespuesta": 1, "mensajeRespuesta": "Error: -1 - ORA-00001: unique constraint (TU_SCHEMA.PRODUCTOS_PK) violated"} ```                                                                                                  | 200 OK      |

