package com.app.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.domain.Estudiante;
import com.app.repository.EstudianteRepository;
import com.app.service.dto.estudiante.requestEstudianteDTO;
import com.app.service.dto.estudiante.responseEstudianteDTO;

import lombok.RequiredArgsConstructor;

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

  public responseEstudianteDTO crearEstudiante(requestEstudianteDTO request) {
    Estudiante estudiante = modelMapper.map(request, Estudiante.class);
    Estudiante nuevoEstudiante = estudianteRepository.save(estudiante);
    responseEstudianteDTO response = modelMapper.map(
      nuevoEstudiante,
      responseEstudianteDTO.class
    );
    return response;
  }

  public ArrayList<responseEstudianteDTO> findAll() {
    ArrayList<responseEstudianteDTO> responseEstudianteDTO = new ArrayList<>();
    List<Estudiante> estudiantes = estudianteRepository.findAllOrderByName();

    for (Estudiante estudiante : estudiantes) {
      responseEstudianteDTO response = modelMapper.map(
        estudiante,
        responseEstudianteDTO.class
      );
      responseEstudianteDTO.add(response);
    }
    return responseEstudianteDTO;
  }

  public responseEstudianteDTO findByLu(int lu) {
    Estudiante estudiante = estudianteRepository.findByLu(lu);
    responseEstudianteDTO response = modelMapper.map(
      estudiante,
      responseEstudianteDTO.class
    );
    return response;
  }

  public List<responseEstudianteDTO> findByGender(String genero) {
    ArrayList<responseEstudianteDTO> responseEstudianteDTO = new ArrayList<>();
    List<Estudiante> estudiantes = estudianteRepository.findByGender(genero);

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
