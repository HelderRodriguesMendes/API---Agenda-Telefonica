package com.testePratico.agendaContatos.service;

import com.testePratico.agendaContatos.model.Telefone;
import com.testePratico.agendaContatos.repository.TelefoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TelefoneService {

    @Autowired
    TelefoneRepository telefoneRepository;

    public List<Telefone> getTelefonesAtivos(Long cliente_id){
        return telefoneRepository.getTelefonesAtivos(cliente_id).get();
    }

    public Telefone atualizar(Telefone telefone){
        return telefoneRepository.save(telefone);
    }

    public void desativar(Long id){
        telefoneRepository.desativar(id);
    }

    public List<Telefone> getTelefonesDesativados(Long cliente_id){
        return telefoneRepository.getTelefonesDesativados(cliente_id).get();
    }

    public void restaurar(Long id){
        telefoneRepository.restaurar(id);
    }
}
