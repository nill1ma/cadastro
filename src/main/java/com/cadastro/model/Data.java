package com.cadastro.model;

import lombok.Builder;

@Builder
@lombok.Data
public class Data {

	private String ipv4;
	private String continent_name;
	private String country_name;
	private String subdivision_1_name;
	private String subdivision_2_name;
	private String city_name;
	private String latitude;
	private String longitude;
}
