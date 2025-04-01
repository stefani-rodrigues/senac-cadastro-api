package br.com.senac_cadastro_api.service;

import br.com.senac_cadastro_api.entity.Enderecos;
import br.com.senac_cadastro_api.exceptions.SenacException;
import br.com.senac_cadastro_api.repository.ClientesRepository;
import br.com.senac_cadastro_api.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.senac_cadastro_api.utils.ValidacoesUtils.validarSeRegistroExiste;

@Service
public class EnderecosService {

    @Autowired
    private EnderecoRepository enderecosRepository;

    @Autowired
    private ClientesRepository clientesRepository;

    public Enderecos criarEndereco(Enderecos endereco) throws SenacException {
        if(endereco.getClientes()== null) {
            throw new SenacException("Cliente não informado!");
        }

        if(endereco.getClientes().getId() == null) {
            throw new SenacException("Id do cliente não informado!");
        }

        // Valida se o cliente existe na base
        if(!clientesRepository.existsById(endereco.getClientes().getId())) {
            throw new SenacException("Cliente não encontrado!");
        }

        boolean permiteCriacao = this.validarQuantidadeEnderecos(endereco.getClientes().getId());
        if(!permiteCriacao) {
            throw new SenacException("Cliente já possui 3 endereços");
        }

        // zera id para garantir que o banco gere o ID
        endereco.setId(null);

        return enderecosRepository.save(endereco);
    }

    public Enderecos atualizarEndereco(Long id, Enderecos endereco) {
        validarSeRegistroExiste(enderecosRepository, id);

        // adicionado id passado por parametro no objeto
        endereco.setId(id);

        return enderecosRepository.save(endereco);
    }

    public List<Enderecos> carregarEnderecos() {
        List<Enderecos> enderecosResult = enderecosRepository.findAll();

        return enderecosResult;
    }

    public List<Enderecos> carregarEnderecosByClienteId(Long clienteId) {
        List<Enderecos> enderecosResult = enderecosRepository.findByClientesId(clienteId);

        return enderecosResult;
    }

    public void excluirEndereco(Long id) {
        validarSeRegistroExiste(enderecosRepository, id);

        enderecosRepository.deleteById(id);
    }

    private boolean validarQuantidadeEnderecos(Long clienteId) {
        List<Enderecos> enderecosResult = enderecosRepository.findByClientesId(clienteId);

        if(enderecosResult.isEmpty()) {
            return true;
        }

        return enderecosResult.size() >= 3 ? false : true;
    }
}
