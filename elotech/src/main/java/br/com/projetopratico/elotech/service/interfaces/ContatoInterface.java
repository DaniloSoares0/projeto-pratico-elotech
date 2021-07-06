package br.com.projetopratico.elotech.service.interfaces;

import br.com.projetopratico.elotech.model.Contato;

public interface ContatoInterface extends BaseServiceInterface<Contato>{

	Contato update(Contato contato);
	
	Boolean delete(Contato contato);
		
}
