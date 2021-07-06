package br.com.projetopratico.elotech.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Pessoa")
@EqualsAndHashCode(of = "id")
@EntityListeners(AuditingEntityListener.class)
public class Pessoa implements Serializable {

	private static final long serialVersionUID = -8505242105324087597L;
	
	@Id
	@Column(name = "id" )
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome" )
	private String nome;
	
	@Column(unique = true)
	private String cpf;
	
	@Column(name = "data_nascimento" )
	private LocalDate dataNascimento;
	//neste exemplo prático não faz sentido carregar a pessoa sem seus contatos
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Contato> contatos;
	
    @CreatedDate
    @Column(nullable = false, updatable = false)
	private LocalDateTime createdAt ;

}
