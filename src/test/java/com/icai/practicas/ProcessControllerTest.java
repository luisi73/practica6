package com.icai.practicas;

import com.icai.practicas.controller.ProcessController;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProcessControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test 
    public void given_app_when_login_using_right_credentials_then_ok() {

        String address = "http://localhost:" + port + "/api/v1/process-step1";

        
        String fullNameRaw = "Luis Bueno";
        String dniRaw = "09341324J";
        String telefonoRaw = "+34 877965872";

        ProcessController.DataRequest data1 = new ProcessController.DataRequest(fullNameRaw, dniRaw, telefonoRaw);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ProcessController.DataRequest> request = new HttpEntity<>(data1, headers);

        ResponseEntity<String> result = this.restTemplate.postForEntity(address, request, String.class);

        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @Test 
    public void given_app_when_login_using_right_credentials_then_ok_legacy() {
        String address = "http://localhost:" + port + "/api/v1/process-step1-legacy";

        MultiValueMap<String, String> data1 = new LinkedMultiValueMap<>();
        data1.add("fullNameRaw", "Luis Bueno");
        data1.add("dniRaw", "09341324J");
        data1.add("telefonoRaw", "+34 877965872");

        HttpHeaders headers = new HttpHeaders();
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(data, headers);

		ResponseEntity<String> result = this.restTemplate.postForEntity(address, request, String.class);

        String expectedResult = ResponseHTMLGenerator.message2;
        then(result.getBody()).isEqualTo(expectedResult);
        
    }
    
}