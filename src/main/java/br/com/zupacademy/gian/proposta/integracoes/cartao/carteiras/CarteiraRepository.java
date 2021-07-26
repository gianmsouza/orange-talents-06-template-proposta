package br.com.zupacademy.gian.proposta.integracoes.cartao.carteiras;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarteiraRepository extends JpaRepository<Carteira, Long> {

}
