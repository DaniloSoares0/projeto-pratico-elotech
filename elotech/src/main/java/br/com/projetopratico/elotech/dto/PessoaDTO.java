package br.com.projetopratico.elotech.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PessoaDTO implements Serializable{

	private static final long serialVersionUID = -5448054585429225193L;

	private Long id;
	
	@NotNull
	private String nome;
	
	@CPF(message="cpf inválido")
	private String cpf;
	
	@NotNull
	private String dataNascimento;

	@NotNull
	private List<ContatoDTO> contatos;
	
	@ApiModelProperty(readOnly = true)
	private LocalDateTime createdAt;

}
