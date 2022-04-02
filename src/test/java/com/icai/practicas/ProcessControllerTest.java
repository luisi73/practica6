package com.icai.practicas;

import com.icai.practicas.controller.ProcessController;
import com.icai.practicas.controller.ResponseHTMLGenerator;
import com.icai.practicas.controller.ProcessController.DataRequest;
import com.icai.practicas.controller.ProcessController.DataResponse;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

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
    public void testing_processController_step1_then_ok(){
        String address = "http://localhost:" + port + "/api/v1/process-step1";
		DataRequest dataRequest = new DataRequest("Luis", "06679111A", "619321455");
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<ProcessController.DataRequest> request = new HttpEntity<>(dataRequest, headers);

		ResponseEntity<ProcessController.DataResponse> result = this.restTemplate.postForEntity(address, request, DataResponse.class);

		String expectedResult = "OK";
		DataResponse expectedResponse = new DataResponse(expectedResult);

		then(result.getBody().result()).isEqualTo(expectedResult);
		then(result.getBody()).isEqualTo(expectedResponse);

    }

    @Test
    public void testing_processController_step1_then_ko(){
    
        String address = "http://localhost:" + port + "/api/v1/process-step1";
		DataRequest dataRequest = new DataRequest("Luis", "0764365891", "649643965693456932563659");
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<ProcessController.DataRequest> request = new HttpEntity<>(dataRequest, headers);

		ResponseEntity<ProcessController.DataResponse> result = this.restTemplate.postForEntity(address, request, DataResponse.class);


		String expectedResult = "KO";
		DataResponse expectedResponse = new DataResponse(expectedResult);

		then(result.getBody().result()).isEqualTo(expectedResult);
		then(result.getBody()).isEqualTo(expectedResponse);

    }

    @Test
    public void testing_processController_step1__legacy_then_ok(){

        String address = "http://localhost:" + port + "/api/v1/process-step1-legacy";
		MultiValueMap<String, String> data = new LinkedMultiValueMap<String, String>();
        data.add("fullName", "Luis");
        data.add("dni", "06679111A");
        data.add("telefono", "619321455");
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(data, headers);

		ResponseEntity<String> result = this.restTemplate.postForEntity(address, request, String.class);

		String expectedResult = ResponseHTMLGenerator.message1;
        then(result.getBody()).isEqualTo(expectedResult);
        
    }

    @Test
    public void testing_processController_step1__legacy_then_ko(){

        String address = "http://localhost:" + port + "/api/v1/process-step1-legacy";
		MultiValueMap<String, String> data1 = new LinkedMultiValueMap<String, String>();
        data1.add("fullName", "Luis");
        data1.add("dni", "0764365891");
        data1.add("telefono", "649643965693456932563659");
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(data1, headers);

		ResponseEntity<String> result = this.restTemplate.postForEntity(address, request, String.class);

		String expectedResult = ResponseHTMLGenerator.message2;
        then(result.getBody()).isEqualTo(expectedResult);
        
    }
    
}