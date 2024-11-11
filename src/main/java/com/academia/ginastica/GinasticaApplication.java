package com.academia.ginastica;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.academia.ginastica.dao.IAlunoDAO;
import com.academia.ginastica.dao.IAvaliacaoFisicaDAO;
import com.academia.ginastica.domain.Aluno;
import com.academia.ginastica.domain.AvaliacaoFisica;

@SpringBootApplication
public class GinasticaApplication {

	public static void main(String[] args) {
		SpringApplication.run(GinasticaApplication.class, args);
	}
	@Bean
	public CommandLineRunner demo(IAlunoDAO aluno, IAvaliacaoFisicaDAO avaliacao) {
		return (args) -> {
			/*Aluno aluno1 = new Aluno();
			
			aluno1.setBairro("JABAQUARA");
			aluno1.setCPF("152.132.547-54");
			aluno1.setDataDeNascimento("2002-06-20");
			aluno1.setEnabled(true);
			aluno1.setNome("PEDRO PEDROSO");
			aluno.save(aluno1);
			
			AvaliacaoFisica avaliacao1 = new AvaliacaoFisica();
			
			avaliacao1.setAltura(182.5);
			avaliacao1.setDataDaAvalicao("10-06-2022");
			avaliacao1.setPeso(95.7);
			avaliacao1.setAluno(aluno1);
			avaliacao.save(avaliacao1);*/
			};
		};

}
