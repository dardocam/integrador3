package com.app.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data
@Table(name = "Inscripcion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Inscripcion {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id_inscripcion;

  @Column(name = "antiguedad", nullable = true)
  private int antiguedad;

  @Column(name = "graduacion", nullable = true)
  private int graduacion;

  @Column(name = "inscripcion", nullable = true)
  private int inscripcion;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_estudiante", nullable = false)
  private Estudiante estudiante;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_carrera", nullable = false)
  private Carrera carrera;
}
