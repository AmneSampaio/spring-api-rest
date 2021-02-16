package com.desafio.api.spring_boot.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;


@AutoConfigureMockMvc
@ContextConfiguration(classes = UsuarioController.class)
//@ExtendWith(SpringExtension.class)
@WebMvcTest
public class UsuarioControllerTest {

    private MockMvc mvc;


    @Test
    void testaSeUsuarioPadraoExiste() throws Exception {

        String url = "/api/v1/user";
        MvcResult result = this.mvc.perform(MockMvcRequestBuilders.get(url))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String resultTest = result.getResponse().getContentAsString();
        assertNotNull(resultTest);
        //assertEquals(dow, resultDOW);

/*
        System.out.println(mvcResult.getResponse());
*/

//        Mockito.verify(usuarioRepository).findAll();

    }

    @org.junit.jupiter.api.Test
    void getAllUsers() {
    }


    /*@Test
    void getAllUsers() {



    }

    @Test
    void getUsuario() {
    }

    @Test
    void registerNewUsuario() {
    }

    @Test
    void loginUsuario() {
    }

    @Test
    void deleteUsuario() {
    }

    @Test
    void updateUsuario() {
    }*/
}