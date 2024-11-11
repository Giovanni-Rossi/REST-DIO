package com.academia.ginastica.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@SuppressWarnings("serial")
@Entity
@Table(name = "Matricula")
public class Matricula extends AbstractEntity<Long>{
	@NotNull
	@OneToOne
	@JoinColumn(name = "aluno_id")
	private Aluno aluno;
	
	@NotNull
	@Column(nullable =false)
	private String dataDaMatricula;
	
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	
	public Aluno getAluno() {
		return this.aluno;
	}
	
	public String getDataDaMAtricula() {
		return this.dataDaMatricula;
	}
	
	public void setDataDaMatricula(String string) {
		this.dataDaMatricula = string;
	}
}
