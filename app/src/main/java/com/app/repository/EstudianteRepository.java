package com.app.repository;

import com.app.domain.Estudiante;
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
}
