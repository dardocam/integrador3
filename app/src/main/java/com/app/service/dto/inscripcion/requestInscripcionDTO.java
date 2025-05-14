package com.app.service.dto.inscripcion;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class requestInscripcionDTO {

  private Long id_inscripcion;
  private int antiguedad;
  private int graduacion;
  private int inscripcion;
  private Long id_estudiante;
  private Long id_carrera;
}
