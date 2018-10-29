package br.edu.iftm.atividadeComplementar.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.edu.iftm.atividadeComplementar.domains.Atividade;
import br.edu.iftm.atividadeComplementar.repositories.AtividadeRepository;

@RestController
@RequestMapping(value="/atividades")
public class AtividadeResource {
	
	@Autowired
	private AtividadeRepository repo;
	
	@GetMapping(value="like/{nome}")
	public ResponseEntity<?> findByNome(@PathVariable String nome) {
		List<Atividade> atividades = repo.fidnByNomeContainingIgnoreCase(nome);
		if(atividades.size() > 0) {
			return ResponseEntity.ok(atividades);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping(value="{codigo}")
	public ResponseEntity<?> findByRa(@PathVariable Long codigo) {
		Optional<Atividade> atividadeOptional = repo.findById(codigo);
		if(atividadeOptional.isPresent()) {
			return ResponseEntity.ok(atividadeOptional);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping(value="{codigo}")
	public ResponseEntity<?> deleteById(@PathVariable Long codigo){
		try {
			repo.deleteById(codigo);
			return ResponseEntity.ok(codigo);
		} catch(EmptyResultDataAccessException e) {
			
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody Atividade atividade){
		Atividade a = repo.save(atividade);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{codigo}").buildAndExpand(a.getCodigo()).toUri();
		return ResponseEntity.created(location).build();
	}

}
