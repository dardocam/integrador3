package com.app.service.dto.carrera;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class responseCarreraConInscriptosDTO {

  private String nombre;
  private int duracion;
  private Long cantidadInscriptos;

  public responseCarreraConInscriptosDTO(
    String nombre,
    int duracion,
    Long cantidadInscriptos
  ) {
    this.nombre = nombre;
    this.duracion = duracion;
    this.cantidadInscriptos = cantidadInscriptos;
  }
}
