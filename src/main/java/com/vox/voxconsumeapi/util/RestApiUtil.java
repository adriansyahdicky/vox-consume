package com.vox.voxconsumeapi.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class RestApiUtil {

    @Autowired
    RestTemplate restTemplate;

    public <S, T> T getExtObjectWithToken(String url, Class<T> classType, String token) {
        log.debug("URL SEND GET        : {}", url);
        String authorization = "Bearer " + token;
        HttpHeaders headers = getHeaders();
        headers.set("Authorization", authorization);
        HttpEntity<String> jwtEntity = new HttpEntity<>(headers);
        ResponseEntity<T> response = restTemplate.exchange(
                url, HttpMethod.GET, jwtEntity, classType);
        T body = response.getBody();
        return body;
    }

    public <S, T> T postExtObjectWithToken(String url, Class<T> classType, String token, Object request) {
        log.debug("URL SEND POST      : {}", url);
        String authorization = "Bearer " + token;
        HttpHeaders headers = getHeaders();
        headers.set("Authorization", authorization);
        HttpEntity<Object> jwtEntity =
                new HttpEntity<>(request, headers);

        ResponseEntity<T> response = restTemplate.exchange(
                url, HttpMethod.POST, jwtEntity, classType);
        T body = response.getBody();
        return body;
    }

    public <S, T> T postExtObject(String url, Class<T> classType, Object request) {
        log.debug("URL SEND POST      : {}", url);
        HttpHeaders headers = getHeaders();
        HttpEntity<Object> entity =
                new HttpEntity<>(request, headers);
        ResponseEntity<T> response = restTemplate.exchange(
                url, HttpMethod.POST, entity, classType);
        T body = response.getBody();
        return body;
    }

    public <S, T> T putExtObjectWithToken(String url, Class<T> classType, String token, Object request) {
        log.debug("URL SEND PUT      : {}", url);
        String authorization = "Bearer " + token;
        HttpHeaders headers = getHeaders();
        headers.set("Authorization", authorization);
        HttpEntity<Object> jwtEntity =
                new HttpEntity<>(request, headers);

        ResponseEntity<T> response = restTemplate.exchange(
                url, HttpMethod.PUT, jwtEntity, classType);
        T body = response.getBody();
        return body;
    }

    public <S, T> T deleteExtObjectWithToken(String url, Class<T> classType, String token) {
        log.debug("URL SEND DELETE      : {}", url);
        String authorization = "Bearer " + token;
        HttpHeaders headers = getHeaders();
        headers.set("Authorization", authorization);
        HttpEntity<Object> jwtEntity =
                new HttpEntity<>(headers);

        ResponseEntity<T> response = restTemplate.exchange(
                url, HttpMethod.DELETE, jwtEntity, classType);
        T body = response.getBody();
        return body;
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        return headers;
    }
}
