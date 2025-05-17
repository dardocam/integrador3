package com.app.web.rest;

import com.app.service.InscripcionService;
import com.app.service.dto.inscripcion.requestInscripcionDTO;
import com.app.service.dto.inscripcion.responseInscripcionDTO;
import jakarta.validation.Valid;
import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @class InscripcionController
 * @author Dardo Camaño
 * @date 16/05/2025
 * @description Controlador REST para manejar las operaciones relacionadas con las inscripciones.
 */
@RestController
@RequestMapping("/api/inscripcion")
@RequiredArgsConstructor
public class InscripcionController {

  private final InscripcionService inscripcionService;

  /**
   * Método para obtener todas las inscripciones.
   *
   * @return ArrayList de responseInscripcionDTO con todas las inscripciones.
   */
  @GetMapping("")
  public ArrayList<responseInscripcionDTO> findAll() {
    return inscripcionService.findAll();
  }

  /**
   * Método para inscribir un estudiante en una carrera.
   *
   * @param request InscripcionDTO con los datos de la inscripción.
   * @return ResponseEntity con el estado de la inscripción y el nuevo estudiante.
   */
  @PostMapping("/alta")
  public ResponseEntity<responseInscripcionDTO> altaEstudiante(
    @RequestBody @Valid requestInscripcionDTO request
  ) {
    responseInscripcionDTO nuevoEstudiante = inscripcionService.inscribirEstudianteCarrera(
      request
    );
    return ResponseEntity.accepted().body(nuevoEstudiante);
  }
}
