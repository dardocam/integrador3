package com.app.service.dto.estudiante;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class responseEstudianteCarreraDTO {

  private final String nombre;
  private final String carrera;
  private final String ciudad;
}
