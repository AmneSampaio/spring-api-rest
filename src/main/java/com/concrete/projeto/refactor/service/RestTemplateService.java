package com.concrete.projeto.refactor.service;

import com.concrete.projeto.refactor.api.object.ViaCepObject;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

public class RestTemplateService {

    public static ViaCepObject getCepViaRestTemplate(String meuCep){
        RestTemplate restTemplate =  new RestTemplateBuilder().build();
        try{
            return restTemplate.getForObject("https://viacep.com.br/ws/" + meuCep + "/json/", ViaCepObject.class);
        }catch (Exception e){
            return null;
        }
    }
}
