# Reto B — Front + Persistencia (Listado de Clientes)

## Objetivo
Refactorizar un listado de clientes que actualmente presenta malas prácticas:
- Lógica de negocio en la vista.
- Uso excesivo de `any` en consultas y eventos.
- Llamadas HTTP directas en el componente.
- Consultas SQL vulnerables por concatenación de strings.

El reto consiste en aplicar buenas prácticas de **Angular** y **Backend con SQL** para obtener un sistema seguro, mantenible y escalable.

---

## Tareas sugeridas (Angular)

1. **Modelos tipados**
   - Crear `Customer` y `Account` como modelos tipados.

2. **Separar responsabilidades**
   - Mover las llamadas HTTP a un `CustomerService` (`@Injectable`).

3. **Evitar `any`**
   - Tipar correctamente eventos, propiedades y valores.

4. **Separar presentación vs. lógica**
   - Usar **pipes** o **adapters** para formateo/normalización.

5. **Manejo de estado**
   - Implementar con **RxJS** y `async pipe`.

6. **Mejorar UX**
   - Añadir paginación y filtros reactivos (`FormControls`).
   - Evitar side-effects en el `constructor`.

7. **Testing**
   - Tests unitarios del componente y del servicio.

---

## Tareas sugeridas (Backend + SQL)

1. **Seguridad**
   - Parametrizar consultas (eliminar concatenación de strings).

2. **DTOs tipados**
   - Devolver objetos claros en vez de `Map`/`dynamic`.

3. **Separar responsabilidades**
   - Evitar mezclar mapeo/consulta en repositorios.
   - Usar `RowMapper` o `Mapper` dedicado.

4. **Normalización de estado**
   - Convertir `A/I` ↔ `ACTIVE/INACTIVE` en la capa de dominio o adaptador.
   - No hacerlo en el componente Angular.

5. **Optimización**
   - Añadir paginación y ordenamiento determinístico en las consultas.

6. **Testing**
   - Tests de repositorio con dataset de prueba.

---

## Criterios de “Hecho”

- **Angular**:
  - Componente delgado (sin lógica de negocio).
  - Servicio HTTP dedicado.
  - Modelos tipados.
  - Uso de `async pipe`.
  - Sin `any`.

- **Backend**:
  - Consultas parametrizadas.
  - DTOs claros.
  - Sin `dynamic`/`Map` crudo.

- **Normalización de estados**
  - Implementada en un **adapter** de dominio, no en la vista.

- **Tests**
  - Al menos un test de UI (Angular).
  - Al menos un test de repositorio (Backend).

---

## Tecnologías sugeridas
- **Frontend**: Angular 18+, RxJS, Jasmine/Karma o Jest.  
- **Backend**: Java 17+, Spring Boot/JDBC/JPA, JUnit 5.  
- **Base de datos**: PostgreSQL / H2 (para pruebas).
