package br.com.projetopratico.elotech.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.projetopratico.elotech.dto.PessoaDTO;
import br.com.projetopratico.elotech.mappers.PessoaMapper;
import br.com.projetopratico.elotech.model.Pessoa;

@SpringBootTest
public class PessoaServiceTest {

    private static Validator validator;
    
    @Autowired
    private PessoaMapper pessoaMapper;

    @BeforeAll
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }


	@Test
	public void cpfValido() {
		Pessoa pessoa = Pessoa.builder()
				.cpf("122.706.156-03")
				.createdAt(LocalDateTime.now())
				.build();

        Set<ConstraintViolation<PessoaDTO>> constraintViolations = validator
                .validate(pessoaMapper.mapToDTO(pessoa));
        
        assertEquals(3, constraintViolations.size());
	}
}
