# Reto A — Backend Bancario (Intereses y Puntos de Cliente)

## 🎯 Objetivo
Refactorizar un servicio con lógica de negocio bancaria altamente acoplada.  
Actualmente presenta:
- Métodos largos.
- Uso de `switch` por tipo.
- Números mágicos.
- Parámetros mutables.
- Duplicaciones.
- Responsabilidades mezcladas.

El reto consiste en aplicar buenas prácticas de **Clean Code** y **Principios SOLID** para mejorar la mantenibilidad y extensibilidad del código.

---

## ✅ Criterios de “Hecho”

- **Cohesión por rol**: separar en clases según responsabilidad (estrategia de tasa, cálculo de interés, cálculo de puntos).
- **Sin malas prácticas**:
  - No números mágicos.
  - No estado global.
  - No mutación de parámetros.
- **Cobertura de tests**:
  - Tipos de cliente: `VIP`, `Premium`, `Standard`.
  - Casos frontera en `score` de cliente.
  - Variación de `meses`.
  - Escenarios con `earlyPayment`.
- **Clean Code**:
  - Nombres autoexplicativos.
  - Métodos cortos.
  - Respeto del Principio de Responsabilidad Única (SRP).

---

## 📝 Tareas sugeridas (Java)

1. **Separar responsabilidades**  
   - Cálculo de tasa.  
   - Cálculo de interés.  
   - Cálculo de puntos.  

2. **Eliminar mutación de parámetros y estado global**  
   - Adoptar programación inmutable.  

3. **Reemplazar `switch/if` por polimorfismo**  
   - Estrategia según tipo de cliente.  

4. **Quitar números mágicos**  
   - Sustituir por constantes o configuración externa.  

5. **Unificar reglas duplicadas**  
   - Aplicar principio **DRY**.  

6. **Añadir tests unitarios (JUnit5)**  
   - Documentar y validar las reglas de negocio.  

---

## 🛠️ Tecnologías sugeridas
- **Java 17+**
- **JUnit 5** para pruebas unitarias
- **Mockito** (opcional, para mocks)
- **Maven/Gradle** como gestor de dependencias

---

## 🚀 Entregables
- Código refactorizado con separación clara de responsabilidades.
- Carpeta de tests con cobertura de casos principales.
- Documentación mínima en el código (autoexplicativo).
