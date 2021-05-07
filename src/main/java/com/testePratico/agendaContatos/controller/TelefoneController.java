package com.testePratico.agendaContatos.controller;

import com.testePratico.agendaContatos.model.Telefone;
import com.testePratico.agendaContatos.service.TelefoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/telefone")
public class TelefoneController {

    @Autowired
    TelefoneService telefoneService;

    @PutMapping(value = "/alterar/{id}")
    public ResponseEntity<Telefone> alterar(@RequestBody Telefone telefone, @PathVariable("id") Long id){
        telefone.setId(id);
        return new ResponseEntity<Telefone>(telefoneService.atualizar(telefone), HttpStatus.OK);
    }

}
