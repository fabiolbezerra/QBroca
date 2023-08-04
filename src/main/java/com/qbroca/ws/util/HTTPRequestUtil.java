package com.qbroca.ws.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import java.io.IOException;

public class HTTPRequestUtil {
    public static <T> T getObject(String url, Class<T> clazz) {
        T resposta = null;
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        try {
            resposta = httpClient.execute(new HttpGet(url),
                    response -> {
                        String bodyAsString = EntityUtils.toString(response.getEntity());
                        return new ObjectMapper().readValue(bodyAsString, clazz);
                    }
            );
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace(System.err);
            }
        }
        return resposta;
    }
}
