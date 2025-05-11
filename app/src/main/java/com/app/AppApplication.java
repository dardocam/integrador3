package com.app;

import com.app.utils.CargaDeDatos;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppApplication {

  private static final String CARRERAS_CSV =
    "app/src/main/resources/static/populateDB/carreras.csv";
  private static final String ESTUDIANTES_CSV =
    "app/src/main/resources/static/populateDB/estudiantes.csv";
  private static final String INSCRIPCIONES_CSV =
    "app/src/main/resources/static/populateDB/estudianteCarrera.csv";

  @Autowired
  CargaDeDatos cargaDeDatos;

  public static void main(String[] args) {
    SpringApplication.run(AppApplication.class, args);
  }

  @PostConstruct
  public void init() throws IOException {
    cargaDeDatos.cargarCarrerasDesdeCSV(CARRERAS_CSV);
    cargaDeDatos.cargarEstudiantesDesdeCSV(ESTUDIANTES_CSV);
    cargaDeDatos.cargarInscripcionesDesdeCSV(INSCRIPCIONES_CSV);
    System.out.println("Datos cargados desde CSV");
  }
}
