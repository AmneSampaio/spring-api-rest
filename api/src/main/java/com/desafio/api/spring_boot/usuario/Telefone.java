package com.desafio.api.spring_boot.usuario;


import javax.persistence.*;

@Entity
public class Telefone {

    @Id
    @GeneratedValue
    private long Id;
    //private Usuario usuario;
    private String numero;
    private int ddd;


    public Telefone(String numero, int ddd) {
        this.numero = numero;
        this.ddd = ddd;

    }

    public Telefone() {
        super();
    }

    public String getNumero() {
        return this.numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public int getDdd() {
        return this.ddd;
    }

    public void setDdd(int ddd) {
        this.ddd = ddd;
    }

    @Override
    public String toString() {
        return "{" +
                "  numero='" + this.numero + '\'' +
                ", ddd='" + this.ddd + '\'' +
                '}';
    }
}