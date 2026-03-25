package com.grupo2.nexus.service;
import com.grupo2.nexus.model.dto.UsuarioDto;
import com.grupo2.nexus.model.embeddable.DatosPersonales;
import com.grupo2.nexus.model.entity.Usuario;
import com.grupo2.nexus.model.enums.RolUsuario;
import com.grupo2.nexus.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


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
                .map(x -> this.toResponse(x))
                .orElseThrow(()  -> new RuntimeException("Usuario no encontrado"));
    }

    @Transactional
    public UsuarioDto save(Usuario entity){

        if (entity.getDatosPersonales() == null){
            throw new RuntimeException("Los datos personales son obligatorios");
        }
        String email = entity.getDatosPersonales().getEmail();

        if(usuarioRepository.existsByDatosPersonalesEmail(email)){
            throw new RuntimeException("El correo electrónico ya está registado: " + email);
        }

        if(entity.getRolUsuario() == null){
            entity.setRolUsuario(RolUsuario.ASPIRANTE);
        }

        Usuario saved = usuarioRepository.save(entity);

        return toResponse(saved);

    }

    @Transactional
    public UsuarioDto update(Long id, Usuario usuarioActualizado) {
        // 1. Buscamos el usuario existente (la Entidad real)
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));

        // 2. Actualizamos los Datos Personales (El @Embedded)
        if (usuarioActualizado.getDatosPersonales() != null) {
            DatosPersonales nuevosDatos = usuarioActualizado.getDatosPersonales();
            DatosPersonales datosActuales = usuarioExistente.getDatosPersonales();

            datosActuales.setNombre(nuevosDatos.getNombre());
            datosActuales.setApellido(nuevosDatos.getApellido());
            datosActuales.setTelefono(nuevosDatos.getTelefono());
            // El email generalmente no se deja cambiar así de fácil por seguridad,
            // pero si quieres, lo incluyes aquí:
            // datosActuales.setEmail(nuevosDatos.getEmail());
        }

        // 3. Actualizamos campos de la Entidad Usuario
        if (usuarioActualizado.getRolUsuario() != null) {
            usuarioExistente.setRolUsuario(usuarioActualizado.getRolUsuario());
        }

        if (usuarioActualizado.getActivo() != null) {
            usuarioExistente.setActivo(usuarioActualizado.getActivo());
        }

        // 4. Guardamos y devolvemos el DTO limpio
        return toResponse(usuarioRepository.save(usuarioExistente));
    }



    public void delete (Long id){
        usuarioRepository.deleteById(id);
    }


    private UsuarioDto toResponse(Usuario entity){
        var datos = entity.getDatosPersonales();

        return UsuarioDto.builder()
                .id(entity.getId_usuario()) // Tu ID en la entidad es id_usuario

                .nombre(datos != null ? datos.getNombre() : "")
                .apellido(datos != null ? datos.getApellido() : "")
                .email(datos != null ? datos.getEmail() : "")
                .telefono(datos != null ? datos.getTelefono() : "")
                // 3. Manejo de Enums: .name() los pasa a String
                .rol(entity.getRolUsuario() != null ? entity.getRolUsuario().name() : "CLIENTE")
                .estado(entity.getActivo() != null ? entity.getActivo().name() : "INACTIVO")
                // 4. Conteo de listas (Recuerda que esto requiere una sesión de Hibernate activa)
                .cantidadCursos(entity.getCursos() != null ? entity.getCursos().size() : 0)
                .cantidadSolicitudes(entity.getSolicitudes() != null ? entity.getSolicitudes().size() : 0)
                .build();
    }
}
