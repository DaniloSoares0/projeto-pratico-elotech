package br.com.projetopratico.elotech.service.interfaces;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.projetopratico.elotech.model.Pessoa;

public interface PessoaInterface extends BaseServiceInterface<Pessoa> {
	
	void delete(Pessoa pessoa);
		
	Optional<Pessoa> findById(Long id) throws Exception;
	
	Page<Pessoa> findByNomeOrCpfOrDataNascimento(Pessoa pessoaExample, Pageable pageOptions);
		
	void validarDataNascimento(String localDate) throws Exception;;
}
