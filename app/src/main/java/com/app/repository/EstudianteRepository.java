package com.app.repository;

import com.app.domain.Estudiante;
import com.app.service.dto.estudiante.responseEstudianteCarreraDTO;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
  @Query("SELECT e " + "FROM Estudiante e " + "ORDER BY e.nombre ASC")
  List<Estudiante> findAllOrderByName();

  @Query("SELECT e " + "FROM Estudiante e " + "WHERE e.libreta = ?1")
  Estudiante findByLu(int lu);

  @Query("SELECT e " + "FROM Estudiante e " + "WHERE e.genero = ?1")
  List<Estudiante> findByGender(String genero);

  @Query(
    "SELECT new com.app.service.dto.estudiante.responseEstudianteCarreraDTO(e.nombre,c.nombre,e.ciudad) " +
    "FROM Estudiante e " +
    "JOIN e.inscripcion i " + //// Asume que la entidad Estudiante tiene una colección "inscriptos" de tipo Inscriptos
    "JOIN i.carrera c " + //// Asume que la entidad Inscripcion tiene una referencia a Carrera
    "WHERE " +
    "c.nombre = :nombreCarrera " +
    "AND e.ciudad = :ciudadResidencia"
  )
  List<responseEstudianteCarreraDTO> findByCarreraPorCiudad(
    String nombreCarrera,
    String ciudadResidencia
  );
}
