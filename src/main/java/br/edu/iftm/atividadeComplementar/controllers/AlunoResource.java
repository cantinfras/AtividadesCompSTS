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

import br.edu.iftm.atividadeComplementar.domains.Aluno;
import br.edu.iftm.atividadeComplementar.repositories.AlunoRepository;


@RestController
@RequestMapping(value="/alunos")
public class AlunoResource {
	
	@Autowired
	private AlunoRepository repo;
	
	@GetMapping(value="like/{nome}")
	public ResponseEntity<?> findByNome(@PathVariable String nome) {
		List<Aluno> alunos = repo.findByNomeContainingIgnoreCase(nome);
		if(alunos.size() > 0) {
			return ResponseEntity.ok(alunos);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping(value="{ra}")
	public ResponseEntity<?> findByRa(@PathVariable Long ra) {
		Optional<Aluno> alunoOptional = repo.findById(ra);
		if(alunoOptional.isPresent()) {
			return ResponseEntity.ok(alunoOptional);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping(value="{ra}")
	public ResponseEntity<?> deleteById(@PathVariable Long ra){
		try {
			repo.deleteById(ra);
			return ResponseEntity.ok(ra);
		} catch(EmptyResultDataAccessException e) {
			
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody Aluno aluno){
		Aluno a = repo.save(aluno);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{ra}").buildAndExpand(a.getRa()).toUri();
		return ResponseEntity.created(location).build();
	}


}
