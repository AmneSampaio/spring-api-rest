package com.desafio.api.spring_boot.controller;

import com.desafio.api.spring_boot.usuario.UsuarioDTO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@ContextConfiguration(classes = UsuarioController.class)
@WebMvcTest
public class UsuarioControllerSecondTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void findAll(UsuarioDTO usuarioDTO) throws Exception {

        String url = "/api/v1/user";
        MvcResult result = this.mvc.perform(MockMvcRequestBuilders.get(url))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String resultTest = result.getResponse().getContentAsString();
        assertNotNull(resultTest);
    }
}