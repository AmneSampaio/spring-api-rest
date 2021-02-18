package com.desafio.api.spring_boot.utility;

import com.desafio.api.spring_boot.repository.UsuarioRepository;
import com.desafio.api.spring_boot.usuario.Usuario;
import com.desafio.api.spring_boot.usuario.Telefone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class UsuarioConfig {

    @Bean
    @Autowired
    CommandLineRunner commandLineRunner(UsuarioRepository repositorio) {
        return args -> {
            Telefone celular = new Telefone("99189122",21);
            Telefone fixo = new Telefone("72727272", 21);
            Telefone celulara = new Telefone("94189122",21);
            Telefone fixoa = new Telefone("74727272", 21);
            List<Telefone> telefones = new ArrayList<Telefone>();
            List<Telefone> telefonesa = new ArrayList<Telefone>();
            telefones.add(celular);
            telefones.add(fixo);
            telefonesa.add(celulara);
            telefonesa.add(fixoa);
            List<Usuario> ListaUsuarios = new ArrayList<Usuario>();
            ListaUsuarios.add(
                    new Usuario(
                            "Juliana",
                            "jujuzinha@gmail.com",
                            "8815554d743c014584418a01f7ad995f",
                            telefones,
                            "user")
                            );
            ListaUsuarios.add(
                    new Usuario(
                            "admin",
                            "admin@gmail.com",
                            "8815554d743c014584418a01f7ad995f",
                            telefonesa,
                            "admin"
                                )
                            );
            //3355539
            repositorio.saveAll(ListaUsuarios);

        };
    }
}
