package com.desafio.api.spring_boot.repository;

import com.desafio.api.spring_boot.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    Optional<Usuario> findUsuarioByName(String name);
    Optional<Usuario> findUsuarioByEmail(String email);
    Optional<Usuario> findById(Long usuarioId);
    Optional<Usuario> findUsuarioByToken(UUID token);
    boolean existsByToken(UUID token);

}
