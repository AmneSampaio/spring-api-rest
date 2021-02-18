package com.desafio.api.spring_boot.service;

import com.desafio.api.spring_boot.exceptions.*;
import com.desafio.api.spring_boot.repository.UsuarioRepository;
import com.desafio.api.spring_boot.usuario.Usuario;
import com.desafio.api.spring_boot.usuario.UsuarioDTO;
import com.desafio.api.spring_boot.usuario.UsuarioEmailPasswordDTO;
import com.desafio.api.spring_boot.utility.EncryptPasswordUtility;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


    public ResponseEntity<List<Usuario>> getAllUsers() {
        return new ResponseEntity<List<Usuario>>(this.usuarioRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Usuario> getUsuario(String token) {

        Optional<Usuario> usuarioByToken = usuarioRepository.findUsuarioByToken(UUID.fromString(token));

        if (usuarioByToken.isPresent()) {

            return new ResponseEntity<Usuario>(usuarioByToken.get(), HttpStatus.OK);

        } else {
            throw new UserNotFoundException(
                    "Usuário e/ou senha inválidos");
        }
    }

    public ResponseEntity<Usuario> addNewUsuario(UsuarioDTO usuarioDTO, String role) {

        Optional<Usuario> usuarioByEmail = usuarioRepository.findUsuarioByEmail(usuarioDTO.getEmail());
        Optional<Usuario> usuarioByName = usuarioRepository.findUsuarioByName(usuarioDTO.getName());

        if (usuarioByEmail.isPresent()) {
            throw new IllegalStateException("Este e-mail já está sendo utilizado, " +
                    "tente novamente com outro endereço");
        } else if (usuarioByName.isPresent()) {
            throw new IllegalStateException("Este nome de usuário já está sendo utilizado, " +
                    "tente novamente com outro nome");
        }

        String encoded = EncryptPasswordUtility.EncondePassword(usuarioDTO.getPassword());
        usuarioDTO.setPassword(encoded);

        Usuario novoUsuario = new Usuario(usuarioDTO, role);
        usuarioRepository.save(novoUsuario);

        return new ResponseEntity<Usuario>(novoUsuario, HttpStatus.OK);

    }

    public ResponseEntity<Usuario> addNewAdmin(
            UsuarioDTO usuarioDTO, String token) {

        Optional<Usuario> usuarioAVerificar = usuarioRepository.findUsuarioByToken(UUID.fromString(token));

        if (usuarioAVerificar.isPresent() && usuarioAVerificar.get().getRole().equals("admin")) {
            return this.addNewUsuario(usuarioDTO, "admin");
        } else {
            throw new UserNotFoundException(
                    "Usuário e/ou senha inválidos");
        }
    }

    public ResponseEntity<Usuario> loginUsuario(@NotNull UsuarioEmailPasswordDTO usuarioDTO, String role) throws UserNotFoundException, PasswordNotFoundException {

        Optional<Usuario> verificaEmailLogin = usuarioRepository.findUsuarioByEmail(usuarioDTO.getEmail());

        if (verificaEmailLogin.isPresent()) {

            String encoded = EncryptPasswordUtility.EncondePassword(usuarioDTO.getPassword());

            if (verificaEmailLogin.get().getPassword().equals(encoded)) {

                verificaEmailLogin.get().setToken(UUID.randomUUID());

                verificaEmailLogin.get().setUltimoLoginUsuario(LocalDateTime.now());

                usuarioRepository.save(verificaEmailLogin.get());

                return new ResponseEntity<Usuario>(verificaEmailLogin.get(), HttpStatus.OK);

            } else {

                throw new PasswordNotFoundException(
                        "Usuário e/ou senha inválidos");

            }
        } else {

            throw new UserNotFoundException(
                    "Usuário e/ou senha inválidos");


        }


    }

    public ResponseEntity<Usuario> loginAdmin(@NotNull UsuarioEmailPasswordDTO usuarioDTO) throws UserNotFoundException, PasswordNotFoundException {
        Optional<Usuario> verificaEmailLoginAdmin = usuarioRepository.findUsuarioByEmail(usuarioDTO.getEmail());

        if (verificaEmailLoginAdmin.isPresent() && verificaEmailLoginAdmin.get().getRole().equals("admin")) {
            return this.loginUsuario(usuarioDTO, "admin");
        } else {
            throw new UserNotFoundException(
                    "Usuário e/ou senha inválidos");
        }

    }

    public ResponseEntity<String> deleteUsuario(String token) {
        Optional<Usuario> usuarioToken = usuarioRepository.findUsuarioByToken(UUID.fromString(token));
        //boolean paraApagar = usuarioRepository.existsByToken(UUID.fromString(token));
        if (!usuarioToken.isPresent()) {
            throw new InvalidTokenException(
                    "Token inválido");
        }
        usuarioRepository.deleteById(usuarioToken.get().getId());
        return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<String> deleteAdmin(Long usuarioId, String token) {
        Optional<Usuario> adminToken = usuarioRepository.findUsuarioByToken(UUID.fromString(token));


        if (!adminToken.isPresent()) {

            throw new InvalidTokenException(
                    "Token inválido");
        }

            Optional<Usuario> usuarioProcurado = usuarioRepository.findById(usuarioId);

        if (!usuarioProcurado.isPresent()) {

            throw new UserNotFoundException(
                     "Id inválido");
        }

        usuarioRepository.deleteById(usuarioProcurado.get().getId());
        return new ResponseEntity<String>(HttpStatus.NO_CONTENT);


    }


    @Transactional
    public ResponseEntity<Usuario> updateUsuario(UsuarioDTO usuarioDTO,
                                                 String token) {

        Optional<Usuario> usuarioAVerificar = usuarioRepository.findUsuarioByToken(UUID.fromString(token));

        if (usuarioAVerificar.isPresent()) {
            /*
            String name = usuario.getName();
            String email = usuario.getEmail();
            List<Telefone> telefone = usuario.getTelefone();
            String password = usuario.getPassword();*/

            if (usuarioDTO.getName().isEmpty() || usuarioDTO.getName().length() <= 3) {

                throw new InvalidNameLengthException(
                        "Usuário vazio ou comprimento insuficiente");
            }

            if (usuarioDTO.getEmail().isEmpty() || usuarioDTO.getEmail().length() <= 3) {

                throw new InvalidEmailLengthException(
                        "Email vazio ou comprimento insuficiente");

            }

            if (usuarioDTO.getPassword().isEmpty() || usuarioDTO.getPassword().length() < 4) {

                throw new InvalidPasswordLengthException(
                        "Senha vazia ou comprimento insuficiente");
            }

            if (usuarioDTO.getTelefone().isEmpty()) {

                throw new InvalidTelefoneLengthException(
                        "Telefone vazio ou comprimento insuficiente");
            }

            String encoded = EncryptPasswordUtility.EncondePassword(usuarioDTO.getPassword());
            usuarioAVerificar.get().setPassword(encoded);

            usuarioAVerificar.get().setName(usuarioDTO.getName());
            usuarioAVerificar.get().setTelefone(usuarioDTO.getTelefone());
            usuarioAVerificar.get().setEmail(usuarioDTO.getEmail());

            usuarioAVerificar.get().setModificacaoUsuario(LocalDateTime.now());
            usuarioRepository.save(usuarioAVerificar.get());

            return new ResponseEntity<Usuario>(usuarioAVerificar.get(), HttpStatus.OK);
        } else {

            throw new InvalidTokenException(
                    "Token inválido");
        }

    }

    @Transactional
    public ResponseEntity<Usuario> updateAdmin(UsuarioDTO usuarioDTO,
                                                 String token) {

        Optional<Usuario> adminAVerificar = usuarioRepository.findUsuarioByToken(UUID.fromString(token));
        if (adminAVerificar.isPresent()) {

            return this.updateUsuario(usuarioDTO, token);
        } else {

            throw new InvalidTokenException(
                    "Token inválido");

        }

    }
}


