package com.academia.ginastica.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.academia.ginastica.domain.AvaliacaoFisica;

@SuppressWarnings("unchecked")
public interface IAvaliacaoFisicaDAO extends CrudRepository<AvaliacaoFisica, Long>{
	AvaliacaoFisica findById(long id);
	List<AvaliacaoFisica> findAll();
	List<AvaliacaoFisica> findByDataDaAvaliacao(String dataDaAvaliacao);
	AvaliacaoFisica save(AvaliacaoFisica avaliacao);
	void deleteById(Long id);
}
