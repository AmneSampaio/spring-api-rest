package com.desafio.api.spring_boot.utility;

import com.desafio.api.spring_boot.repository.UsuarioRepository;
import com.desafio.api.spring_boot.usuario.Telefone;
import com.desafio.api.spring_boot.usuario.Usuario;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class UsuarioConfig {

    @Bean
    CommandLineRunner commandLineRunner(UsuarioRepository repositorio) {
        return args -> {
            Telefone celular = new Telefone("99189122",21);
            Telefone fixo = new Telefone("72727272", 21);
            List<Telefone> telefones = new ArrayList<Telefone>();
            telefones.add(celular);
            telefones.add(fixo);
            List<Usuario> ListaUsuarios = new ArrayList<>();
            ListaUsuarios.add(
                    new Usuario(
                            "Juliana",
                            "jujuzinha@gmail.com",
                            "8815554d743c014584418a01f7ad995f",
                            telefones)
            );
            //3355539
            repositorio.saveAll(ListaUsuarios);

        };
    }
}
