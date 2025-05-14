package com.app.service.dto.carrera;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class responseCarreraDTO {

  private Long id_carrera;
  private String nombre;
  private int duracion;
}
