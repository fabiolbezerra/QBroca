package com.qbroca.ws.client;

import com.qbroca.ws.dto.ViaCepDTO;
import com.qbroca.ws.util.HTTPRequestUtil;

/**
 * Classe de integração com a API VIACEP, de consumo de serviços.
 */
public class ViaCepClient {

    private final String URL = "https://viacep.com.br/ws/";
    private final String JSON = "/json";

    public ViaCepDTO pesquisarCEP(String cep) {
        String urlString = URL + cep + JSON;
        ViaCepDTO resposta = HTTPRequestUtil.getObject(urlString, ViaCepDTO.class);
        return resposta;
    }

}
