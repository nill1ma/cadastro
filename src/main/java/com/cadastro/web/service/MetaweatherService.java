package com.cadastro.web.service;

import org.springframework.stereotype.Component;

@Component
public class MetaweatherService {
	private static final String URL_LOCATION_SEARCH = "https://www.metaweather.com/api/location/search/?lattlong=";
	private static final String URL_WEATHER_SEARCH = "https://www.metaweather.com/api/location/";
	
//	public int getWoeid(String latLong, RestTemplate template) throws Exception {
//		
//		LocationSearchDTO[] locationSearch;
//		
//		try{
//			locationSearch = template.getForObject(URL_LOCATION_SEARCH 
//				+ latLong, LocationSearchDTO[].class);
//		}catch(RestClientException e) {
//			e.printStackTrace();
//			throw new Exception("Falha ao tentar se comunicar com Serviço de Clima "
//					+ "MetaWeather. Favor tentar mais tarde.");
//		}
//		
//		return locationSearch[0].getWoeid();
//	}
//	
//	public WeatherClientDTO getInfoClima(int woeid, RestTemplate template) throws Exception {
//		WeatherClientDTO weatherInfo;
//		
//		try{
//			weatherInfo = template.getForObject(URL_WEATHER_SEARCH 
//				+ woeid, WeatherClientDTO.class);
//		}catch(RestClientException e) {
//			e.printStackTrace();
//			throw new Exception("Falha ao tentar se comunicar com Serviço de Clima "
//					+ "MetaWeather. Favor tentar mais tarde.");
//		}
//		return weatherInfo;
//	}
}
