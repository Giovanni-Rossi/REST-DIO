package com.academia.ginastica.service.spec;

import java.util.List;
import com.academia.ginastica.domain.*;

public interface IAvaliacaoFisicaService {
	AvaliacaoFisica buscarPorId(Long id);
	List<AvaliacaoFisica> buscarTodos();
	List<AvaliacaoFisica> buscarPorDataDaAvaliacao(String data);
	AvaliacaoFisica salvar(AvaliacaoFisica avaliacao);
	void excluir(Long id);
}
