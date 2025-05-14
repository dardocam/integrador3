package com.app.repository;

import com.app.domain.Carrera;
import com.app.service.dto.carrera.responseCarreraConInscriptosDTO;
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
}
