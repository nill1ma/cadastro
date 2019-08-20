package com.cadastro.controller;

import java.text.Normalizer;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cadastro.model.Cadastro;
import com.cadastro.model.Clima;
import com.cadastro.model.ConsolidatedWeather;
import com.cadastro.model.DadosLocalizacao;
import com.cadastro.model.InfoCadastro;
import com.cadastro.service.CadastroService;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class CadastroController {

	@Autowired
	private CadastroService service;

	@PostMapping(value = "/save", produces = "application/json", consumes = "application/json")
	public ResponseEntity<?> save(@RequestBody Cadastro cad, @RequestBody InfoCadastro info) throws Exception {

		Cadastro c = Cadastro.builder().nome(cad.getNome()).idade(cad.getIdade()).info(info).build();

		ResponseEntity<Cadastro> resp = new ResponseEntity<Cadastro>(c, HttpStatus.CREATED);
		resp = service.save(c);
		return resp;
	}

	@PutMapping(value = "/update", produces = "application/json", consumes = "application/json")
	public ResponseEntity<?> update(@RequestBody Cadastro cadastro) throws Exception {
		ResponseEntity<Cadastro> resp = new ResponseEntity<Cadastro>(cadastro, HttpStatus.OK);
		resp = service.save(cadastro);
		return resp;
	}

	@GetMapping(value = "/", produces = "application/json")
	public ResponseEntity<List<Cadastro>> findAll() {
		ResponseEntity<List<Cadastro>> resp = new ResponseEntity<>(HttpStatus.OK);
		resp = service.findAll();
		return resp;
	}

	@GetMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
	public ResponseEntity<Cadastro> findById(@PathVariable("id") Long id) throws Exception {
		ResponseEntity<Cadastro> resp = new ResponseEntity<>(HttpStatus.OK);
		resp = service.findById(id);
		return resp;
	}

	@GetMapping(value = "/{id}", produces = "application/json")
	public void delete(@PathVariable("id") Long id) throws Exception {
		service.delete(id);
	}

	@GetMapping(value = "/ipVigilante", produces = MediaType.APPLICATION_JSON_VALUE)
	public DadosLocalizacao ipVigilante() {
		RestTemplate rest = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> requestEntity = new HttpEntity<>(headers);

		DadosLocalizacao d = rest.exchange("https://ipvigilante.com/json/", HttpMethod.GET, requestEntity,
				new ParameterizedTypeReference<DadosLocalizacao>() {
				}).getBody();

		return d;
	}

	@GetMapping(value = "/clima", produces = MediaType.APPLICATION_JSON_VALUE)
	public String clima(@RequestParam("latitude") String latitude, @RequestParam("longitude") String longitude) {
		RestTemplate rest = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> requestEntity = new HttpEntity<>(headers);

		List<Clima> s = rest.exchange("https://www.metaweather.com/api/location/search/?lattlong=" + latitude + "," + longitude,
						HttpMethod.GET, requestEntity, new ParameterizedTypeReference<List<Clima>>() {
						})
				.getBody();
		String city = ipVigilante().getData().getSubdivision_1_name();
		
		String woeid = "";
		
		for (Clima clima : s) {
			String title = Normalizer.normalize(clima.getTitle(), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
			if (title.equals(city))
				woeid = clima.getWoeid();
		}

		return woeid;
	}

	@GetMapping(value = "/metaWeather", produces = MediaType.APPLICATION_JSON_VALUE)
	public ConsolidatedWeather metaWeather(@RequestParam("woeid") String woeid) {
		RestTemplate rest = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> requestEntity = new HttpEntity<>(headers);

		ConsolidatedWeather cl = rest.exchange("https://www.metaweather.com/api/location/" + woeid, HttpMethod.GET,
				requestEntity, new ParameterizedTypeReference<ConsolidatedWeather>() {
				}).getBody();

		return cl;
	}

	public String city(String city, List<Clima> titles) {

		for (Clima clima : titles) {
			String title = Normalizer.normalize(clima.getTitle(), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
			if (title.equals(city))
				return title;
		}

		return "";
	}

	@GetMapping(value = "/infoCadastro", produces = MediaType.APPLICATION_JSON_VALUE)
	public InfoCadastro infoCadastro() {
		
		
		
		String woeid = clima(ipVigilante().getData().getLatitude(), ipVigilante().getData().getLongitude());
		metaWeather(woeid);
		

		InfoCadastro info = InfoCadastro.builder()
				.latitudeAndLongitude(
						ipVigilante().getData().getLatitude() + "" + ipVigilante().getData().getLongitude())
				.cidade(ipVigilante().getData().getSubdivision_1_name())
				.tempMaxima(metaWeather(woeid).getConsolidated_weather().get(0).getMax_temp())
				.tempMinima(metaWeather(woeid).getConsolidated_weather().get(0).getMin_temp())
				.descricaoTempo(metaWeather(woeid).getConsolidated_weather().get(0).getWeather_state_name())
				.build();

		return info;
	}

}
