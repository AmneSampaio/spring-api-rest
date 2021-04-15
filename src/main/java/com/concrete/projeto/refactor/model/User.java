package com.concrete.projeto.refactor.model;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Fill field 'name' ")
    @Column(unique = true, nullable = false)
    @Size(min = 1, max = 10)
    private String name;
    @Email
    @NotBlank(message = "Fill field 'email'" )
    @Column(unique = true, nullable = false)
    @Size(min = 1, max = 30)
    private String email;
    @NotBlank(message = "Fill field 'password'")
    @Column(nullable = false)
    private String password;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<Phone> phone;

    public User() {
    }

    public User(String name, 
                String email,
                String login, 
                String password,
                List<Phone> phone) {
        this.name = name;
        this.email = email;
        this.login = login;
        this.password = password;
        this.phone = phone;
    }

    public User(Long id,
                String name,
                String email,
                String login,
                String password,
                List<Phone> phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.login = login;
        this.password = password;
        this.phone = phone;
    }

    public User(String email,
                String password) {
        this.email = email;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        cryptPassword();
    }

    public void cryptPassword() {
        this.password =  new BCryptPasswordEncoder().encode(password);
    }

    public List<Phone> getPhone() {
        return phone;
    }

    public void setPhone(List<Phone> phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone=" + phone +
                '}';
    }


}
