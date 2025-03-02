package br.com.senac_cadastro_api.controller;


import br.com.senac_cadastro_api.entity.Cadastro;
import br.com.senac_cadastro_api.repository.CadastroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping ("/cliente")

public class CadastroController
{

    @Autowired
    private CadastroRepository cadastroRepository;

    @GetMapping("/listar")
    public ResponseEntity<List<Cadastro>> listarClientes()throws Exception
    {
        try
        {
            List<Cadastro>cadastroList = cadastroRepository.findAll();

            if (cadastroList.isEmpty()){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(cadastroList);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @PostMapping("/criar")
    public ResponseEntity<Cadastro> criarCadastro (@RequestBody Cadastro cadastro) throws Exception {
        try
        {
            Cadastro cadastroResult = cadastroRepository.save(cadastro);

            return ResponseEntity.ok(cadastroResult);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Cadastro> atualizarCadastro(@PathVariable Long id, @RequestBody Cadastro cadastroAtualizado) throws Exception {

        if (!cadastroRepository.existsById(id)) {
            throw new Exception();
        }
        cadastroAtualizado.setId(id);
        Cadastro cadastroResult = cadastroRepository.save(cadastroAtualizado);
        return ResponseEntity.ok(cadastroResult);
    }
    @DeleteMapping("/excluir/{id}")
    public void excluirCadastro(@PathVariable Long id) throws Exception {
        try {
            if (!cadastroRepository.existsById(id))throw new Exception(); {
                cadastroRepository.deleteById(id);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
