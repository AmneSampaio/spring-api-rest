package com.desafio.api.spring_boot.usuario;


import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
public class Usuario {

    @Id @GeneratedValue
    private long Id;
    private String name;
    private String email;
    private String password;
    private LocalDateTime criacaoUsuario;
    private LocalDateTime modificacaoUsuario;
    private LocalDateTime ultimoLoginUsuario;
    private UUID token;
    private String role;
    @OneToMany(targetEntity = Telefone.class,cascade = CascadeType.ALL)
    private List<Telefone> telefone;




    public Usuario() {
        super();
    }

    public Usuario(UsuarioDTO usuarioDTO, String role) {
        this(usuarioDTO.getName(),
             usuarioDTO.getEmail(),
             usuarioDTO.getPassword(),
             usuarioDTO.getTelefone(),
             role);


    }

    public Usuario(String name,
                   String email,
                   String password,
                   List<Telefone> telefone,
                   String role) {

        this.name = name;
        this.email = email;
        this.password = password;
        this.telefone = telefone;
        this.role = role;
        LocalDateTime agora = LocalDateTime.now();
        this.criacaoUsuario = agora;
        this.modificacaoUsuario = agora;
        this.ultimoLoginUsuario = agora;
        this.token = UUID.randomUUID();
    }

    public long getId() {
        return this.Id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTelefone(List<Telefone> telefone) {
        this.telefone = telefone;
    }

    public void setModificacaoUsuario(LocalDateTime modificacaoUsuario) {
        this.modificacaoUsuario = modificacaoUsuario;
    }

    public void setUltimoLoginUsuario(LocalDateTime ultimoLoginUsuario) {
        this.ultimoLoginUsuario = ultimoLoginUsuario;
    }

    public void setToken(UUID token) {
        this.token = token;
    }

    public String getRole() {
        return this.role;
    }

/*
    public void setId(long id) {
        this.Id = id;
    }*/

    public String getName() {
        return name;
    }

    public List<Telefone> getTelefone() {
        return this.telefone;
    }

    public LocalDateTime getCriacaoUsuario() {
        return this.criacaoUsuario;
    }

    public LocalDateTime getModificacaoUsuario() {
        return this.modificacaoUsuario;
    }

    public LocalDateTime getUltimoLoginUsuario() {
        return this.ultimoLoginUsuario;
    }

    public UUID getToken() {
        return this.token;
    }
/*
    public void setRole(String role) {
        this.role = role;
    }
    */
    @Override
    public String toString() {
        return "Usuario{" +
                ", id='" + this.Id + '\'' +
                ", name='" + this.name + '\'' +
                ", email='" + this.email + '\'' +
                ", password='" + this.password + '\'' +
                ", telefone='" + this.telefone +  '\'' +
                ", criacaoUsuario='" + this.criacaoUsuario +  '\'' +
                ", modificacaoUsuario='" + this.modificacaoUsuario +  '\'' +
                ", ultimoLoginUsuario='" + this.ultimoLoginUsuario +  '\'' +
                ", token=" + this.token +  '\'' +
                '}';
    }
}



