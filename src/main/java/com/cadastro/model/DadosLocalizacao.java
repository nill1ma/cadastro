package com.cadastro.model;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DadosLocalizacao implements Serializable{
	
	private static final long serialVersionUID = -2802885815845272307L;

	private Long id;
	private String status;
	private com.cadastro.model.Data data;
	
}
