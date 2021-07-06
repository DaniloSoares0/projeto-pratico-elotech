package br.com.projetopratico.elotech.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.projetopratico.elotech.model.Pessoa;
import br.com.projetopratico.elotech.repository.PessoaRepository;
import br.com.projetopratico.elotech.service.interfaces.PessoaInterface;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service("PessoaService")
public class PessoaService implements PessoaInterface {
	
	PessoaRepository pessoaRepository;
	
	public Pessoa save(Pessoa pessoa) {
		return pessoaRepository.save(pessoa);
	}

	@Override
	public void delete(Pessoa pessoa) {
		pessoaRepository.delete(pessoa);
	}

	@Override
	public Optional<Pessoa> findById(Long id) throws Exception {
		return pessoaRepository.findById(id);
	}

	@Override
	public Page<Pessoa> findByNomeOrCpfOrDataNascimento(Pessoa pessoaExample, Pageable pageOptions) {
		return pessoaRepository.findAll(Example.of(pessoaExample), pageOptions);
	}

	@Override
	public void validarDataNascimento(String localDate) throws Exception{
		if(LocalDate.parse(localDate).isAfter(LocalDate.now())) {
		   throw new Exception("A data de nascimento deve ser anterior a data atual");
		}
	}
}
