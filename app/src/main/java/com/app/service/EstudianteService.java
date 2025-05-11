package com.app.service;

import com.app.domain.Estudiante;
import com.app.repository.EstudianteRepository;
import com.app.service.dto.estudiante.requestEstudianteDTO;
import com.app.service.dto.estudiante.responseEstudianteDTO;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EstudianteService {

  // Inyeccion de dependencias
  // Se inyecta el repositorio de estudiantes
  // para poder realizar operaciones CRUD
  // en la base de datos
  // Se utiliza el patron de diseño Singleton
  // para que solo haya una instancia de la clase
  // y no se creen varias instancias
  // Se utiliza el patron de diseño Repository
  // para separar la logica de negocio de la logica de acceso a datos
  // Se utiliza el patron de diseño Service
  // para separar la logica de negocio de la logica de presentacion
  @Autowired
  private final EstudianteRepository estudianteRepository;

  @Autowired
  private final ModelMapper modelMapper;

  public Estudiante crearEstudiante(requestEstudianteDTO request) {
    Estudiante estudiante = modelMapper.map(request, Estudiante.class);
    return estudianteRepository.save(estudiante);
  }

  public ArrayList<responseEstudianteDTO> findAll() {
    ArrayList<responseEstudianteDTO> responseEstudianteDTO = new ArrayList<>();
    List<Estudiante> estudiantes = estudianteRepository.findAll();

    for (Estudiante estudiante : estudiantes) {
      responseEstudianteDTO response = modelMapper.map(
        estudiante,
        responseEstudianteDTO.class
      );
      responseEstudianteDTO.add(response);
    }
    return responseEstudianteDTO;
  }
}
