package com.concrete.projeto.refactor.model;

import com.sun.istack.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Field name cannot be empty!")
    @Column(unique = true, nullable = false)
    @Size(min = 1, max = 10)
    private String name;
    @Email
    @NotBlank(message = "Field email cannot be empty!")
    @Column(unique = true, nullable = false)
    @Size(min = 1, max = 30)
    private String email;
    @CPF
    @Column(unique = true, nullable = false)
    private String cpf;
    @NotBlank(message = "campo login não pode ser nulo")
    @Column(nullable = false, unique = true)
    @Size(min = 5, max = 10)
    private String login;
    @NotBlank(message = "campo senha não pode ser nulo")
    @Column(nullable = false)
    private String senha;
    @Column(nullable = false)
    private Date dataNascimento;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<Phone> phone;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<Address> address;


    /*@OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    */

    public User() {
    }

    public User(Long id,
                String name,
                String email,
                String cpf,
                String login,
                String senha,
                Date dataNascimento,
                List<Phone> phone,
                List<Address> address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.login = login;
        this.senha = senha;
        this.dataNascimento = dataNascimento;
        this.phone = phone;
        this.address = address;
    }

    public User(String name,
                String email,
                String cpf,
                String login,
                String senha,
                Date dataNascimento,
                List<Phone> phone,
                List<Address> address) {

        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.login = login;
        this.senha = senha;
        this.dataNascimento = dataNascimento;
        this.phone = phone;
        this.address = address;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public List<Phone> getPhone() {
        return phone;
    }

    public void setPhone(List<Phone> phone) {
        this.phone = phone;
    }

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", cpf='" + cpf + '\'' +
                ", login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", phone=" + phone +
                ", address=" + address +
                '}';
    }
}
