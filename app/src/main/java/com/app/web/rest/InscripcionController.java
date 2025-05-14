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

@RestController
@RequestMapping("/api/inscripcion")
@RequiredArgsConstructor
public class InscripcionController {

  private final InscripcionService inscripcionService;

  @GetMapping("")
  public ArrayList<responseInscripcionDTO> findAll() {
    return inscripcionService.findAll();
  }

  @PostMapping("/alta")
  public ResponseEntity<responseInscripcionDTO> altaEstudiante(
    @RequestBody @Valid requestInscripcionDTO request
  ) {
    responseInscripcionDTO nuevoEstudiante = inscripcionService.inscribirEstudianteCarrera(
      request
    );

    // final var result = this.clienteService.save( request );
    return ResponseEntity.accepted().body(nuevoEstudiante);
  }
}
