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
import com.wirecard.enums.PaymentStatus;
import com.wirecard.enums.PaymentType;
import com.wirecard.model.Buyer;
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
		Payment payment = this.mockPayment(PaymentType.BOLETO.getValue(), 123.12);
		payment.setBuyer(this.mockBuyer(12L, "Sanderson Monteiro Coelho", "123456789", "sanderson.mc@gmail.com"));
		ResponseEntity<String> response = this.restTemplate.postForEntity(this.url + port + "/payments", payment, String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).matches("\\d+");
	}
	
	@Test
	public void testCreatePaymentCreditCard() {
		Payment payment = this.mockPayment(PaymentType.CREDIT_CARD.getValue(), 234.23);
		ResponseEntity<String> response = this.restTemplate.postForEntity(this.url + port + "/payments", payment, String.class);
		List<String> possibleReturns = Arrays.asList(PaymentCardStatus.SUCCESS.getDescription(), PaymentCardStatus.FAIL.getDescription());
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
	
	@Test 
	public void testGetPaymentStatus() {
		Long id = 2L;
		ResponseEntity<Integer> response = this.restTemplate.getForEntity(this.url + port + "/payments/" + id + "/status",
				Integer.class);
		List<Integer> possibleReturns = Arrays.asList(PaymentStatus.PENDING.getValue(), PaymentStatus.PAYED.getValue(),
				PaymentStatus.DENIED.getValue());
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isIn(possibleReturns);
	}
	
	private Payment mockPayment(Integer paymentType, Double amount) {
		Payment payment = new Payment();
		payment.setType(paymentType);
		payment.setAmount(amount);
		return payment;
	}
	
	private Buyer mockBuyer(Long id, String name, String cpf, String email) {
		Buyer buyer = new Buyer();
		buyer.setId(id);
		buyer.setName(name);
		buyer.setCpf(cpf);
		buyer.setEmail(email);
		return buyer;
	}
}