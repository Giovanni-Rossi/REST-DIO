package com.academia.ginastica.domain;

import jakarta.persistence.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@SuppressWarnings("serial")
@Entity
@Table(name = "Aluno")
public class Aluno extends AbstractEntity <Long>{
	@NotBlank
	@Column(nullable = false, length = 30)
	private String nome;
	
	@NotBlank
	@Column(nullable = false, length = 30)
	private String CPF;
	
	@NotBlank
	@Column(nullable = false, length = 30)
	private String bairro;
	
	@NotBlank
	@Column(nullable = false)
	private String dataDeNascimento;
	
	@OneToMany(mappedBy = "aluno", cascade = CascadeType.REMOVE)
	@JsonManagedReference
	private List<AvaliacaoFisica> avaliacoesFisica;
	
	@Column(nullable=false)
    private boolean enabled;
	
	public String getNome() {
		return this.nome;
	}
	
	public void setNome( String nome) {
		this.nome = nome;
	}
	
	public String getCPF() {
		return this.CPF;
	}
	public void setCPF(String CPF) {
		this.CPF = CPF;
	}
	public String getBairro() {
		return this.bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	
	public String getDataDeNascimento() {
		return this.dataDeNascimento;
	}
	
	public void setDataDeNascimento(String string) {
		this.dataDeNascimento = string;
	}
	
	public List<AvaliacaoFisica> getAvaliacoes(){
		return avaliacoesFisica;
	}
	
	public void setAvaliacaoFisica(List<AvaliacaoFisica> avaliacao) {
		this.avaliacoesFisica = avaliacao;
	}
	
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
