package com.oper.springapicommon.common.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class RestTemplateService {

    private final RestTemplate restTemplate;

    public <T> T get(
            String url,
            Class<T> responseType) {

        ResponseEntity<T> response =
                restTemplate.getForEntity(
                        url,
                        responseType);

        return response.getBody();
    }

    public <T> T post(
            String url,
            Object request,
            Class<T> responseType) {

        HttpHeaders headers =
                new HttpHeaders();

        headers.setContentType(
                MediaType.APPLICATION_JSON);

        HttpEntity<Object> entity =
                new HttpEntity<>(
                        request,
                        headers);

        ResponseEntity<T> response =
                restTemplate.exchange(
                        url,
                        HttpMethod.POST,
                        entity,
                        responseType);

        return response.getBody();
    }
}