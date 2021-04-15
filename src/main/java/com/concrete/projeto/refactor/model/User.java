package com.concrete.projeto.refactor.model;

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
public class User {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    //@NotBlank(message = "Fill field 'name' ")
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

    private LocalDateTime userSignIn;
    private LocalDateTime userUpdate;
    private LocalDateTime lastUserLogin;

    public User() {
    }

    public User(String name, 
                String email,
                String password,
                List<Phone> phone) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }



    public User(UUID id,
                String name,
                String email,
                String password,
                List<Phone> phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        LocalDateTime now = LocalDateTime.now();
        this.userSignIn = now;
        this.userUpdate = now;
        this.lastUserLogin = now;
    }

    public User(String email,
                String password) {
        this.email = email;
        this.password = password;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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
        //cryptPassword();
    }

   /* public void cryptPassword() {
        this.password =  new BCryptPasswordEncoder().encode(password);
    }*/

    public List<Phone> getPhone() {
        return phone;
    }

    public void setPhone(List<Phone> phone) {
        this.phone = phone;
    }

    public LocalDateTime getUserSignIn() {
        return userSignIn;
    }

    public LocalDateTime getUserUpdate() {
        return userUpdate;
    }

    public void setUserUpdate(LocalDateTime userUpdate) {
        this.userUpdate = userUpdate;
    }

    public LocalDateTime getLastUserLogin() {
        return lastUserLogin;
    }

    public void setLastUserLogin(LocalDateTime lastUserLogin) {
        this.lastUserLogin = lastUserLogin;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone=" + phone +
                ", userSignIn=" + userSignIn +
                ", userUpdate=" + userUpdate +
                ", lastUserLogin=" + lastUserLogin +
                '}';
    }
}
