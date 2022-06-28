package com.nathanael.florcreation;

import com.nathanael.florcreation.orders.repository.OrderAmountProj;
import okhttp3.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class FlorcreationApplicationTests {

	@Test
	void contextLoads() throws IOException {
		String paymentType = "GCASH";
		String url = "http://localhost:3000/orders/payment/sucess";
		String urlFiled = "http://localhost:3000/orders/payment/failed";
		double amountInCents = 2320.75 * 100;
		int finalAmount = (int) amountInCents;
		OkHttpClient client = new OkHttpClient();

		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(mediaType,
				"{\"data\":{\"attributes\":{\"amount\":" + finalAmount +
						",\"redirect\":{\"success\":\"" + url +
						"\",\"failed\":\"" + urlFiled +
						"\"},\"type\":\"" + paymentType.toLowerCase() +
						"\",\"currency\":\"PHP\"}}}");

		Request request = new Request.Builder()
				.url("https://api.paymongo.com/v1/sources")
				.post(body)
				.addHeader("Accept", "application/json")
				.addHeader("Content-Type", "application/json")
				.addHeader("Authorization", "Basic c2tfdGVzdF9hVzZvcjFpaXlYYjdXN3Z4aGhycE1UNzM6c2tfdGVzdF9hVzZvcjFpaXlYYjdXN3Z4aGhycE1UNzM=")
				.build();

		ResponseBody response = client.newCall(request).execute().body();

		assert response != null;
		System.out.println(response);
		Assertions.assertNotNull(response);
	}

}
