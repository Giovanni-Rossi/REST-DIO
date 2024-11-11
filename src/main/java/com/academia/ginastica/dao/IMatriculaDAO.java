package com.academia.ginastica.dao;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.academia.ginastica.domain.Matricula;

@SuppressWarnings("unchecked")
public interface IMatriculaDAO extends CrudRepository<Matricula, Long>{
	Matricula findById(long id);
	List<Matricula> findAll();
	List<Matricula> findByDataDaMatricula(String data);
	Matricula save(Matricula matricula);
	void deleteById(Long id);
}
