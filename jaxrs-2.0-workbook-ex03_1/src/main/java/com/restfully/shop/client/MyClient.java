package com.restfully.shop.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class MyClient {

	public static void main(String[] args) {
		Client client = ClientBuilder.newClient();

		try {
            // test
			System.out.println("*** Create a new Customer ***");
			String xml = "<customer>" + "<first-name>kkkkk</first-name>" + "<last-name>dddd</last-name>"
					+ "<street>256 Clarendon Street</street>" + "<city>aaa</city>" + "<state>MA</state>"
					+ "<zip>02115</zip>" + "<country>USA</country>" + "</customer>";

			Response response = client.target("http://localhost:8080/services/customers")
					.request(MediaType.APPLICATION_XML)
					.post(Entity.xml(xml));
			if (response.getStatus() != 201) {
				throw new RuntimeException("Failed to create");
			}
			
			String location = response.getLocation().toString();
			System.out.println("Location: " + location);
			response.close();

		} finally {
			client.close();
		}
	}
}
