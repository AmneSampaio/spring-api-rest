package com.desafio.api.spring_boot.controller;

import com.desafio.api.spring_boot.usuario.Usuario;
import com.desafio.api.spring_boot.usuario.UsuarioDTO;
import com.desafio.api.spring_boot.usuario.UsuarioEmailPasswordDTO;
import com.desafio.api.spring_boot.exceptions.PasswordNotFoundException;
import com.desafio.api.spring_boot.exceptions.UserNotFoundException;
import com.desafio.api.spring_boot.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/user")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /*@GetMapping
	public List<Usuario> getAllUsers() {
        return this.usuarioService.getAllUsers();
    }
*/
    @GetMapping(path = "profile")
    public ResponseEntity<Usuario> getUsuario(
            @RequestHeader("access-token") String token) {

        return this.usuarioService.getUsuario(token);
    }

   /* @GetMapping(path = "hello")
    public ResponseEntity<String> getsldlsd() {

        return new ResponseEntity<String>("Hello", HttpStatus.valueOf(200));
    }*/


    @PostMapping
    public ResponseEntity<Usuario> registerNewUsuario(
            @RequestBody UsuarioDTO usuarioDTO) {

        return usuarioService.addNewUsuario(usuarioDTO, "user");
    }

    @PostMapping(path = "login")
    public ResponseEntity<Usuario> loginUsuario(
            @RequestBody UsuarioEmailPasswordDTO usuarioEmailPasswordDTO) throws
            UserNotFoundException, PasswordNotFoundException {

        return usuarioService.loginUsuario(usuarioEmailPasswordDTO, "user");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteUsuario(
            @RequestHeader("access-token") String token) {

        return usuarioService.deleteUsuario(token);
    }

    @PutMapping
    public ResponseEntity<Usuario> updateUsuario(
            @RequestBody UsuarioDTO usuarioDTO,
            @RequestHeader("access-token") String token){

       return usuarioService.updateUsuario(usuarioDTO, token);
    }

}