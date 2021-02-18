package com.desafio.api.spring_boot.controller;

import com.desafio.api.spring_boot.exceptions.PasswordNotFoundException;
import com.desafio.api.spring_boot.exceptions.UserNotFoundException;
import com.desafio.api.spring_boot.service.UsuarioService;
import com.desafio.api.spring_boot.usuario.Usuario;
import com.desafio.api.spring_boot.usuario.UsuarioDTO;
import com.desafio.api.spring_boot.usuario.UsuarioEmailPasswordDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "api/v1/admin")
public class AdminController {

    private final UsuarioService usuarioService;

    @Autowired
    public AdminController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping(path = "allusers")
    public ResponseEntity<List<Usuario>> getAllUsers() {
        return this.usuarioService.getAllUsers();
    }

    @PostMapping
    public ResponseEntity<Usuario> registerAdmin(
            @RequestBody UsuarioDTO usuarioDTO,
            @RequestHeader("access-token") String token) {
        return usuarioService.addNewAdmin(usuarioDTO, token);
    }

    @PostMapping(path = "login")
    public ResponseEntity<Usuario> loginAdmin(
            @RequestBody UsuarioEmailPasswordDTO usuarioEmailPasswordDTO) {
        return usuarioService.loginAdmin(usuarioEmailPasswordDTO);
    }

    @DeleteMapping
    @RequestMapping(path = "{usuarioId}")
    public ResponseEntity<String> deleteAdmin(
            @PathVariable("usuarioId") Long usuarioId,
            @RequestHeader("access-token") String token) {
        return usuarioService.deleteAdmin(usuarioId, token);
    }

    @PutMapping
    public ResponseEntity<Usuario> updateAdmin(
            @RequestBody UsuarioDTO usuarioDTO,
            @RequestHeader("access-token") String token
    ) {
        return usuarioService.updateAdmin(usuarioDTO, token);
    }

}
