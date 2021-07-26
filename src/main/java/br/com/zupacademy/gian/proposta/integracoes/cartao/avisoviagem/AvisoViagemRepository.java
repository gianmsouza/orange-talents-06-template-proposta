package br.com.zupacademy.gian.proposta.integracoes.cartao.avisoviagem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvisoViagemRepository extends JpaRepository<AvisoViagem, Long> {

}
