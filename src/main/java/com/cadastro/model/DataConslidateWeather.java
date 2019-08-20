package com.cadastro.model;

import lombok.Builder;

@Builder
@lombok.Data
public class DataConslidateWeather {
	
	private String weather_state_name;
	private double min_temp;
	private double max_temp;
}
