package com.app.service.dto.inscripcion;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class responseInscripcionDTO {

  private Long id_inscripcion;
  private int antiguedad;
  private int graduacion;
  private int inscripcion;
  private Long id_estudiante;
  private Long id_carrera;
}
