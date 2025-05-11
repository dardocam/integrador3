package com.app.web.rest;

import com.app.domain.Estudiante;
import com.app.service.EstudianteService;
import com.app.service.dto.estudiante.requestEstudianteDTO;
import com.app.service.dto.estudiante.responseEstudianteDTO;
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
@RequestMapping("/api/estudiantes")
@RequiredArgsConstructor
public class EstudianteController {

  private final EstudianteService estudianteService;

  @GetMapping("")
  public ArrayList<responseEstudianteDTO> findAll() {
    return estudianteService.findAll();
  }

  @PostMapping
  public ResponseEntity<?> crearEstudiante(
    @RequestBody @Valid requestEstudianteDTO request
  ) {
    Estudiante nuevoEstudiante = estudianteService.crearEstudiante(request);

    // final var result = this.clienteService.save( request );
    return ResponseEntity.accepted().body(nuevoEstudiante);
  }
}
