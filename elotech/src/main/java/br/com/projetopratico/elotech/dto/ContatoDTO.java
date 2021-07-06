package br.com.projetopratico.elotech.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContatoDTO {

	private Long id;
	
	private String nome;
	
	private String telefone;

	private String email;
	
}
