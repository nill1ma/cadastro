package com.cadastro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cadastro.model.Cadastro;

@Repository
public interface CadastroRepository  extends JpaRepository<Cadastro, Long>{

}
