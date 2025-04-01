package br.com.senac_cadastro_api.service;


import br.com.senac_cadastro_api.entity.Clientes;
import br.com.senac_cadastro_api.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientesService {
    @Autowired
    private ClientesRepository clientesRepository;


    public Clientes Cadastrar(Clientes cadastro) {
        if (cadastro.getDocumento() == null|| cadastro.getDocumento().isEmpty())
        {
            throw new IllegalArgumentException("O documento não pode ser vazio.");
        }
        if (cadastro.getNome() == null|| cadastro.getNome().isEmpty())
        {
            throw new IllegalArgumentException("O nome não pode ser vazio.");
        }
        if (cadastro.getSobrenome()==null|| cadastro.getSobrenome().isEmpty()){
            throw new IllegalArgumentException("O sobrenome não pode ser vazio.");
        }
        return clientesRepository.save(cadastro);
    }

    public Clientes AtualizarCadastro (Long id, Clientes cadastroAtualizado){
        Optional<Clientes> clienteExistente = clientesRepository.findById(id);

        if (clienteExistente.isEmpty()){
            throw new RuntimeException("O cliente não foi encontrado");
        }
        return clientesRepository.save(cadastroAtualizado);
    }

    public void ExcluirCadastro(Long id) {
        if (!clientesRepository.existsById(id)) {
            throw new RuntimeException("Cadastro com ID " + id + " não encontrado.");
        }
        clientesRepository.deleteById(id);
    }

    public List<Clientes> ListarClientes() {
        return  clientesRepository.findAll();
    }
}

