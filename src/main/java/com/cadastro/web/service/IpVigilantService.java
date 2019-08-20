package com.cadastro.web.service;

import org.springframework.stereotype.Component;

@Component
public class IpVigilantService {

	private static final String URL_JSON = "https://ipvigilante.com/json/";
	
	@SuppressWarnings("unused")
	private static final String URL_CSV = "https://ipvigilante.com/csv/";
	
//	private static final Logger logger = LogManager.getLogger(IpVigilantService.class);
	/** 
	 * Busca Localização geografica (Latitude e Longitude)
	 * do ip da Origem da Requisição
	 * 
	 * @param ipOrigem
	 * @param template
	 * @return String com Latitude e Longitude separados por virgula
	 */
//	public String getLatitudeLongitude(String ipOrigem, RestTemplate template) throws Exception {
//		
//		LocationClientDTO localizacao = null;
//		
//		try {
//			localizacao = template.getForObject(URL_JSON + ipOrigem,
//					LocationClientDTO.class);
//		}catch(RestClientException e) {
////			logger.error("Falha ao tentar se comunicar com Serviço de Geolocalização "
////					+ "IP Vigilant. Favor tentar mais tarde.", e);
//			throw new Exception("Falha ao tentar se comunicar com Serviço de Geolocalização "
//					+ "IP Vigilant. Favor tentar mais tarde.");
//		}
////
////		String latLongWithComma = localizacao.getData().getLatitude() 
////				+ "," 
////				+ localizacao.getData().getLongitude();
//		
//		return latLongWithComma;
//
//		
//		
//	}
	
}
