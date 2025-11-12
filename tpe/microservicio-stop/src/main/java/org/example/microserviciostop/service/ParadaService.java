package org.example.microserviciostop.service;

import org.example.microserviciostop.DTO.ParadaDTO;
import org.example.microserviciostop.DTO.ParadaResponseDTO;
import org.example.microserviciostop.entity.Parada;
import org.example.microserviciostop.repository.ParadaRepository;
import jakarta.transaction.Transactional;
import org.example.microserviciostop.service.exception.ParadaNotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ParadaService {

    private final ParadaRepository paradaRepository;

    public ParadaService(ParadaRepository paradaRepository) {
        this.paradaRepository = paradaRepository;
    }

    public List<ParadaDTO> findAll() {
        List<Parada> paradas= paradaRepository.findAll();
        if(paradas.isEmpty()){
            throw new ParadaNotFoundException("no se encontraron paradas en la base de datos");
        }
        return paradas.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public ParadaDTO findById(long id) {
        Parada parada = paradaRepository.findById(id)
                .orElseThrow(()-> new ParadaNotFoundException("no se encontraron paradas con el id:" + id));

        return toDTO(parada);
    }
    public ParadaDTO save(ParadaDTO p){
        Parada parada = toEntity(p);
        Parada saved = paradaRepository.save(parada);
        return toDTO(saved);

    }

    public List<ParadaResponseDTO> getParadasCercanas(float x, float y){
        Pageable pageable = PageRequest.of(0, 2);
        List<Parada> paradas = paradaRepository.getParadasCercanas(x, y, pageable);
        List<ParadaResponseDTO> paradaResponseDTOS = new ArrayList<>();
        for(Parada p : paradas){
            paradaResponseDTOS.add(this.toResponseDTO(p));
        }
        return paradaResponseDTOS;
    }


    public void deleteById(long id) {
        if(!paradaRepository.existsById(id)){
            throw new ParadaNotFoundException("no se puede eliminar el viaje con el id:" + id + " por que no existe");

        }
        paradaRepository.deleteById(id);
    }
    private ParadaDTO toDTO(Parada p) {
        return new ParadaDTO(
                p.getId(),
                p.getDireccion(),
                p.getNombre(),
                p.getX(),
                p.getY()
        );
    }
    private ParadaResponseDTO toResponseDTO(Parada p) {
        return new ParadaResponseDTO(p.getId(),p.getX(),p.getY());
    }

    private Parada toEntity(ParadaDTO dto) {
        return new Parada(
                dto.getId(),
                dto.getDireccion(),
                dto.getNombre(),
                dto.getX(),
                dto.getY()
        );
    }
}