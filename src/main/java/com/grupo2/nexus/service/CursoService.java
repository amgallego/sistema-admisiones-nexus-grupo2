package com.grupo2.nexus.service;
import com.grupo2.nexus.model.dto.CursoDto;
import com.grupo2.nexus.model.entity.Curso;
import java.util.List;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;  
import com.grupo2.nexus.repository.CursoRepository;

@Service
@RequiredArgsConstructor
public class CursoService {

    private final CursoRepository cursoRepository;

      public List<CursoDto> findAll() {
        return cursoRepository.findAll().stream()
                .map(x -> this.toResponse(x))
                .toList();
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

    public CursoDto update(Curso cursoActualizado) {
        Curso cursoExistente = cursoRepository.findById(cursoActualizado.getId())
                .orElseThrow(() -> new RuntimeException("Curso no encontrado con ID: " + cursoActualizado.getId()));
        cursoExistente.setNombre(cursoActualizado.getNombre());
        cursoExistente.setDescripcion(cursoActualizado.getDescripcion());
        cursoExistente.setCuposDisponibles(cursoActualizado.getCuposDisponibles()); // ¡Importante!
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
