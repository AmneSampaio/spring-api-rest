package com.concrete.projeto.refactor.model;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    //@Column(unique = true)
    private String name;
    //@NotBlank
    @Size(min = 8, max = 9)
    private String number;
   // @NotBlank
    @Size(min = 3, max = 3)
    private String ddd;

    public Phone() {
    }

    public Phone(String name, String number, String ddd) {
        this.name = name;
        this.number = number;
        this.ddd = ddd;
    }

    public Phone(Long id, String name, String number, String ddd) {
        Id = id;
        this.name = name;
        this.number = number;
        this.ddd = ddd;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", ddd='" + ddd + '\'' +
                '}';
    }
}
