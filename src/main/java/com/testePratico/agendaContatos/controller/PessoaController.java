package com.testePratico.agendaContatos.controller;

import com.testePratico.agendaContatos.model.Pessoa;
import com.testePratico.agendaContatos.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    PessoaService pessoaService;

    //cadastra um contato
    @PostMapping(value = "/cadastrar")
    public ResponseEntity<Pessoa> cadastrar(@Valid @RequestBody Pessoa pessoa){
        return new ResponseEntity<Pessoa>(pessoaService.cadastrar(pessoa), HttpStatus.CREATED);
    }

    //busca todos os contatos ativos
    @GetMapping("/getContatosAtivos")
    public  ResponseEntity<List<Pessoa>> getContatosAtivos(){
        return new ResponseEntity<List<Pessoa>>(pessoaService.getContatosAtivos(), HttpStatus.OK);
    }

    //faz busca por nome dos contatos ativos
    @GetMapping("/getContatosAtivos_nome")
    public  ResponseEntity<List<Pessoa>> getContatosAtivos_nome(@RequestParam String nome){
        return new ResponseEntity<List<Pessoa>>(pessoaService.getContatosAtivos_nome(nome), HttpStatus.OK);
    }

    //faz busca por email dos contatos ativos
    @GetMapping("/getContatosAtivos_email")
    public  ResponseEntity<List<Pessoa>> getContatosAtivos_email(@RequestParam String email){
        return new ResponseEntity<List<Pessoa>>(pessoaService.getContatosAtivos_email(email), HttpStatus.OK);
    }

    //altera um contato
    @PutMapping(value = "/alterar/{id}")
    public ResponseEntity<Pessoa> alterar(@RequestBody Pessoa pessoa, @PathVariable("id") Long id){
        pessoa.setId(id);
        return new ResponseEntity<Pessoa>(pessoaService.cadastrar(pessoa), HttpStatus.OK);
    }

    //exclui(desabilita) um contato
    @PutMapping("/excluirContato/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id){
        pessoaService.excluirContato(id);
        return ResponseEntity.noContent().build();
    }

    //faz busca por email dos contatos que estão desabilitados
    @GetMapping("/getContatosDesativados_nome")
    public  ResponseEntity<List<Pessoa>> getContatosDesativados_nome(@RequestParam String nome){
        return new ResponseEntity<List<Pessoa>>(pessoaService.getContatosDesativados_nome(nome), HttpStatus.OK);
    }

    //restaura um contato desabilitado
    @PutMapping("/restaurarContato/{id}")
    public ResponseEntity<Void> restaurar(@PathVariable Long id){
        pessoaService.restaurarContato(id);
        return ResponseEntity.noContent().build();
    }
}
