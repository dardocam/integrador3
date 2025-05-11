package com.app.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data
@Table(name = "Estudiante")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Estudiante {

  @Id
  @Column(name = "DNI", nullable = false)
  private Long id_estudiante;

  @Column(name = "nombre", nullable = false)
  private String nombre;

  @Column(name = "apellido", nullable = false)
  private String apellido;

  @Column(name = "edad", nullable = false)
  private int edad;

  @Column(name = "genero", nullable = false)
  private String genero;

  @Column(name = "ciudad", nullable = true)
  private String ciudad;

  @Column(name = "LU", nullable = true)
  private int libreta;

  @OneToMany(
    mappedBy = "estudiante",
    cascade = CascadeType.ALL,
    orphanRemoval = true,
    fetch = FetchType.LAZY
  )
  private List<Inscripcion> inscripcion = new ArrayList<>();
}
