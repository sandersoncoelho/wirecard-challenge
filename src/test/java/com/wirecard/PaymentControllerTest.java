package com.wirecard;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
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
		Payment payment = MockUtils.mockPayment(PaymentType.BOLETO.getValue(), 123.12);
		payment.setBuyer(MockUtils.mockBuyer("Sanderson Monteiro Coelho", "123456789", "sanderson.mc@gmail.com"));
		ResponseEntity<String> response = this.restTemplate.postForEntity(this.url + port + "/payments", payment, String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).matches("\\d+");
	}
	
	@Test
	public void testCreatePaymentCreditCard() {
		Payment payment = MockUtils.mockPayment(PaymentType.CREDIT_CARD.getValue(), 234.23);
		payment.setBuyer(MockUtils.mockBuyer("Sanderson Monteiro Coelho", "123456789", "sanderson.mc@gmail.com"));
		payment.setCard(MockUtils.mockCard("Master", "1234123456785678", new Date(), 123));
		ResponseEntity<String> response = this.restTemplate.postForEntity(this.url + port + "/payments", payment, String.class);
		List<String> possibleReturns = Arrays.asList(PaymentCardStatus.SUCCESS.getDescription(), PaymentCardStatus.FAIL.getDescription());
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isIn(possibleReturns);
	}
	
	@Test 
	public void testGetPayment() {
		ResponseEntity<Payment> response = this.restTemplate.getForEntity(this.url + port + "/payments/1",
				Payment.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isNull();
	}
	
	@Test 
	public void testGetPaymentStatus() {
		ResponseEntity<Integer> response = this.restTemplate.getForEntity(this.url + port + "/payments/1/status",
				Integer.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isNull();
	}
}
