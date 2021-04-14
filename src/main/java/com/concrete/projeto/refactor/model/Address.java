package com.concrete.projeto.refactor.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String street;
    private String complement;
    private String cep;
    private Integer number;

    public Address() {
    }

    public Address(String name, String street, String complement, String cep, Integer number) {
        this.name = name;
        this.street = street;
        this.complement = complement;
        this.cep = cep;
        this.number = number;
    }

    public Address(Long id, String name, String street, String complement, String cep, Integer number) {
        this.id = id;
        this.name = name;
        this.street = street;
        this.complement = complement;
        this.cep = cep;
        this.number = number;
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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    /*@Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", complement='" + complement + '\'' +
                ", cep='" + cep + '\'' +
                ", number=" + number +
                '}';
    }*/
}
