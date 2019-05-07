package com.wirecard;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

import com.wirecard.enums.PaymentType;
import com.wirecard.model.Payment;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PaymentControllerTest {
	
	@LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

	/*@Override
	public Application configure() {
		forceSet(TestProperties.CONTAINER_PORT, "0");
		enable(TestProperties.LOG_TRAFFIC);
		enable(TestProperties.DUMP_ENTITY);
		return new ResourceConfig(PaymentController.class);
	}*/

	@Test
	public void testCreatePaymentBoleto() {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/payments/2",
                Payment.class)).extracting("id").isEqualTo(2);
		/*Payment payment = this.mockPayment(PaymentType.BOLETO);
		Response response = target("/payments").request().post(Entity.json(payment));
		assertEquals("should return status 200", Status.OK.getStatusCode(), response.getStatus());
		assertTrue(response.readEntity(String.class).matches("\\d+"));*/
	}
	
	@Test
	public void testCreatePaymentCreditCard() {
		/*Payment payment = this.mockPayment(PaymentType.CREDIT_CARD);
		Response response = target("/payments").request().post(Entity.json(payment));
		assertEquals("should return status 200", Status.OK.getStatusCode(), response.getStatus());
		String returned = response.readEntity(String.class);
		List<String> possibleReturns = Arrays.asList(PaymentCardStatus.SUCCESS.getStatus(), PaymentCardStatus.FAIL.getStatus());
		assertTrue(possibleReturns.contains(returned));*/
	}
	
	@Test 
	public void testGetPayment() {
		/*Long id = 1L;
		Response response = target("/payments/" + id).request().get();
		assertEquals("should return status 200", 200, response.getStatus());
		Payment payment = response.readEntity(Payment.class);
		assertEquals("Payment ID", id, payment.getId());*/
	}
	
	private Payment mockPayment(PaymentType paymentType) {
		Payment payment = new Payment();
		payment.setId(1L);
		payment.setType(paymentType);
		payment.setAmount(12.12);
		return payment;
	}
}