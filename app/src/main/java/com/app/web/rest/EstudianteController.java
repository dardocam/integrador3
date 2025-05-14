package com.app.web.rest;

import com.app.service.EstudianteService;
import com.app.service.dto.estudiante.requestEstudianteDTO;
import com.app.service.dto.estudiante.responseEstudianteDTO;
import jakarta.validation.Valid;
import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/estudiante")
@RequiredArgsConstructor
public class EstudianteController {

  private final EstudianteService estudianteService;

  @GetMapping("")
  public ArrayList<responseEstudianteDTO> findAll() {
    return estudianteService.findAll();
  }

  @GetMapping("/findByLu/{lu}")
  public responseEstudianteDTO findById(@PathVariable int lu) {
    return estudianteService.findByLu(lu);
  }

  @GetMapping("/findByGender/{genero}")
  public ArrayList<responseEstudianteDTO> findByGender(
    @PathVariable String genero
  ) {
    return estudianteService.findByGender(genero);
  }

  @PostMapping("/alta")
  public ResponseEntity<responseEstudianteDTO> altaEstudiante(
    @RequestBody @Valid requestEstudianteDTO request
  ) {
    responseEstudianteDTO nuevoEstudiante = estudianteService.crearEstudiante(
      request
    );

    // final var result = this.clienteService.save( request );
    return ResponseEntity.accepted().body(nuevoEstudiante);
  }
}
