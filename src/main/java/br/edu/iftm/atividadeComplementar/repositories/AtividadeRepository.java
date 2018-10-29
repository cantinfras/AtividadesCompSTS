package br.edu.iftm.atividadeComplementar.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.edu.iftm.atividadeComplementar.domains.Atividade;

@Repository
public interface AtividadeRepository extends JpaRepository<Atividade,Long>{
	List<Atividade> fidnByNomeContainingIgnoreCase(@Param("nome") String nome);
}
