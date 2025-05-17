package com.app.web.rest;

import com.app.service.CarreraService;
import com.app.service.dto.carrera.responseCarreraConInscriptosDTO;
import com.app.service.dto.carrera.responseCarreraReporteDTO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @class CarreraController
 * @author Dardo Camaño
 * @date 16/05/2025
 * @brief Controlador REST para manejar las operaciones relacionadas con las carreras.
 */
@RestController
@RequestMapping("/api/carrera")
@RequiredArgsConstructor
public class CarreraController {

  private final CarreraService carreraService;

  /**
   * @brief Endpoint para obtener una lista de carreras con la cantidad de inscriptos.
   *
   * @return ResponseEntity con la lista de carreras y su cantidad de inscriptos.
   */
  @GetMapping("/con-inscriptos")
  public ResponseEntity<List<responseCarreraConInscriptosDTO>> listarCarrerasConInscriptos() {
    try {
      List<responseCarreraConInscriptosDTO> resultados = carreraService.obtenerCarrerasConInscriptos();
      return ResponseEntity.ok(resultados);
    } catch (Exception e) {
      return ResponseEntity.status(500).build();
    }
  }

  /**
   * @brief Endpoint para obtener un reporte de carreras con la cantidad de inscriptos y egresados.
   *
   * @return ResponseEntity con la lista de carreras y su cantidad de inscriptos y egresados.
   */
  @GetMapping("/reporte")
  public ResponseEntity<List<responseCarreraReporteDTO>> reporteCarreras() {
    try {
      List<responseCarreraReporteDTO> resultados = carreraService.reporteCarreras();
      return ResponseEntity.ok(resultados);
    } catch (Exception e) {
      return ResponseEntity.status(500).build();
    }
  }
}
