package com.academia.ginastica.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.academia.ginastica.domain.AvaliacaoFisica;
import com.academia.ginastica.service.spec.IAlunoService;
import com.academia.ginastica.service.spec.IAvaliacaoFisicaService;
import com.academia.ginastica.domain.Aluno;

@RestController
public class AvaliacaoController {
	@Autowired
	private IAvaliacaoFisicaService service;
	
	@Autowired
	private IAlunoService alunoService;
	
	
	private boolean isJSONValid(String jsonInString) {
		try {
			return new ObjectMapper().readTree(jsonInString) != null;
		}catch(IOException e) {
			return false;
		}
	}
	
	private void parse(AvaliacaoFisica avaliacao, JSONObject json) {
		Object id = json.get("id");
		Object alunoId = json.get("alunoId");
		if(id != null) {
			if (id instanceof Integer) {
				avaliacao.setId( ( (Integer) id ).longValue() );
			}
			else {
				avaliacao.setId((Long) id);
			}
		}
		avaliacao.setPeso((Double) json.get("peso"));
		avaliacao.setAltura((Double) json.get("altura"));
		avaliacao.setDataDaAvalicao((String) json.get("dataDaAvaliacao"));
		
		if (alunoId != null) {
			Long alunoIdLong = (alunoId instanceof Integer) ? ((Integer) alunoId).longValue() : (Long) alunoId;
			Aluno aluno = alunoService.buscarPorId(alunoIdLong);
			avaliacao.setAluno(aluno);
		}
	}
	
	@GetMapping(path = "/avaliacoes")
	public ResponseEntity <List<AvaliacaoFisica>> lista(){
		List<AvaliacaoFisica> lista = service.buscarTodos();
		if(lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping(path = "/avaliacoes/aluno/{id}")
	public ResponseEntity < List<AvaliacaoFisica>> listaAluno(@PathVariable Long id){
		List<AvaliacaoFisica> list = service.buscarTodos();
		if(list.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		for(int i = 0; i < list.size(); i++) {
			if (list.get(i).getID() != id)
				list.remove(i);
				
		}
		return ResponseEntity.ok(list);
	}
	@GetMapping(path = "/avaliacoes/data/{id}")
	public ResponseEntity < List<AvaliacaoFisica>> listadata(@PathVariable String data){
		List<AvaliacaoFisica> list = service.buscarPorDataDaAvaliacao(data);
		if(list.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(list);
	}
	@PostMapping(path = "/avaliacoes")
	@ResponseBody
	public ResponseEntity<AvaliacaoFisica> cria(@RequestBody JSONObject json){
		try {
			if(isJSONValid(json.toJSONString())) {
				AvaliacaoFisica avalicao = new AvaliacaoFisica();
				parse(avalicao, json);
				service.salvar(avalicao);
				return ResponseEntity.ok(avalicao);
			}else {
				return ResponseEntity.badRequest().body(null);
				}
			} catch(Exception e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
			}
	}
	@PutMapping(path  = "/avaliacoes/{id}")
	public ResponseEntity<AvaliacaoFisica> atualiza(@PathVariable("id") long id, @RequestBody JSONObject json){
		try {
			if(isJSONValid(json.toJSONString())) {
				AvaliacaoFisica avaliacao = service.buscarPorId(id);
				if(avaliacao == null) {
					return ResponseEntity.notFound().build();
				}
				else {
					parse(avaliacao, json);
					service.salvar(avaliacao);
					return ResponseEntity.ok(avaliacao);
				}
			}else {
				return ResponseEntity.badRequest().body(null);
			}
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
	}
	
	@DeleteMapping(path = "/avaliacoes/{id}")
	public ResponseEntity<AvaliacaoFisica> exclui(@PathVariable("id") long id, @RequestBody JSONObject json){
		AvaliacaoFisica avaliacao = service.buscarPorId(id);
		if(avaliacao == null) {
			return ResponseEntity.notFound().build();
		}
		else {
			service.excluir(id);
			return ResponseEntity.noContent().build();
		}
	}
	
}
