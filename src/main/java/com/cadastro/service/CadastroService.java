package com.cadastro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cadastro.model.Cadastro;
import com.cadastro.repository.CadastroRepository;

@Service
public class CadastroService {
	
	@Autowired
	private CadastroRepository cadastroRepository;

	public ResponseEntity<Cadastro> save(Cadastro cad) throws Exception {
	
		validaCamposCadastro(cad);
		Cadastro cadastro = cadastroRepository.saveAndFlush(cad);
		ResponseEntity<Cadastro> resp = new ResponseEntity<Cadastro>(cadastro, HttpStatus.CREATED);

		return resp;
	}
	
	public ResponseEntity<Cadastro> update(Cadastro cad) throws Exception {

		validaCamposCadastro(cad);
		Cadastro cadastro = cadastroRepository.save(cad);
		ResponseEntity<Cadastro> resp = new ResponseEntity<Cadastro>(cadastro, HttpStatus.OK);

		return resp;
	}

	public void delete(Long id) throws Exception{
		
		if(id == null) {
			throw new NumberFormatException("Parametro inválido ou não preenchido.");
		}
		
		cadastroRepository.deleteById(id);
	}

	public ResponseEntity<Cadastro> findById(Long id) throws Exception {

		if(id == null) {
			throw new NumberFormatException("Parametro inválido ou não preenchido.");
		}
		
		Cadastro Cadastro = cadastroRepository.findById(id).get();

		if(Cadastro == null) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok().body(Cadastro);
	}

	public ResponseEntity<List<Cadastro>> findAll() {
		List<Cadastro> cadastro = cadastroRepository.findAll();
		ResponseEntity<List<Cadastro>> resp = new ResponseEntity<List<Cadastro>>(cadastro,HttpStatus.OK);
//		resp = cadastroRepository.findAll();
		return resp;
		
		

//		if(lstCadastro.isEmpty()) {
//			return ResponseEntity.noContent().build();
//		}
//		
//		return ResponseEntity.ok().body(lstCadastro);
	}

	private void validaCamposCadastro(Cadastro cadastro) throws Exception {

		if (cadastro.getNome().isEmpty() || cadastro.getIdade() >=0 ) {
			throw new Exception("Campos não preenchidos ou preenchidos incorretamente.");
		}

	}

}