package com.app.repository;

import com.app.domain.Carrera;
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
}
