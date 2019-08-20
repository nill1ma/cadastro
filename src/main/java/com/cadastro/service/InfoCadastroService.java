package com.cadastro.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.cadastro.repository.InfoCadastroRepository;
import com.cadastro.web.service.AmazonService;
import com.cadastro.web.service.IpVigilantService;
import com.cadastro.web.service.MetaweatherService;

public class InfoCadastroService {
	
	@Autowired
	private InfoCadastroRepository repository;

	@Autowired 
	private IpVigilantService ipVigilant;

	@Autowired
	private MetaweatherService metaWeather;

	@Autowired
	private AmazonService amazon;

//	public InfoCadastro gerarInformacoesCliente(HttpServletRequest request) throws Exception{
//
//		String ipRequest = getIp(request);
//
//		RestTemplate template = new RestTemplate();
//
////		String latLongWithComma = ipVigilant.getLatitudeLongitude(ipRequest, template);
//
//		int woeid = metaWeather.getWoeid(latLongWithComma, template);
//
//		WeatherClientDTO weatherInfo = metaWeather.getInfoClima(woeid, template);
//
//		return repository.save(createWeatherInfo(weatherInfo, latLongWithComma));
//
//	}

	public String getIp(HttpServletRequest request) throws Exception {

		String ipRequest = (request.getHeader("X-FORWARDED-FOR") == null ? request.getRemoteAddr()

				: request.getHeader("X-FORWARDED-FOR"));

		if (isPrivateIp(ipRequest)) {
			ipRequest = amazon.getIpPublic();
		}
		
		return ipRequest;
	}

	private static boolean isPrivateIp(String ipRequest) {

		if (ipRequest.startsWith("0:") || ipRequest.startsWith("127")) {
			return true;
		}

		if (ipRequest.startsWith("10.")) {
			return true;
		} else {

			if (ipRequest.startsWith("172.")) {

				int subIp = Integer.parseInt(ipRequest.substring(4, 5));
				
				if (subIp > 15 || subIp < 32)
					return true;
				else
					return false;
			} else {

				if (ipRequest.startsWith("192.168"))
					return true;
				else
					return false;

			}

		}

	}

//	private InfoCadastro createWeatherInfo(WeatherClientDTO weatherInfo, String latLong) {
//
//		InfoCadastro info = InfoCadastro.builder()
//
//				.latitudeAndLongitude(latLong)
//
//				.tempMaxima(weatherInfo.getConsolidated_weather().get(0).getMax_temp())
//
//				.tempMinima(weatherInfo.getConsolidated_weather().get(0).getMin_temp())
//
//				.descricaoTempo(weatherInfo.getConsolidated_weather().get(0).getWeather_state_name())
//
//				.build();
//
//		
//
//		return info;
//
//	}
}
