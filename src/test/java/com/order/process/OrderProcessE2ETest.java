package com.order.process;

import com.order.process.model.OrderProcess;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.StatusAssertions;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 0)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class OrderProcessE2ETest {

	@Autowired
	private WebTestClient webTestClient;
	private static final String BASE_PATH = "/v1/orders-process";

	@Test
	public void execute_order_process_with_sucess() {

		// given
		String idTest = "1243";

		// when
		String id = webTestClient
				.post()
				.uri(BASE_PATH)
				.bodyValue(idTest)
				.exchange()
				.expectStatus()
				.is2xxSuccessful()
				.expectBody(String.class)
				.returnResult()
				.getResponseBody();

		// then
		OrderProcess orderProcess = webTestClient
				.get()
				.uri(BASE_PATH+"/{id}", id)
				.exchange()
				.expectStatus()
				.is2xxSuccessful()
				.expectBody(OrderProcess.class)
				.returnResult()
				.getResponseBody();

		Assertions.assertEquals(orderProcess.getIdProduct(), idTest);
		Assertions.assertEquals(orderProcess.getId(), id);
		Assertions.assertFalse(orderProcess.isWasNotified());
	}

	@Test
	public void execute_order_process_with_internal_error_because_productId_dont_exist() {

		// given
		String idTest = "12345";

		// when
		StatusAssertions statusAssertions = webTestClient
				.post()
				.uri(BASE_PATH)
				.bodyValue(idTest)
				.exchange()
				.expectStatus();

		// then
		statusAssertions.is5xxServerError();
	}
}
