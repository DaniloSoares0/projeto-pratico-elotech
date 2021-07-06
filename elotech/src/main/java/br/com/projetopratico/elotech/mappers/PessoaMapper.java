package br.com.projetopratico.elotech.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import br.com.projetopratico.elotech.dto.PessoaDTO;
import br.com.projetopratico.elotech.model.Pessoa;

@Mapper(componentModel = "spring")
public interface PessoaMapper {

	@Mappings({
		@Mapping(source = "id", target = "id"),
		@Mapping(source = "nome", target = "nome"),
		@Mapping(source = "cpf", target = "cpf"),
		@Mapping(source = "dataNascimento", target = "dataNascimento"),
		@Mapping(source = "contatos", target = "contatos"),
		@Mapping(source = "createdAt", target = "createdAt", dateFormat = "yyyy-MM-dd HH:mm"),
	})
	
	PessoaDTO mapToDTO(Pessoa pessoa);

	List<PessoaDTO> mapToListDTO(List<Pessoa> pessoa);


	@Mappings({
		@Mapping(source = "id", target = "id"),
		@Mapping(source = "nome", target = "nome"),
		@Mapping(source = "cpf", target = "cpf"),
		@Mapping(source = "dataNascimento", target = "dataNascimento"),
		@Mapping(source = "contatos", target = "contatos"),
		@Mapping(source = "createdAt", target = "createdAt", dateFormat = "yyyy-MM-dd HH:mm"),
	})
	
	Pessoa mapToPessoa(PessoaDTO pessoaDTO);
	
	List<Pessoa> mapToListPessoa(List<PessoaDTO> pessoaDTO);

    void castToPessoa(@MappingTarget Pessoa pessoa,  PessoaDTO tagetpessoaDTO);
}
