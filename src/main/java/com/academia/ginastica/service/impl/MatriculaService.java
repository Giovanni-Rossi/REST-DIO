package com.academia.ginastica.service.impl;

import java.util.List;

import com.academia.ginastica.domain.Matricula;
import com.academia.ginastica.service.spec.IMatriculaService;
import com.academia.ginastica.dao.IMatriculaDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = false)
public class MatriculaService implements IMatriculaService{
	@Autowired
	private IMatriculaDAO dao;
	
	@Transactional(readOnly = true)
	public Matricula buscarPorId(Long id) {
		
		return dao.findById(id.longValue());
	}

	@Transactional(readOnly =true)
	public List<Matricula> buscarTodos() {
		return dao.findAll();
	}

	@Transactional(readOnly = true)
	public List<Matricula> buscarPorDataDaMatricula(String data) {
		return dao.findByDataDaMatricula(data);
	}

	@Transactional
	public Matricula salvar(Matricula matricula) {
		return dao.save(matricula);
	}

	@Transactional
	public void excluir(Long id) {
		dao.deleteById(id);	
	}

}
