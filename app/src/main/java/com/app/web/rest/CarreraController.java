package com.app.web.rest;

import com.app.service.CarreraService;
import com.app.service.dto.carrera.responseCarreraConInscriptosDTO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/carrera")
@RequiredArgsConstructor
public class CarreraController {

  private final CarreraService carreraService;

  @GetMapping("/con-inscriptos")
  public ResponseEntity<List<responseCarreraConInscriptosDTO>> listarCarrerasConInscriptos() {
    List<responseCarreraConInscriptosDTO> resultados = carreraService.obtenerCarrerasConInscriptos();
    return ResponseEntity.ok(resultados);
  }
}
