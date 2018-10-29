package br.edu.iftm.atividadeComplementar.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iftm.atividadeComplementar.domains.Atividade;
import br.edu.iftm.atividadeComplementar.repositories.AtividadeRepository;

@Service
public class AtividadeService {
	
	@Autowired
	private AtividadeRepository repository;
	
	public List<Atividade> buscar(String nome) {
		return repository.fidnByNomeContainingIgnoreCase(nome);
	}
	
	public Optional<Atividade> buscarCodigo(Long codigo) {
		return repository.findById(codigo);
	}
	
	public List<Atividade> buscarTodos() {
		return repository.findAll();
	}
	

}
