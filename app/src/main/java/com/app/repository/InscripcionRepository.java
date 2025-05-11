package com.app.repository;

import com.app.domain.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InscripcionRepository
  extends JpaRepository<Inscripcion, Long> {}
