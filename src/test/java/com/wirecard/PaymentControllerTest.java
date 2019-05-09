package com.wirecard;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.wirecard.enums.PaymentCardStatus;
import com.wirecard.enums.PaymentType;
import com.wirecard.model.Payment;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PaymentControllerTest {
	
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
	public void testCreatePaymentBoleto() {
		Payment payment = this.mockPayment(10L, PaymentType.BOLETO.getValue(), 123.12);
		ResponseEntity<String> response = this.restTemplate.postForEntity(this.url + port + "/payments", payment, String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).matches("\\d+");
	}
	
	@Test
	public void testCreatePaymentCreditCard() {
		Payment payment = this.mockPayment(11L, PaymentType.CREDIT_CARD.getValue(), 234.23);
		ResponseEntity<String> response = this.restTemplate.postForEntity(this.url + port + "/payments", payment, String.class);
		List<String> possibleReturns = Arrays.asList(PaymentCardStatus.SUCCESS.getDescription(), PaymentCardStatus.FAIL.getDescription());
		System.out.println("TESTE: " + response.getBody());
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isIn(possibleReturns);
	}
	
	@Test 
	public void testGetPayment() {
		Long id = 2L;
		ResponseEntity<Payment> response = this.restTemplate.getForEntity(this.url + port + "/payments/" + id,
				Payment.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody().getId()).isEqualTo(id);
	}
	
	private Payment mockPayment(Long id, Integer paymentType, Double amount) {
		Payment payment = new Payment();
		payment.setId(id);
		payment.setType(paymentType);
		payment.setAmount(amount);
		return payment;
	}
}