package br.com.zupacademy.gian.proposta.novaproposta;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta, Long> {

	List<Proposta> findByDocumento(String documento);

	@Query(value = "select p from Proposta p where p.estadoProposta = 'ELEGIVEL'"
			+ "and numeroCartao is null")
	List<Proposta> findByPropostaElegivelECartaoNull();
}
