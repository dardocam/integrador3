package com.app.service.dto.carrera;

import lombok.Data;

@Data
public class responseCarreraReporteDTO {

  private final String nombreCarrera;
  private final int anioInscripcion;
  private final Long totalInscriptos;
  private final Long totalEgresados;

  public responseCarreraReporteDTO(
    String nombreCarrera,
    int anioInscripcion,
    Long totalInscriptos,
    Long totalEgresados
  ) {
    this.nombreCarrera = nombreCarrera;
    this.anioInscripcion = anioInscripcion;
    this.totalInscriptos = totalInscriptos;
    this.totalEgresados = totalEgresados;
  }
}
