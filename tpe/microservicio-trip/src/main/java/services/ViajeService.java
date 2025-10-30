package services;


import DTO.ViajeDTO;
import entities.Viaje;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import repository.ViajeRepository;
import services.exception.ViajeNotFoundException;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class ViajeService {


    private final ViajeRepository viajeRepository;
    public ViajeService(ViajeRepository viajeRepository) {
        this.viajeRepository = viajeRepository;
    }

    public List<ViajeDTO> findAll() {
        List<Viaje> viajes= viajeRepository.findAll();
        if(viajes.isEmpty()){
            throw new ViajeNotFoundException("no se encontro viajes en la base de datos");
        }
        return viajes.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public ViajeDTO findById(int id) {
        Viaje  viaje = viajeRepository.findById(id)
                .orElseThrow(()-> new ViajeNotFoundException("no se encontro viaje con el id:" + id));

        return toDTO(viaje);
    }
    public ViajeDTO save(ViajeDTO v){
        Viaje viaje = toEntity(v);
        Viaje saved = viajeRepository.save(viaje);
        return toDTO(saved);

    }

    public void deleteById(Integer id) {
        if(!viajeRepository.existsById(id)){
            throw new ViajeNotFoundException("no se puede elimianr el viaje con el id:" + id + " por que no existe");

        }
        viajeRepository.deleteById(id);
    }
    private ViajeDTO toDTO(Viaje v) {
        return new ViajeDTO(
                v.getId(),
                v.getIdParadaInicio(),
                v.getIdParadaFin(),
                v.getKilometros(),
                v.getTiempo(),
                v.getTarifa(),
                v.getIdMonopatin(),
                v.getIdUsuario()
        );
    }

    private Viaje toEntity(ViajeDTO dto) {
        return new Viaje(
                dto.getId(),
                dto.getIdParadaInicio(),
                dto.getIdParadaFin(),
                dto.getKilometros(),
                dto.getTiempo(),
                dto.getTarifa(),
                dto.getIdMonopatin(),
                dto.getIdUsuario()
        );
    }
}
