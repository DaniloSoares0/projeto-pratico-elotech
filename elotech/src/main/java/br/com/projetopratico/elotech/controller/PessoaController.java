package br.com.projetopratico.elotech.controller;

import java.io.Serializable;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.hibernate.ObjectNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.projetopratico.elotech.dto.PessoaDTO;
import br.com.projetopratico.elotech.mappers.PessoaMapper;
import br.com.projetopratico.elotech.model.Pessoa;
import br.com.projetopratico.elotech.service.PessoaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@Api(value = "Pessoas")
@RequestMapping("/pessoas")
public class PessoaController {

	private PessoaService pessoaService;
	private PessoaMapper pessoaMapper;
	
	@GetMapping("/{id}")
	@ApiOperation(value = "Busca pessoas por id.")
	public ResponseEntity<Serializable> findById(@PathVariable("id") Long id) throws ObjectNotFoundException {
		
		try {

			return ResponseEntity.ok().body(pessoaService.findById(id)
					.map(pessoaMapper::mapToDTO).get());
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Usuario não encontrado");
		}
	}
	
    @GetMapping("/pesquisa")
	@ApiOperation(value = "Busca pessoa com paginação e filtros.")
    public Page<Pessoa> findPessoa(@RequestBody PessoaDTO pessoaExample,
                                   @RequestParam(name = "page" , defaultValue = "0") Integer page,
                                   @RequestParam(name = "size" , defaultValue = "10") Integer size,
                                   @RequestParam(name = "sortTarget" , defaultValue = "ASC") String sortTarget){
    
        return pessoaService.findByNomeOrCpfOrDataNascimento(pessoaMapper.mapToPessoa(pessoaExample),
        		PageRequest.of(page, size, Sort.Direction.valueOf(sortTarget), "createdAt"));
    }
	 
    @ApiOperation(value = "Cadastra uma pessoa.")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Serializable> save(@RequestBody @Valid PessoaDTO pessoaDTO) {

		try {
			
			pessoaService.validarDataNascimento(pessoaDTO.getDataNascimento());
			
			return ResponseEntity.status(HttpStatus.OK)
					.body(pessoaMapper.mapToDTO(pessoaService.save(pessoaMapper.mapToPessoa(pessoaDTO))));

		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("O cliente já se encontra cadastrado na base");

		} catch (ConstraintViolationException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Digite um cpf válido ");

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Um erro ocorreu ao processar a solicitação");
		}
	}
	
	@ApiOperation(value = "Atualiza uma pessoa.")
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Serializable> update(@RequestBody @Valid PessoaDTO pessoaDTO)  {

		try {
			pessoaService.validarDataNascimento(pessoaDTO.getDataNascimento());

			Pessoa pessoa = pessoaService.findById(pessoaDTO.getId()).get();

			pessoaMapper.castToPessoa(pessoa, pessoaDTO);

			pessoaMapper.mapToDTO(pessoaService.save(pessoa));

			return ResponseEntity.status(HttpStatus.OK)
					.body(pessoa);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Um erro ocorreu ao processar a solicitação");
		}
	}
	
	@ApiOperation(value = "Deleta uma pessoa.")
	@DeleteMapping (consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Serializable> delete(@RequestBody @Valid PessoaDTO pessoaDTO) {

		try {
			
			Pessoa pessoa = pessoaService.findById(pessoaDTO.getId()).get();

			pessoaMapper.castToPessoa(pessoa, pessoaDTO);

			pessoaService.delete(pessoa);

			return ResponseEntity.status(HttpStatus.OK)
					.body("Usuário deletado");

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Cliente não encontrado");
		}
	}
}
