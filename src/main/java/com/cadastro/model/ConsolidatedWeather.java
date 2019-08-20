package com.cadastro.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;

@Builder
@lombok.Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConsolidatedWeather {
	
	private List<DataConslidateWeather> consolidated_weather;
	
}
