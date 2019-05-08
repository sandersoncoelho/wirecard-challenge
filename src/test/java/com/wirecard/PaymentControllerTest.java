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

	/*@Override
	public Application configure() {
		forceSet(TestProperties.CONTAINER_PORT, "0");
		enable(TestProperties.LOG_TRAFFIC);
		enable(TestProperties.DUMP_ENTITY);
		return new ResourceConfig(PaymentController.class);
	}*/
    
    @Before
    public void beforeTests() {
    	this.url = "http://localhost:";
    }

	@Test
	public void testCreatePaymentBoleto() {
		Payment payment = this.mockPayment(10L, PaymentType.BOLETO, 123.12);
		ResponseEntity<String> response = this.restTemplate.postForEntity(this.url + port + "/payments", payment, String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).matches("\\d+");
	}
	
	@Test
	public void testCreatePaymentCreditCard() {
		Payment payment = this.mockPayment(11L, PaymentType.CREDIT_CARD, 234.23);
		ResponseEntity<String> response = this.restTemplate.postForEntity(this.url + port + "/payments", payment, String.class);
		List<String> possibleReturns = Arrays.asList(PaymentCardStatus.SUCCESS.getStatus(), PaymentCardStatus.FAIL.getStatus());
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isIn(possibleReturns);
		
		/*Payment payment = this.mockPayment(PaymentType.CREDIT_CARD);
		Response response = target("/payments").request().post(Entity.json(payment));
		assertEquals("should return status 200", Status.OK.getStatusCode(), response.getStatus());
		String returned = response.readEntity(String.class);
		
		assertTrue(possibleReturns.contains(returned));*/
	}
	
	@Test 
	public void testGetPayment() {
		Long id = 2L;
		ResponseEntity<Payment> response = this.restTemplate.getForEntity(this.url + port + "/payments/" + id,
				Payment.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody().getId()).isEqualTo(id);
		
		/*Long id = 1L;
		Response response = target("/payments/" + id).request().get();
		assertEquals("should return status 200", 200, response.getStatus());
		Payment payment = response.readEntity(Payment.class);
		assertEquals("Payment ID", id, payment.getId());*/
	}
	
	private Payment mockPayment(Long id, PaymentType paymentType, Double amount) {
		Payment payment = new Payment();
		payment.setId(id);
		payment.setType(paymentType);
		payment.setAmount(amount);
		return payment;
	}
}