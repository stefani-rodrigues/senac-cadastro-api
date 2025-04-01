package br.com.senac_cadastro_api.controller;


import br.com.senac_cadastro_api.entity.Clientes;
import br.com.senac_cadastro_api.service.ClientesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping ("/clientes")

public class ClientesController {
    @Autowired
    private ClientesService clientesService;


    @GetMapping("/listar")
    public ResponseEntity<List<Clientes>> listarClientes()throws Exception {
        try
        {
            List<Clientes>cadastroList = clientesService.ListarClientes();

            if (cadastroList.isEmpty()){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(cadastroList);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @PostMapping("/criar" )
    public ResponseEntity<Clientes> criarCadastro (@RequestBody Clientes cadastro) throws Exception {
        try
        {
            Clientes cadastroResult =  clientesService.Cadastrar(cadastro);

            return ResponseEntity.ok(cadastroResult);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Clientes> atualizarCadastro(@PathVariable Long id, @RequestBody Clientes cadastroAtualizado) throws Exception {
        cadastroAtualizado.setId(id);
        Clientes cadastroResult = clientesService.AtualizarCadastro(id, cadastroAtualizado);
        return ResponseEntity.ok(cadastroResult);
    }

    @DeleteMapping("/excluir/{id}")
    public void excluirCadastro(@PathVariable Long id) throws Exception {
        try {
            clientesService.ExcluirCadastro(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
