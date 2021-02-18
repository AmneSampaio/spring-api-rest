package com.desafio.api.spring_boot.usuario;

import java.util.List;


public class UsuarioDTO {

    private String name;
    private String email;
    private String password;
    private List<Telefone> telefone;

    public UsuarioDTO() {
        super();
    }
 

    public UsuarioDTO(String name,
                      String email,
                      String password,
                      List<Telefone> telefone) {

        this.name = name;
        this.email = email;
        this.password = password;
        this.telefone = telefone;

    }

    public String getName() {
        return name;
    }

   /* public void setName(String name) {
        this.name = name;
    }*/

    public String getEmail() {
        return email;
    }

/*    public void setEmail(String email) {
        this.email = email;
    }*/

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Telefone> getTelefone() {
        return this.telefone;
    }

/*
    public void setTelefone(List<Telefone> telefone) {
        this.telefone = telefone;
    }
*/



    @Override
    public String toString() {
        return "UsuarioDTO{" +
                "  name='" + this.name + '\'' +
                ", email='" + this.email + '\'' +
                ", password='" + this.password + '\'' +
                ", telefone='" + this.telefone +  '\'' +
                '}';
    }
}



