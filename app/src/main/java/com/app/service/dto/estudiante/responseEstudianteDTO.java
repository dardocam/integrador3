package com.app.service.dto.estudiante;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class responseEstudianteDTO {

  private Long id_estudiante;
  private String nombre;
  private String apellido;
  private int edad;
  private String genero;
  private String ciudad;
  private int libreta;

  public responseEstudianteDTO(
    Long id_estudiante,
    String nombre,
    String apellido,
    int edad,
    String genero,
    String ciudad,
    int libreta
  ) {
    this.id_estudiante = id_estudiante;
    this.nombre = nombre;
    this.apellido = apellido;
    this.edad = edad;
    this.genero = genero;
    this.ciudad = ciudad;
    this.libreta = libreta;
  }
}
