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

import com.academia.ginastica.domain.Aluno;
import com.academia.ginastica.service.spec.IAlunoService;

@RestController
public class AlunoController {
	@Autowired
	private IAlunoService service;
	
	private boolean isJSONValid(String jsonInString) {
		try {
			return new ObjectMapper().readTree(jsonInString) != null;
		}catch(IOException e) {
			return false;
		}
	}
	
	public void parse(Aluno aluno, JSONObject json) {
		Object id = json.get("id");
		if(id != null) {
			if (id instanceof Integer) {
				aluno.setId( ( (Integer) id ).longValue() );
			}
			else {
				aluno.setId(((Long) id));
			}
		}
		
		aluno.setNome((String) json.get("nome"));
		aluno.setCPF((String) json.get("cpf"));
		aluno.setDataDeNascimento((String) json.get("dataDeNascimento"));
		aluno.setBairro((String) json.get("bairro"));
	}
	@GetMapping(path = "/alunos")
	public ResponseEntity <List<Aluno>> lista(){
		List <Aluno> lista = service.buscarTodos();
		if(lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lista);
	}
	@GetMapping(path = "/alunos/{bairro}")
	public ResponseEntity <List<Aluno>> listaBairro(@PathVariable String bairro){
		List<Aluno> lista = service.buscarPorBairro(bairro);
		if (lista.isEmpty())
			return ResponseEntity.notFound().build();
		else
			return ResponseEntity.ok(lista);
	}
	@GetMapping(path = "/aluno/{cpf}")
	public ResponseEntity <Aluno> buscaalunoCPF(@PathVariable String cpf){
		Aluno aluno = new Aluno();
		if(service.buscarPorCPF(cpf) == null)
			return ResponseEntity.notFound().build();
		else
			return ResponseEntity.ok(aluno);
	}
	
	@PostMapping(path = "/alunos")
	@ResponseBody
	public ResponseEntity<Aluno> cria(@RequestBody JSONObject json) {
		try {
			if(isJSONValid(json.toJSONString())) {
				Aluno aluno = new Aluno();
				parse(aluno, json);
				service.salvar(aluno);
				return ResponseEntity.ok(aluno);
			}else {
				return ResponseEntity.badRequest().body(null);
			}
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
	}
	
	@PutMapping(path = "/alunos/{id}")
	public ResponseEntity<Aluno> atualiza(@PathVariable("id") long id, @RequestBody JSONObject json){
		try {
			if(isJSONValid(json.toJSONString())) {
				Aluno aluno = service.buscarPorId(id);
				if(aluno == null) {
					return ResponseEntity.notFound().build();
				}
				else {
					parse(aluno, json);
					service.salvar(aluno);
					return ResponseEntity.ok(aluno);
				}
			}else {
				return ResponseEntity.badRequest().body(null);
			}
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
	}
	
	@DeleteMapping(path = "/aluno/{id}")
	public ResponseEntity<Boolean> remove(@PathVariable("id") long id){
		Aluno aluno = service.buscarPorId(id);
		if(aluno == null)
			return ResponseEntity.notFound().build();
		else {
			service.excluir(id);
			return ResponseEntity.noContent().build();
		}
	}
	
}
