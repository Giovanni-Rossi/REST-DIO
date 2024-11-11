package com.academia.ginastica.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.academia.ginastica.domain.Aluno;

@SuppressWarnings("unchecked")
public interface IAlunoDAO  extends CrudRepository <Aluno, Long>{
	Aluno findById(long id);
	List<Aluno> findAll();
	List<Aluno> findByNome(String nome);
	Aluno findByCPF(String CPF);
	List<Aluno> findByBairro(String bairro);
	Aluno save(Aluno aluno);
	void deleteById(Long id);
}
