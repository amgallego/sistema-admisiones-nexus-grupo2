package com.grupo2.nexus.service;
import com.grupo2.nexus.model.dto.CursoDto;
import com.grupo2.nexus.model.entity.Curso;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.grupo2.nexus.repository.CursoRepository;

@Service
@RequiredArgsConstructor
public class CursoService {

    private final CursoRepository cursoRepository;

    public Page<CursoDto> findAll(Pageable pageable) {
        return cursoRepository.findAll(pageable)
                .map(this::toResponse);
    }

    public CursoDto findById(Long id) {
        return cursoRepository.findById(id)
                .map(x -> this.toResponse(x))
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
    }



    public Curso findByNombre(String nombre){
          return cursoRepository.findByNombre(nombre)
                  .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

    }

    public CursoDto save(Curso curso) {
        curso.setFechaCreacion(LocalDateTime.now());
        if (curso.getCuposDisponibles() == null){
            curso.setCuposDisponibles(curso.getCuposMaximos());
        }
        return toResponse(cursoRepository.save(curso));
    }

    private Curso findEntityById(Long id) {
        return cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
    }

    @Transactional
    public void reducirCupo(Long cursoId) {
        Curso curso = findEntityById(cursoId);

        if (curso.getCuposDisponibles() <= 0) {
            throw new RuntimeException("No hay cupos disponibles para el curso: " + curso.getNombre());
        }

        curso.setCuposDisponibles(curso.getCuposDisponibles() - 1);
        cursoRepository.save(curso);
    }

    public CursoDto update(Long id, Curso cursoActualizado) {
        Curso cursoExistente = findEntityById(id);
        cursoExistente.setNombre(cursoActualizado.getNombre());
        cursoExistente.setDescripcion(cursoActualizado.getDescripcion());
        cursoExistente.setCategoria(cursoActualizado.getCategoria());
        cursoExistente.setDuracionHoras(cursoActualizado.getDuracionHoras());
        cursoExistente.setCuposMaximos(cursoActualizado.getCuposMaximos());
        cursoExistente.setCuposDisponibles(cursoActualizado.getCuposDisponibles());
        cursoExistente.setPrecio(cursoActualizado.getPrecio());
        cursoExistente.setInstructor(cursoActualizado.getInstructor());
        cursoExistente.setFechaInicio(cursoActualizado.getFechaInicio());
        cursoExistente.setActivo(cursoActualizado.getActivo());
        return toResponse(cursoRepository.save(cursoExistente));
      }



    public void delete(Long id) {
        cursoRepository.deleteById(id);
    }

    private CursoDto toResponse(Curso entity) {
        return CursoDto.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .descripcion(entity.getDescripcion())
                .categoria(entity.getCategoria())
                .duracionHoras(entity.getDuracionHoras())
                .cuposMaximos(entity.getCuposMaximos())
                .cuposDisponibles(entity.getCuposDisponibles())
                .precio(entity.getPrecio())
                .instructor(entity.getInstructor())
                .mensajeDisponibilidad(entity.getCuposDisponibles() <= 0 ? "AGOTADO" : entity.getCuposDisponibles() <5 ? "últimos cupos disponibles!" : "DISPONIBLE")
                .build();
    }
}
