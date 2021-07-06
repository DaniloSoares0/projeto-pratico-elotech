package br.com.projetopratico.elotech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projetopratico.elotech.model.Contato;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {

}
