Patrones de Diseño
------------------

A continuación se describen los patrones de diseño utilizados en la aplicación, indicando dónde y por qué se aplicaron:

### 1\. Singleton

*   **Dónde**: En los componentes gestionados por Spring como ProductoService y ProductoController.

*   **Por qué**: Spring Boot utiliza el patrón Singleton por defecto para sus beans, asegurando que solo exista una instancia de cada servicio y controlador en el contenedor de Spring. Esto optimiza el uso de recursos y garantiza un estado consistente en toda la aplicación, evitando la creación innecesaria de múltiples instancias.


### 2\. Factory

*   **Dónde**: En la creación de EntityManager y StoredProcedureQuery a través de Spring Data JPA.

*   **Por qué**: Spring actúa como una fábrica que crea y configura automáticamente estas instancias abstrayendo los detalles de su inicialización (como la conexión a la base de datos).


### 3\. Repository

*   **Dónde**: En la interfaz ProductoRepository, aunque en este caso específico se usa directamente EntityManager para ejecutar el procedimiento almacenado.

*   **Por qué**: El patrón Repository proporciona una capa de abstracción entre la lógica de negocio y la persistencia de datos. Aunque aquí no se usa explícitamente para operaciones CRUD, su presencia en el diseño refleja la intención de separar la lógica de acceso a datos.


### 4\. DTO (Data Transfer Object)

*   **Dónde**: En las clases ProductoRequest, ProductoResponse y ProductoDTO (dentro de ProductoResponse).

*   **Por qué**: Se utiliza para encapsular los datos transferidos entre el cliente y el servidor (ProductoRequest para la entrada, ProductoResponse para la salida) y entre capas internas (ProductoDTO para mapear entidades a respuestas). Esto reduce el acoplamiento, controla qué datos se exponen en la API.