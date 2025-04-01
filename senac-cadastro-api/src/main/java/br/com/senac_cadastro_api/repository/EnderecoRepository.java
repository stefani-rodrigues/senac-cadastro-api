package br.com.senac_cadastro_api.repository;

import br.com.senac_cadastro_api.entity.Enderecos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnderecoRepository extends JpaRepository<Enderecos, Long> {
    List<Enderecos> findByClientesId(Long clientesId);
}
