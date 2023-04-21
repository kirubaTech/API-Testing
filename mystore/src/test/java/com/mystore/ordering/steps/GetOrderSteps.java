package com.mystore.ordering.steps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest
public class GetOrderSteps {

	public static final String OrderId = null;
	String url;
	List<Map<String, String>> data = new ArrayList<>();
	Map<String, Integer> result = new HashMap<>();

	// executeGet implementation
	@Given("API end point is given as {string}")
	public void setEndPoint(String url) {
		this.url = url;
	}

	@And("OrderId and expected response are given as below")
	public void setOderIds(DataTable table) {
		data = table.asMaps(String.class, String.class);
	}

	@When("Order details retrieved successfully")
	public void getOrderDetails() {

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		for (Map<String, String> map : data) {
			int status = 0;
			String orderId = map.get("id").trim();
			try {
				ResponseEntity<String> response = restTemplate.exchange(url + orderId, HttpMethod.GET, entity,
						String.class);
				status = response.getStatusCodeValue();
			} catch (HttpClientErrorException e) {
				if (e.getMessage().toString().contains("404")) {
					status = 404;
				}
			}
			System.out.println("orderid-->"+orderId+"->"+status);
			result.put(orderId, status);

		}

	}

	@Then("Validate the response")
	public void validateOrderDetails() {
		for (Map<String, String> map : data) {
			int status = result.get(map.get("id"));
			Assertions.assertEquals(status, Integer.parseInt(map.get("http_status_code").trim()));
		}

	}
}
