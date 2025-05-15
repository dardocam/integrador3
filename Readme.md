c# Portada

## **Trabajo Práctico Integrador N3:**  

Trabajo práctico integrador para la cátedra Arq Web - TUDAI-2025

---

**Autor(es):**  
Dardo Camaño

**Grupo:**  
8

**Curso/Asignatura:**  
Arquitectura Web

**Fecha:**  
14/05/2025

---

# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/3.4.5/maven-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/3.4.5/maven-plugin/build-image.html)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/3.4.5/reference/using/devtools.html)
* [Spring Web](https://docs.spring.io/spring-boot/3.4.5/reference/web/servlet.html)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

### Maven Parent overrides

Due to Maven's design, elements are inherited from the parent POM to the project POM.
While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the parent.
To prevent this, the project POM contains empty overrides for these elements.
If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides.

---

### ENTORNO DE DESARROLLO 
- IDE Visual Studio Code
- Extensiónes:
  -  *Thunder Client* para testing de servicios REST
  -  *Debugger for Java*
  -  *Java*
  -  *Java prettier formatter*
  -  *Maven for Java*
  -  *Project Manager for Java*
  -  *Spring Boot extension pack*
  -  *PlantUML* para diagramas
  
- Dos Contenedores Docker - MySQL - PHPMyAdmin
- openjdk version "21.0.7" 2025-04-15
- OpenJDK Runtime Environment (build 21.0.7+6-Ubuntu-0ubuntu124.04)
- OpenJDK 64-Bit Server VM (build 21.0.7+6-Ubuntu-0ubuntu124.04, mixed mode, sharing)

### LIBRERIAS ADICIONALES
Se utilizó para mapear @Entity con DTO

        <dependency>
            <groupId>org.modelmapper</groupId>
            <artifactId>modelmapper</artifactId>
            <version>3.1.1</version>
        </dependency>


---


## Resumen

Para exponer en tu API REST un DTO que contenga el nombre de cada carrera junto con el conteo de inscripciones, debes:

1. **Definir el DTO** con un constructor compatible con la expresión JPQL `new`.
2. **Crear el método en el repositorio** usando `@Query` con la expresión de constructor.
3. **Implementar el servicio** que invoca dicho repositorio.
4. **Exponer un controlador REST** que retorne un `ResponseDTO` (o similar) con la lista de resultados.

A continuación, encontrarás el paso a paso con ejemplos de código.

---

## 1. Definir el DTO

```java
package com.tuempresa.dto;

public class CarreraConInscriptosDTO {
    private String nombreCarrera;
    private long cantidadInscriptos;

    public CarreraConInscriptosDTO(String nombreCarrera, long cantidadInscriptos) {
        this.nombreCarrera = nombreCarrera;
        this.cantidadInscriptos = cantidadInscriptos;
    }

    // Getters y setters (o usar Lombok @Getter/@Setter)
    public String getNombreCarrera() {
        return nombreCarrera;
    }

    public long getCantidadInscriptos() {
        return cantidadInscriptos;
    }
}
```

> *La expresión `SELECT new ...` invoca este constructor directamente en la consulta*  ([JPA Always Best Trick — DTO Via Constructor And Spring ... - Prateek](https://prateek-ashtikar512.medium.com/jpa-always-best-trick-dto-via-constructor-and-spring-data-query-builder-mechanism-5ca50f591061?utm_source=chatgpt.com)).

---

## 2. Crear el método en el repositorio

```java
package com.tuempresa.repository;

import com.tuempresa.dto.CarreraConInscriptosDTO;
import com.tuempresa.entity.Carrera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarreraRepository extends JpaRepository<Carrera, Long> {

    @Query("SELECT new com.tuempresa.dto.CarreraConInscriptosDTO(" +
           "c.nombre, COUNT(i)) " +
           "FROM Carrera c " +
           "JOIN c.inscripciones i " +
           "GROUP BY c.id, c.nombre " +
           "ORDER BY COUNT(i) DESC")
    List<CarreraConInscriptosDTO> findCarrerasConCantidadInscriptos();
}
```

- **Paquetes**: asegúrate de usar la ruta completa del DTO en la query  ([10. JPQL- Constructor Expressions | The Shift from Combined Path ...](https://medium.com/%40yazilimkonseptleri/10-jpql-constructor-expressions-the-shift-from-combined-path-expressions-to-constructor-578d486885ce?utm_source=chatgpt.com)).  
- **Conteo**: JPA traduce `COUNT(i)` como `long` por defecto  ([Projections :: Spring Data JPA](https://docs.spring.io/spring-data/jpa/reference/repositories/projections.html?utm_source=chatgpt.com)).

---

## 3. Implementar el servicio

```java
package com.tuempresa.service;

import com.tuempresa.dto.CarreraConInscriptosDTO;
import com.tuempresa.repository.CarreraRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarreraService {

    private final CarreraRepository carreraRepository;

    public CarreraService(CarreraRepository carreraRepository) {
        this.carreraRepository = carreraRepository;
    }

    public List<CarreraConInscriptosDTO> obtenerCarrerasConInscriptos() {
        return carreraRepository.findCarrerasConCantidadInscriptos();
    }
}
```

> *Separa la lógica de negocio del controlador para mantener un diseño limpio*  ([The best way to fetch a Spring Data JPA DTO Projection](https://vladmihalcea.com/spring-jpa-dto-projection/?utm_source=chatgpt.com)).

---

## 4. Exponer el controlador REST

```java
package com.tuempresa.controller;

import com.tuempresa.dto.CarreraConInscriptosDTO;
import com.tuempresa.service.CarreraService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CarreraController {

    private final CarreraService carreraService;

    public CarreraController(CarreraService carreraService) {
        this.carreraService = carreraService;
    }

    @GetMapping("/api/carreras/con-inscriptos")
    public ResponseEntity<List<CarreraConInscriptosDTO>> listarCarrerasConInscriptos() {
        List<CarreraConInscriptosDTO> resultados = carreraService.obtenerCarrerasConInscriptos();
        return ResponseEntity.ok(resultados);
    }
}
```

- **URL**: Ajusta el path según convenga a tu API.  
- **ResponseEntity**: Permite controlar el código HTTP y headers si fuera necesario  ([Spring Data JPA - How to Return DTOs from Native Queries](https://thorben-janssen.com/spring-data-jpa-dto-native-queries/?utm_source=chatgpt.com)).

---

## Notas finales

- **Nombres de atributos** en la query (`JOIN c.inscripciones i`) deben coincidir con el atributo en la entidad `Carrera` (por ejemplo `Set<Inscripcion> inscripciones`).  
- Si prefieres **interfaces** en lugar de clases DTO, Spring Data JPA también las soporta como *proyecciones*  ([The best way to fetch a Spring Data JPA DTO Projection](https://vladmihalcea.com/spring-jpa-dto-projection/?utm_source=chatgpt.com)).  
- Asegúrate de que tu `application.properties` incluya la configuración de escaneo de paquetes para entidades y repositorios.

Con esto tendrás una consulta JPQL en Spring Boot que retorna tu `ResponseDTO` correctamente poblado.