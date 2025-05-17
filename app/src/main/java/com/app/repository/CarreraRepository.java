package com.app.repository;

import com.app.domain.Carrera;
import com.app.service.dto.carrera.responseCarreraConInscriptosDTO;
import com.app.service.dto.carrera.responseCarreraReporteDTO;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CarreraRepository extends JpaRepository<Carrera, Long> {
  @Query(
    "SELECT c " +
    "FROM Carrera c " +
    "WHERE (:nombre IS NULL OR c.nombre LIKE :nombre) "
  )
  List<Carrera> search(String nombre);

  @Query(
    "SELECT new com.app.service.dto.carrera.responseCarreraConInscriptosDTO(" +
    "c.nombre, c.duracion, COUNT(i)) " +
    "FROM Carrera c " +
    "JOIN c.inscripcion i " +
    "GROUP BY c.id_carrera,c.nombre,c.duracion " +
    "ORDER BY COUNT(i) DESC"
  )
  List<responseCarreraConInscriptosDTO> findCarrerasConCantidadInscriptos();

  @Query(
    "SELECT new com.app.service.dto.carrera.responseCarreraReporteDTO(c.nombre, i.inscripcion, COUNT(i),SUM(CASE WHEN i.graduacion > 0 THEN 1 ELSE 0 END) ) " +
    "FROM Carrera c " +
    "JOIN c.inscripcion i " + //// Asume que la entidad Carrera tiene una colección "inscriptos" de tipo Inscriptos
    "GROUP BY " +
    "c.nombre, i.inscripcion " +
    "ORDER BY c.nombre ASC, i.inscripcion ASC"
  )
  List<responseCarreraReporteDTO> reporteCarreras();
}
