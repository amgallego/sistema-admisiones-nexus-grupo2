package com.grupo2.nexus.repository;

import com.grupo2.nexus.model.entity.Curso;
import com.grupo2.nexus.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByNombre(String email);

    boolean existsByDatosPersonalesEmail(String email);
}
