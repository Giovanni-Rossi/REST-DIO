package com.academia.ginastica.service.spec;

import java.util.List;
import com.academia.ginastica.domain.*;

public interface IAlunoService {
	Aluno buscarPorId(Long id);
	List<Aluno> buscarTodos();
	List<Aluno> buscarPorNome(String nome);
	Aluno buscarPorCPF(String CPF);
	List<Aluno> buscarPorBairro(String bairro);
	void salvar(Aluno aluno);
	void excluir(Long id);
	boolean alunoTemAvaliacoes(Long id);
}
