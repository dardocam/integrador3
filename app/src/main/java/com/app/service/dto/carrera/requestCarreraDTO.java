package com.app.service.dto.carrera;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class requestCarreraDTO {

  private Long id_carrera;
  private String nombre;
  private int duracion;
}
