package br.com.alura.listavip.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.alura.listavip.model.Convidado;
//ele precisa saber qual entidade ele vai gerar o crud e qual o serialize da classe
public interface ConvidadoRepository extends CrudRepository<Convidado, Long>{
	List<Convidado> findByNome(String nome);
}
