package com.academia.ginastica.service.spec;


import java.util.List;

import com.academia.ginastica.domain.Matricula;

public interface IMatriculaService {
	Matricula buscarPorId(Long id);
	List<Matricula> buscarTodos();
	List<Matricula> buscarPorDataDaMatricula(String data);
	Matricula salvar(Matricula matricula);
	void excluir(Long id);
}
