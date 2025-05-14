package com.app.service;

import com.app.domain.Carrera;
import com.app.repository.CarreraRepository;
import com.app.service.dto.carrera.responseCarreraConInscriptosDTO;
import com.app.service.dto.carrera.responseCarreraDTO;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarreraService {

  @Autowired
  private final CarreraRepository carreraRepository;

  @Autowired
  private final ModelMapper modelMapper;

  public ArrayList<responseCarreraDTO> findAll() {
    ArrayList<responseCarreraDTO> responseCarreraDTO = new ArrayList<>();
    List<Carrera> carreras = carreraRepository.findAll();
    for (Carrera carrera : carreras) {
      responseCarreraDTO response = modelMapper.map(
        carrera,
        responseCarreraDTO.class
      );
      responseCarreraDTO.add(response);
    }
    return responseCarreraDTO;
  }

  public List<responseCarreraConInscriptosDTO> obtenerCarrerasConInscriptos() {
    return carreraRepository.findCarrerasConCantidadInscriptos();
  }
}
