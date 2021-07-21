package br.com.zupacademy.gian.proposta.novaproposta;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@ActiveProfiles("test")
public class TesteNovaProposta {
	
	/*
	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper jsonMapper;

	@Autowired
	EntityManager manager;
	
	@Test
	public void deveCriarUmaNovaProposta() throws Exception {
		mockMvc.perform(post("/proposta")
				.contentType(MediaType.APPLICATION_JSON)
				.content(toJson(new NovaPropostaRequest("67935334013", "gian@gian.com", "Gian",
						"Rua dos Alfedeiros", new BigDecimal(2500.00)))))
				.andExpect(MockMvcResultMatchers.status().isCreated());

		String sql = "from Proposta p where p.nome = :nome";

		TypedQuery<Proposta> query = manager.createQuery(sql, Proposta.class);
		query.setParameter("nome", "Gian");

		List<Proposta> propostas = query.getResultList();
		Proposta proposta = propostas.get(0);

		assertTrue(propostas.size() == 1);
		assertEquals("Gian", proposta.getNome());
	}
	
	@Test
	public void naoDeveCriarUmaNovaPropostaSeDocumentoJaExiste() throws Exception {
		Proposta proposta = new Proposta("67935334013", "email@email", "nome", "endereco", new BigDecimal(2500.0));
		manager.persist(proposta);
		
		mockMvc.perform(post("/proposta")
				.contentType(MediaType.APPLICATION_JSON)
				.content(toJson(new NovaPropostaRequest("67935334013", "gian@gian.com", "Gian",
						"Rua dos Alfedeiros", new BigDecimal(2500.00)))))
				.andExpect(MockMvcResultMatchers.status().isUnprocessableEntity());
	}

	private String toJson(NovaPropostaRequest request) throws JsonProcessingException {
		return jsonMapper.writeValueAsString(request);
	}*/
}
