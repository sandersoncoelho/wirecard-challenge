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

import com.wirecard.model.Buyer;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class BuyerControllerTest {
	
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
	public void testCreateBuyer() {
		Buyer buyer = MockUtils.mockBuyer("Sanderson Monteiro Coelho", "123456789", "sanderson.mc@gmail.com");
		ResponseEntity<Buyer> response = this.restTemplate.postForEntity(this.url + port + "/buyers", buyer, Buyer.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody().getId()).isNotNull();
		assertThat(response.getBody().getName()).isEqualTo(buyer.getName());
		assertThat(response.getBody().getCpf()).isEqualTo(buyer.getCpf());
		assertThat(response.getBody().getEmail()).isEqualTo(buyer.getEmail());
	}
}
