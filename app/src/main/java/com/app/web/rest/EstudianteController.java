package com.app.web.rest;

import com.app.service.EstudianteService;
import com.app.service.dto.estudiante.requestEstudianteDTO;
import com.app.service.dto.estudiante.responseEstudianteCarreraDTO;
import com.app.service.dto.estudiante.responseEstudianteDTO;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @class EstudianteController
 * @author Dardo Camaño
 * @date 16/05/2025
 * @description Controlador REST para manejar las operaciones relacionadas con los estudiantes.
 */
@RestController
@RequestMapping("/api/estudiante")
@RequiredArgsConstructor
public class EstudianteController {

  private final EstudianteService estudianteService;

  /**
   * Método para obtener todos los estudiantes.
   *
   * @return ResponseEntity con la lista de estudiantes y el estado HTTP correspondiente.
   */
  @GetMapping("")
  public ResponseEntity<List<responseEstudianteDTO>> findAll() {
    try {
      return ResponseEntity
        .status(HttpStatus.OK)
        .body(estudianteService.findAll());
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(null);
    }
  }

  /**
   * Método para obtener un estudiante por su número de LU.
   *
   * @param lu Número de LU del estudiante.
   * @return ResponseEntity con el estudiante encontrado y el estado HTTP correspondiente.
   */
  @GetMapping("/findByLu/{lu}")
  public ResponseEntity<responseEstudianteDTO> findById(@PathVariable int lu) {
    try {
      return ResponseEntity
        .status(HttpStatus.OK)
        .body(estudianteService.findByLu(lu));
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(null);
    }
  }

  /**
   * Método para obtener estudiantes por su género.
   *
   * @param genero Género de los estudiantes a buscar.
   * @return ResponseEntity con la lista de estudiantes encontrados y el estado HTTP correspondiente.
   */
  @GetMapping("/findByGender/{genero}")
  public ResponseEntity<List<responseEstudianteDTO>> findByGender(
    @PathVariable String genero
  ) {
    try {
      return ResponseEntity
        .status(HttpStatus.OK)
        .body(estudianteService.findByGender(genero));
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(null);
    }
  }

  /**
   * Método para obtener estudiantes por su carrera y ciudad.
   *
   * @param carrera Carrera de los estudiantes a buscar.
   * @param ciudad Ciudad de los estudiantes a buscar.
   * @return ResponseEntity con la lista de estudiantes encontrados y el estado HTTP correspondiente.
   */
  @GetMapping("/findByCarreraPorCiudad/{carrera}/{ciudad}")
  public ResponseEntity<List<responseEstudianteCarreraDTO>> findByCarreraPorCiudad(
    @PathVariable String carrera,
    @PathVariable String ciudad
  ) {
    try {
      return ResponseEntity
        .status(HttpStatus.OK)
        .body(estudianteService.findByCarreraPorCiudad(carrera, ciudad));
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(null);
    }
  }

  /**
   * Método para obtener estudiantes por su carrera.
   *
   * @param carrera Carrera de los estudiantes a buscar.
   * @return ResponseEntity con la lista de estudiantes encontrados y el estado HTTP correspondiente.
   */
  @PostMapping("/alta")
  public ResponseEntity<responseEstudianteDTO> altaEstudiante(
    @RequestBody @Valid requestEstudianteDTO request
  ) {
    try {
      return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(estudianteService.crearEstudiante(request));
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(null);
    }
  }
}
