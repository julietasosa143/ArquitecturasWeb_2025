package org.example.integrador3.service;

import org.example.integrador3.dto.request.CarreraRequestDTO;
import org.example.integrador3.dto.response.CarreraInscriptosResponseDTO;
import org.example.integrador3.dto.response.CarreraResponseDTO;
import org.example.integrador3.model.Carrera;
import org.example.integrador3.repository.CarreraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarreraService {
    private final CarreraRepository carreraRepository;

    @Autowired
    public CarreraService(CarreraRepository carreraRepository) {
        this.carreraRepository = carreraRepository;
    }

    public List<CarreraInscriptosResponseDTO> getCarrerasConInscriptosOrdenadas() {
        return carreraRepository.findCarreraConInscriptosOrdenadas();
    }

    public CarreraResponseDTO create(CarreraRequestDTO carreraRequestDTO) {
        Carrera carrera = new Carrera( carreraRequestDTO.getNombreCarrera(),carreraRequestDTO.getDuracionCarrera());
        Carrera carreraAgregado = this.carreraRepository.save(carrera);
        return new CarreraResponseDTO(carreraAgregado.getNombreCarrera(),carreraRequestDTO.getDuracionCarrera());
    }
}
