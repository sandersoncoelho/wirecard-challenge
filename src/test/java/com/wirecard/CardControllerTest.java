
package com.wirecard;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CardControllerTest {
	
	@LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
    
    private String url;
    
    @Before
    public void beforeTests() {
    	this.url = "http://localhost:";
    }

	@Test
	public void testCheckCard() {
		ResponseEntity<Boolean> response = this.restTemplate.getForEntity(this.url + port + "/cards/1234567890/status", Boolean.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isFalse();
	}
	
	@Test
	public void testGetCardIssuer() {
		ResponseEntity<String> response = this.restTemplate.getForEntity(this.url + port + "/cards/1234567890/issuer", String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isNull();
	}
}
