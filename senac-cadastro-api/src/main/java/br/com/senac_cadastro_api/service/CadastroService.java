package br.com.senac_cadastro_api.service;


import br.com.senac_cadastro_api.entity.Cadastro;
import br.com.senac_cadastro_api.repository.CadastroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CadastroService {
    @Autowired
    private CadastroRepository cadastroRepository;

    public Cadastro criarCadastro (Cadastro cadastro)
    {
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
        return cadastroRepository.save(cadastro);
    }

    public Cadastro atualizarCadastro (Long id, Cadastro cadastroAtualizado){
        Optional<Cadastro> clienteExistente = cadastroRepository.findById(id);

        if (clienteExistente.isEmpty()){
            throw new RuntimeException("O cliente não foi encontrado");
        }
        return cadastroRepository.save(cadastroAtualizado);
    }

    public boolean excluirCadastro(Long id) {

        Optional<Cadastro> clienteExistente = cadastroRepository.findById(id);

        if (clienteExistente.isEmpty()) {
            return false;
        }

        cadastroRepository.deleteById(id);

        return true;
    }
}

