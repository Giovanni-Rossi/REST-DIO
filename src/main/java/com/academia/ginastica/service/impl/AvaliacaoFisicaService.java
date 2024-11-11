package com.academia.ginastica.service.impl;

import com.academia.ginastica.service.spec.IAlunoService;
import com.academia.ginastica.service.spec.IAvaliacaoFisicaService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.academia.ginastica.domain.AvaliacaoFisica;
import com.academia.ginastica.dao.IAvaliacaoFisicaDAO;

@Service
@Transactional(readOnly = false)
public class AvaliacaoFisicaService implements IAvaliacaoFisicaService{
	@Autowired
	private IAvaliacaoFisicaDAO dao;
	
	@Transactional(readOnly = true)
	public AvaliacaoFisica buscarPorId(Long id) {
		return dao.findById(id.longValue());
	}

	@Transactional(readOnly = true)
	public List<AvaliacaoFisica> buscarTodos() {
		return dao.findAll();
	}

	@Transactional(readOnly = true)
	public List<AvaliacaoFisica> buscarPorDataDaAvaliacao(String data) {
		return dao.findByDataDaAvaliacao(data);
	}


	@Transactional/*(readOnly = true)*/
	public AvaliacaoFisica salvar(AvaliacaoFisica avaliacao) {
		return dao.save(avaliacao);
	}

	@Transactional(readOnly = true)
	public void excluir(Long id) {
		dao.deleteById(id);
		
	}


}
