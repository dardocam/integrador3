package com.app.repository;

import com.app.domain.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
  // @Query(
  //   "SELECT e " +
  //   "FROM Estudiante e " +
  //   "WHERE (:nombre IS NULL OR c.nombre LIKE :nombre) "
  // )
  // ArrayList<Estudiante> search(String nombre);
}
