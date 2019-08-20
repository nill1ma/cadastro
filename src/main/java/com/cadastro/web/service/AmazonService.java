package com.cadastro.web.service;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class AmazonService {

	private static final String URL = "http://checkip.amazonaws.com";

	public String getIpPublic() throws Exception {

		String ipPublico;
		
		try {

			ipPublico = new RestTemplate().getForObject(URL, String.class);

		} catch (RestClientException e) {

			e.printStackTrace();

			throw new RestClientException(
					"Falha ao tentar se comunicar com Serviço de IP da Amazon. Favor tentar mais tarde.");

		}

		return ipPublico;
	}
}