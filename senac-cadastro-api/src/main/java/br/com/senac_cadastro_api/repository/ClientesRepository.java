package br.com.senac_cadastro_api.repository;

import br.com.senac_cadastro_api.entity.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientesRepository extends JpaRepository<Clientes, Long> {}
