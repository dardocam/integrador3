package com.app.service;

import com.app.domain.Carrera;
import com.app.domain.Estudiante;
import com.app.domain.Inscripcion;
import com.app.repository.CarreraRepository;
import com.app.repository.EstudianteRepository;
import com.app.repository.InscripcionRepository;
import com.app.service.dto.inscripcion.requestInscripcionDTO;
import com.app.service.dto.inscripcion.responseInscripcionDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InscripcionService {

  @Autowired
  private final InscripcionRepository inscripcionRepository;

  @Autowired
  private final CarreraRepository carreraRepository;

  @Autowired
  private final EstudianteRepository estudianteRepository;

  @Autowired
  private final ModelMapper modelMapper;

  public responseInscripcionDTO inscribirEstudianteCarrera(
    requestInscripcionDTO request
  ) {
    Inscripcion inscripcion = modelMapper.map(request, Inscripcion.class);
    Optional<Carrera> carrera = carreraRepository.findById(
      request.getId_carrera()
    );
    Optional<Estudiante> estudiante = estudianteRepository.findById(
      request.getId_estudiante()
    );
    inscripcion.setCarrera(carrera.get());
    inscripcion.setEstudiante(estudiante.get());

    inscripcionRepository.save(inscripcion);

    responseInscripcionDTO response = modelMapper.map(
      inscripcion,
      responseInscripcionDTO.class
    );
    return response;
    //Comentario: El diseño de la tabla de inscripcion admite repetidos deberia haberse creado una clave primaria compuesta con el id de estudiante y el id de carrera
    // y no un id de inscripcion autoincremental
  }

  public ArrayList<responseInscripcionDTO> findAll() {
    ArrayList<responseInscripcionDTO> responseInscripcionDTO = new ArrayList<>();
    List<Inscripcion> estudiantes = inscripcionRepository.findAll();

    for (Inscripcion estudiante : estudiantes) {
      responseInscripcionDTO response = new responseInscripcionDTO();
      response.setId_inscripcion(estudiante.getId_inscripcion());
      response.setAntiguedad(estudiante.getAntiguedad());
      response.setGraduacion(estudiante.getGraduacion());
      response.setInscripcion(estudiante.getInscripcion());
      response.setId_estudiante(estudiante.getEstudiante().getId_estudiante());
      response.setId_carrera(estudiante.getCarrera().getId_carrera());
      responseInscripcionDTO.add(response);
    }
    return responseInscripcionDTO;
  }
}
