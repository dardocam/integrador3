package com.app;

import com.app.utils.CargaDeDatos;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @class AppApplication
 * @author Dardo Camaño
 * @version 1.0
 * @date 16-05-2025
 * @brief
    This class is the main entry point for the Spring Boot application.
    It initializes the application and loads data from CSV files.
    The application is responsible for managing student enrollments in different careers.
 */
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

  /**
   * @brief
      This method is called after the application context is loaded.
      It loads data from CSV files into the application.
   * @throws IOException
      If there is an error reading the CSV files.
   */
  @PostConstruct
  public void init() throws IOException {
    cargaDeDatos.cargarCarrerasDesdeCSV(CARRERAS_CSV);
    cargaDeDatos.cargarEstudiantesDesdeCSV(ESTUDIANTES_CSV);
    cargaDeDatos.cargarInscripcionesDesdeCSV(INSCRIPCIONES_CSV);
    System.out.println("Datos cargados desde CSV");
  }
}
