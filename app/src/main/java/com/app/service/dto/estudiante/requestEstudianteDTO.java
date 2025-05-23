package com.app.service.dto.estudiante;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class requestEstudianteDTO {

  // @NotEmpty(message = "El DNI es un campo obligatorio.")
  private Long id_estudiante;

  // @NotEmpty(message = "El DNI es un campo obligatorio.")
  private int dni;

  @NotBlank(message = "El nombre es obligatorio")
  @NotEmpty(message = "El nombre es un campo obligatorio.")
  private String nombre;

  @NotBlank(message = "El apellido es obligatorio")
  @NotEmpty(message = "El apellido es un campo obligatorio.")
  private String apellido;

  // @NotEmpty(message = "La edad es un campo obligatorio.")
  private int edad;

  @NotBlank(message = "El género es obligatorio")
  @NotEmpty(message = "El género es un campo obligatorio.")
  private String genero;

  @NotBlank(message = "La ciudad es obligatoria")
  @NotEmpty(message = "La ciudad es un campo obligatorio.")
  private String ciudad;

  // @NotEmpty(message = "El número de libreta es un campo obligatorio.")
  private int libreta;
  // Si la entidad tiene más campos, agregarlos aquí
  // private LocalDate fechaNacimiento;
  // private String direccion;
}
