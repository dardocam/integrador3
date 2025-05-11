package com.app.utils;

import com.app.domain.Carrera;
import com.app.domain.Estudiante;
import com.app.domain.Inscripcion;
import com.app.repository.CarreraRepository;
import com.app.repository.EstudianteRepository;
import com.app.repository.InscripcionRepository;
import jakarta.transaction.Transactional;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

@Component
public class CargaDeDatos {

  private final CarreraRepository carreraRepository;
  private final EstudianteRepository estudianteRepository;
  private final InscripcionRepository inscripcionRepository;

  // @Autowired
  public CargaDeDatos(
    CarreraRepository carreraRepository,
    EstudianteRepository estudianteRepository,
    InscripcionRepository inscripcionRepository
  ) {
    this.estudianteRepository = estudianteRepository;
    this.carreraRepository = carreraRepository;
    this.inscripcionRepository = inscripcionRepository;
  }

  @Transactional
  public void cargarCarrerasDesdeCSV(String filePath) throws IOException {
    File carreraCSV = ResourceUtils.getFile(filePath);

    try (
      FileReader reader = new FileReader(carreraCSV);
      CSVParser csvParser = CSVFormat.DEFAULT
        .withFirstRecordAsHeader()
        .parse(reader)
    ) {
      for (CSVRecord csvRecord : csvParser) {
        Carrera carrera = new Carrera();
        carrera.setNombre(csvRecord.get("carrera"));
        carrera.setDuracion(Integer.parseInt(csvRecord.get("duracion")));

        carreraRepository.save(carrera);
      }
    }
  }

  @Transactional
  public void cargarEstudiantesDesdeCSV(String filePath) throws IOException {
    File estudianteCSV = ResourceUtils.getFile(filePath);

    try (
      FileReader reader = new FileReader(estudianteCSV);
      CSVParser csvParser = CSVFormat.DEFAULT
        .withFirstRecordAsHeader()
        .parse(reader)
    ) {
      for (CSVRecord csvRecord : csvParser) {
        Estudiante estudiante = new Estudiante();
        estudiante.setId_estudiante(Long.valueOf(csvRecord.get("DNI")));
        estudiante.setNombre(csvRecord.get("nombre"));
        estudiante.setApellido(csvRecord.get("apellido"));
        estudiante.setEdad(Integer.parseInt(csvRecord.get("edad")));
        estudiante.setGenero(csvRecord.get("genero"));
        estudiante.setCiudad(csvRecord.get("ciudad"));
        estudiante.setLibreta(Integer.parseInt(csvRecord.get("LU")));

        estudianteRepository.save(estudiante);
      }
    }
  }

  @Transactional
  public void cargarInscripcionesDesdeCSV(String filePath) throws IOException {
    File inscripcionesCSV = ResourceUtils.getFile(filePath);

    try (
      FileReader reader = new FileReader(inscripcionesCSV);
      CSVParser csvParser = CSVFormat.DEFAULT
        .withFirstRecordAsHeader()
        .parse(reader)
    ) {
      for (CSVRecord csvRecord : csvParser) {
        Long idEstudiante = Long.valueOf(csvRecord.get("id_estudiante"));
        Long idCarrera = Long.valueOf(csvRecord.get("id_carrera"));

        Optional<Estudiante> estudianteOpt = estudianteRepository.findById(
          idEstudiante
        );
        Optional<Carrera> carreraOpt = carreraRepository.findById(idCarrera);

        if (estudianteOpt.isEmpty() || carreraOpt.isEmpty()) {
          // Loggear error o recolectar registros inválidos
          System.err.println(
            "Registro inválido: Estudiante=" +
            idEstudiante +
            ", Carrera=" +
            idCarrera
          );
          continue;
        }
        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setAntiguedad(
          Integer.parseInt(csvRecord.get("antiguedad"))
        );
        inscripcion.setInscripcion(
          Integer.parseInt(csvRecord.get("inscripcion"))
        );
        inscripcion.setGraduacion(
          Integer.parseInt(csvRecord.get("graduacion"))
        );
        inscripcion.setEstudiante(estudianteOpt.get());
        inscripcion.setCarrera(carreraOpt.get());
        estudianteOpt.get().getInscripcion().add(inscripcion); // Mantener sincronización de bidireccionalidad
        carreraOpt.get().getInscripcion().add(inscripcion); // Mantener sincronización de bidireccionalidad
        inscripcionRepository.save(inscripcion);
      }
    }
  }
}
