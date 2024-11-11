package com.academia.ginastica.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RestController;

import com.academia.ginastica.domain.Aluno;
import com.academia.ginastica.domain.Matricula;
import com.academia.ginastica.service.spec.IAlunoService;
import com.academia.ginastica.service.spec.IMatriculaService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class MatriculaController {
	@Autowired
	private IMatriculaService service;
	
	@Autowired
	private IAlunoService alunoService;
	
	private boolean isJSONValid(String jsonInString) {
		try {
			return new ObjectMapper().readTree(jsonInString) != null;
		}catch(IOException e) {
			return false;
		}
	}
	
	private void parse(Matricula matricula, JSONObject json) {
		Object id = json.get("id");
		Object alunoId = json.get("alunoId");
		if(id != null) {
			if (id instanceof Integer) {
				matricula.setId( ( (Integer) id).longValue() );
			}
			else {
				matricula.setId((Long) id);
			}
		}
		matricula.setDataDaMatricula((String) json.get("dataDaMatricula"));
		if(alunoId != null) {
			Long alunoIdlong = (alunoId instanceof Integer) ? ((Integer) alunoId).longValue() : (Long) alunoId;
			Aluno aluno = alunoService.buscarPorId(alunoIdlong);
			matricula.setAluno(aluno);
		}
	}
	
	@GetMapping(path = "/matriculas")
	public ResponseEntity <List<Matricula>> lista(){
		List<Matricula> lista = service.buscarTodos();
		if(lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping(path = "/matriculas/{id}")
	public ResponseEntity<Matricula> buscarPorAluno(@PathVariable("id") Long id) {
		List<Matricula> lista = service.buscarTodos();
		Matricula matriculaDoAluno = new Matricula();
		for(Matricula matricula : lista) {
			if (matricula.getAluno().getID() == id.longValue())
				matriculaDoAluno = matricula;
				return ResponseEntity.ok(matriculaDoAluno);
		}
		return ResponseEntity.notFound().build();	
	}
	
	@GetMapping(path = "/matriculas/{data}")
	public ResponseEntity<List<Matricula>> buscaPorData(@PathVariable("data") String data){
		List<Matricula> lista = service.buscarPorDataDaMatricula(data);
		if(lista.isEmpty())
			return ResponseEntity.notFound().build();
		else
			return ResponseEntity.ok(lista);
	}
	
	@PostMapping(path = "/matriculas")
	public ResponseEntity<Matricula> cria(@RequestBody JSONObject json){
		try {
			if(isJSONValid(json.toJSONString())) {
				Matricula matricula = new Matricula();
				parse(matricula, json);
				service.salvar(matricula);
				return ResponseEntity.ok(matricula);
			}
			else {
				return ResponseEntity.badRequest().build();
			}
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
	}
	
	@PutMapping(path = "/matriculas/{id}")
	public ResponseEntity<Matricula> atualiza(@PathVariable("id") Long id, JSONObject json){
		try {
			if(isJSONValid(json.toJSONString())) {
				Matricula matricula = service.buscarPorId(id);
				if(matricula == null) {
					return ResponseEntity.notFound().build();
				}else {
					parse(matricula, json);
					service.salvar(matricula);
					return ResponseEntity.ok(matricula);
				}
			}else {
				return ResponseEntity.badRequest().build();
			}
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
		
	}
	
	@DeleteMapping(path = "/matriculas/{id}")
	public ResponseEntity<Matricula> exclui(@PathVariable("id") long id, @RequestBody JSONObject json){
		Matricula matricula = service.buscarPorId(id);
		if(matricula == null) {
			return ResponseEntity.notFound().build();
		}
		else {
			service.excluir(id);
			return ResponseEntity.noContent().build();
		}
	}
}
