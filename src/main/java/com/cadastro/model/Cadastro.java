package com.cadastro.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Cadastro implements Serializable{

	private static final long serialVersionUID = 2592267507721490173L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private int idade;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_info", referencedColumnName = "id", nullable = false)
	private InfoCadastro info;

}
