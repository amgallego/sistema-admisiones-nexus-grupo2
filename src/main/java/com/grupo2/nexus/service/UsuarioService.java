package com.grupo2.nexus.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.grupo2.nexus.model.dto.UsuarioCreateDto;
import com.grupo2.nexus.model.dto.UsuarioDto; // IMPORTANTE: Importamos nuevo DTO
import com.grupo2.nexus.model.embeddable.DatosPersonales;
import com.grupo2.nexus.model.entity.Usuario;
import com.grupo2.nexus.model.enums.Activo;
import com.grupo2.nexus.model.enums.RolUsuario;
import com.grupo2.nexus.repository.UsuarioRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public List<UsuarioDto> findAll() {
        return usuarioRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public UsuarioDto findById(Long id){
        return usuarioRepository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    // ACTUALIZADO: recibe UsuarioCreateDto en lugar de la Entidad
    @Transactional
    public UsuarioDto save(UsuarioCreateDto dto){

        if (dto.getEmail() == null || dto.getEmail().isEmpty()){
            throw new RuntimeException("El correo electrónico es obligatorio");
        }

        if(usuarioRepository.existsByDatosPersonalesEmail(dto.getEmail())){
            throw new RuntimeException("El correo electrónico ya está registrado: " + dto.getEmail());
        }

        // Convertimos el DTO a Entidad para poder guardarlo
        Usuario entity = toEntity(dto);

        // Lógica de negocio por defecto
        if(entity.getRolUsuario() == null){
            entity.setRolUsuario(RolUsuario.ASPIRANTE);
        }
        
        if(entity.getActivo() == null) {
            entity.setActivo(Activo.ACTIVO);
        }

        Usuario saved = usuarioRepository.save(entity);
        return toResponse(saved);
    }

    @Transactional
    public UsuarioDto update(Long id, UsuarioCreateDto dto) {
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));

        // Actualizamos los datos desde el DTO
        DatosPersonales datos = usuarioExistente.getDatosPersonales();
        if (datos == null) datos = new DatosPersonales();

        datos.setNombre(dto.getNombre());
        datos.setApellido(dto.getApellido());
        datos.setTelefono(dto.getTelefono());
        datos.setEmail(dto.getEmail());
        
        usuarioExistente.setDatosPersonales(datos);
        usuarioExistente.setUsername(dto.getUsername());
        
        // Solo actualizamos password si el DTO trae uno nuevo
        if(dto.getPassword() != null && !dto.getPassword().isEmpty()){
            usuarioExistente.setPassword(dto.getPassword());
        }

        return toResponse(usuarioRepository.save(usuarioExistente));
    }

    public void delete (Long id){
        usuarioRepository.deleteById(id);
    }

    // NUEVO MÉTODO: Traduce de CreateDto a Entidad (Para Guardar)
    private Usuario toEntity(UsuarioCreateDto dto) {
        Usuario entity = new Usuario();
        entity.setUsername(dto.getUsername());
        entity.setPassword(dto.getPassword()); // Aquí viaja la contraseña hacia la DB
        
        DatosPersonales datos = new DatosPersonales();
        datos.setNombre(dto.getNombre());
        datos.setApellido(dto.getApellido());
        datos.setEmail(dto.getEmail());
        datos.setTelefono(dto.getTelefono());
        
        entity.setDatosPersonales(datos);
        return entity;
    }

    // TU MÉTODO EXISTENTE: Traduce de Entidad a DTO (Para Responder sin password)
    private UsuarioDto toResponse(Usuario entity){
        var datos = entity.getDatosPersonales();

        return UsuarioDto.builder()
                .id(entity.getId())
                .nombre(datos != null ? datos.getNombre() : "")
                .apellido(datos != null ? datos.getApellido() : "")
                .email(datos != null ? datos.getEmail() : "")
                .telefono(datos != null ? datos.getTelefono() : "")
                .rol(entity.getRolUsuario() != null ? entity.getRolUsuario().name() : "ASPIRANTE")
                .estado(entity.getActivo() != null ? entity.getActivo().name() : "INACTIVO")
                .cantidadCursos(entity.getCursos() != null ? entity.getCursos().size() : 0)
                .cantidadSolicitudes(entity.getSolicitudes() != null ? entity.getSolicitudes().size() : 0)
                .build();
    }
}