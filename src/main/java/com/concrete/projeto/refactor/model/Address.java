package com.concrete.projeto.refactor.model;

import com.concrete.projeto.refactor.api.object.ViaCepObject;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String logradouro;

    private String cep;

    private String bairro;

    private String localidade;

    private String uf;

    private Integer numero;

    private String complemento;

    @JsonIgnore
    private String ibge;

    @JsonIgnore
    private String gia;

    @JsonIgnore
    private String ddd;

    @JsonIgnore
    private String siafi;

    public Address() {
    }

    public Address(Long id,
                   String logradouro,
                   String cep,
                   String bairro,
                   String localidade,
                   String uf,
                   Integer numero,
                   String complemento,
                   String ibge,
                   String gia,
                   String ddd,
                   String siafi) {

        this.id = id;
        this.logradouro = logradouro;
        this.cep = cep;
        this.bairro = bairro;
        this.localidade = localidade;
        this.uf = uf;
        this.numero = numero;
        this.complemento = complemento;
        this.ibge = ibge;
        this.gia = gia;
        this.ddd = ddd;
        this.siafi = siafi;
    }

    public Address(String logradouro,
                   String cep,
                   String bairro,
                   String localidade,
                   String uf,
                   Integer numero,
                   String complemento,
                   String ibge,
                   String gia,
                   String ddd,
                   String siafi) {

        this.logradouro = logradouro;
        this.cep = cep;
        this.bairro = bairro;
        this.localidade = localidade;
        this.uf = uf;
        this.numero = numero;
        this.complemento = complemento;
        this.ibge = ibge;
        this.gia = gia;
        this.ddd = ddd;
        this.siafi = siafi;

    }

    public Address(ViaCepObject viaCepObject) {
        this.logradouro = viaCepObject.getLogradouro();
        this.cep = viaCepObject.getCep();
        this.bairro = viaCepObject.getBairro();
        this.localidade = viaCepObject.getLocalidade();
        this.uf = viaCepObject.getUf();
        this.ibge = viaCepObject.getIbge();
        this.gia = viaCepObject.getGia();
        this.ddd = viaCepObject.getDdd();
        this.siafi = viaCepObject.getSiafi();

    }

    public void updateWithViaCepObject(ViaCepObject viaCepObject) {

        this.logradouro = viaCepObject.getLogradouro();
        this.cep = viaCepObject.getCep();
        this.bairro = viaCepObject.getBairro();
        this.localidade = viaCepObject.getLocalidade();
        this.uf = viaCepObject.getUf();
        this.ibge = viaCepObject.getIbge();
        this.gia = viaCepObject.getGia();
        this.ddd = viaCepObject.getDdd();
        this.siafi = viaCepObject.getSiafi();

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

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public String toString() {

        return "Address{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", logradouro='" + logradouro + '\'' +
                ", cep='" + cep + '\'' +
                ", bairro='" + bairro + '\'' +
                ", uf='" + uf + '\'' +
                ", complemento='" + complemento + '\'' +
                ", number=" + number +
                '}';

    }
}
