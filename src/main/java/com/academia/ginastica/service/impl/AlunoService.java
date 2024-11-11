package com.academia.ginastica.service.impl;

import com.academia.ginastica.service.spec.IAlunoService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.academia.ginastica.domain.Aluno;
import com.academia.ginastica.dao.IAlunoDAO;

@Service
@Transactional(readOnly = false)

public class AlunoService implements IAlunoService{
	@Autowired
	private IAlunoDAO dao;
	
	@Transactional(readOnly = true)
	public Aluno buscarPorId(Long id) {
		return dao.findById(id.longValue());
	}

	@Transactional(readOnly= true)
	public List<Aluno> buscarTodos() {
		return dao.findAll();
	}

	@Transactional(readOnly= true)
	public List<Aluno> buscarPorNome(String nome) {
		return dao.findByNome(nome);
	}

	@Transactional(readOnly = true)
	public Aluno buscarPorCPF(String CPF) {
		return dao.findByCPF(CPF);
	}

	@Transactional
	public List<Aluno> buscarPorBairro(String bairro) {
		return dao.findByBairro(bairro);
	}

	public void salvar(Aluno aluno) {
		dao.save(aluno);
		
	}

	public void excluir(Long id) {
		dao.deleteById(id.longValue());		
	}

	public boolean alunoTemAvaliacoes(Long id) {
		Aluno aluno = new Aluno();
		aluno =  dao.findById(id.longValue());
		return !aluno.getAvaliacoes().isEmpty();
	}

}
