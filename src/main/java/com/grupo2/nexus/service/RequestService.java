package com.grupo2.nexus.service;
import com.grupo2.nexus.model.dto.RequestDto;
import com.grupo2.nexus.model.entity.Curso;
import com.grupo2.nexus.model.entity.Request;
import com.grupo2.nexus.repository.RequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestService {
    private final RequestRepository requestRepository;
    private final CursoService cursoService;

    public List<RequestDto> findAll(){
        return requestRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();

    }

    public RequestDto findById(Long id){
        return requestRepository.findById(id)
                .map(x -> this.toResponse(x))
                .orElseThrow(()  -> new RuntimeException("Solicitud no encotrada"));
    }

    public RequestDto save(Request entity){
        Curso curso = cursoService.findByNombre(entity.getPrimerPrograma());

       if(curso.getCuposDisponibles() <= 0){
           throw new RuntimeException("No hay cupos disponibles para: " + entity.getPrimerPrograma());
       }

       curso.setCuposDisponibles(curso.getCuposDisponibles() - 1);

       cursoService.update(curso);

        entity.setFechaCreacion(LocalDateTime.now());
        Request saved = requestRepository.save(entity);

        return this.toResponse(saved);
    }



    private RequestDto toResponse(Request entity){
        return RequestDto.builder().id(entity.getId())
                .nombreCompleto(entity.getNombres() + " " + entity.getApellidos())
                .numeroDoc(entity.getTipoDoc() + " " + entity.getNumeroDoc())
                .numeroCelular(entity.getNumeroCelular())
                .programaInteres("Primer programa: " + entity.getPrimerPrograma() + "\nSegundo Programa " + entity.getSegundoPrograma())
                .estado(entity.getEstadoSolicitud() != null ? entity.getEstadoSolicitud().name() : "PENDIENTE")
                .fechaCreacion(entity.getFechaCreacion())
                .build();
    }

}
