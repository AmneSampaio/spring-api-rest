package com.desafio.api.spring_boot.usuario;


public class UsuarioEmailPasswordDTO {

    private String email;
    private String password;

    public UsuarioEmailPasswordDTO() {
        super();
    }

    public UsuarioEmailPasswordDTO(Usuario UsuarioEmailPasswordDTO) {
        this.email = UsuarioEmailPasswordDTO.getEmail();
        this.password = UsuarioEmailPasswordDTO.getPassword();
    }
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
