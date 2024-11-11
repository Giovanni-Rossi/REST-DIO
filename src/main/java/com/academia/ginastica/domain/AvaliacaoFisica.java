package com.academia.ginastica.domain;
import java.time.LocalDateTime;

import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@SuppressWarnings("serial")
@Entity
@Table(name="AvaliacaoFisica")
public class AvaliacaoFisica extends AbstractEntity<Long>{
	@NotBlank
	@Column(nullable = false)
	private String dataDaAvaliacao;
	
	@ManyToOne
	@JoinColumn(name="aluno_id")
	@JsonBackReference
	private Aluno aluno;
	
	@NotNull
	@Column(nullable = false)
	private double peso;
	
	@NotNull
	@Column(nullable = false)
	private double altura;
	
	public String getaDataDaAvalicao() {
		return this.dataDaAvaliacao;
	}
	
	public void setDataDaAvalicao(String dataDaAvaliacao) {
		this.dataDaAvaliacao = dataDaAvaliacao;
	}
	
	public Aluno getAluno() {
		return this.aluno; 
	}
	
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	
	public double getPeso() {
		return this.peso;
	}
	
	public void setPeso(double peso) {
		this.peso = peso;
	}
	
	public double getAltura() {
		return this.altura;
	}
	
	public void setAltura(double altura) {
		this.altura = altura;
	}
	
	
}
