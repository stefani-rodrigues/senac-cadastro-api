package br.com.senac_cadastro_api.controller;


import br.com.senac_cadastro_api.entity.Cadastro;
import br.com.senac_cadastro_api.repository.CadastroRepository;
import br.com.senac_cadastro_api.service.CadastroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping ("/clientes")

public class CadastroController
{
    @Autowired
    private final CadastroService _cadastroService;

    public CadastroController(CadastroService cadastroService) {
        _cadastroService = cadastroService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Cadastro>> ListarClientes()throws Exception {
        try
        {
            List<Cadastro>cadastroList = _cadastroService.ListarClientes();

            if (cadastroList.isEmpty()){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(cadastroList);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @PostMapping("/criar" )
    public ResponseEntity<Cadastro> CriarCadastro (@RequestBody Cadastro cadastro) throws Exception {
        try
        {
            Cadastro cadastroResult =  _cadastroService.Cadastrar(cadastro);

            return ResponseEntity.ok(cadastroResult);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Cadastro> AtualizarCadastro(@PathVariable Long id, @RequestBody Cadastro cadastroAtualizado) throws Exception {
        cadastroAtualizado.setId(id);
        Cadastro cadastroResult = _cadastroService.AtualizarCadastro(id, cadastroAtualizado);
        return ResponseEntity.ok(cadastroResult);
    }

    @DeleteMapping("/excluir/{id}")
    public void ExcluirCadastro(@PathVariable Long id) throws Exception {
        try {
            _cadastroService.ExcluirCadastro(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
