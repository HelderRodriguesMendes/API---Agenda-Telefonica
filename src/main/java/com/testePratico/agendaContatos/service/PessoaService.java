package com.testePratico.agendaContatos.service;

import com.testePratico.agendaContatos.model.Pessoa;
import com.testePratico.agendaContatos.model.Telefone;
import com.testePratico.agendaContatos.repository.PessoaRepository;
import com.testePratico.agendaContatos.repository.TelefoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    PessoaRepository pessoaRepository;

    @Autowired
    TelefoneRepository telefoneRepository;

    @Autowired
    TelefoneService telefoneService;

    List<Pessoa> pessoas;


    public Pessoa cadastrar(Pessoa pessoa) {
        pessoa.setHabilitado(true);

        List<Telefone> telefones = pessoa.getTelefones();

        Pessoa pessoaSalva = pessoaRepository.save(pessoa);
        telefones.stream().forEach(t -> {
            t.setPessoa(pessoaSalva);
            t.setHabilitado(true);
            telefoneRepository.save(t);
        });
        pessoaSalva.setTelefones(telefones);


        return pessoaSalva;
    }

    public List<Pessoa> getContatosAtivos() {
        pessoas = new ArrayList<>();
        pessoas = pessoaRepository.getContatosAtivos().get();

        return getJsonCompleto(pessoas, true);
    }

    public List<Pessoa> getContatosAtivos_nome(String nome) {
        pessoas = new ArrayList<>();
        pessoas = pessoaRepository.getContatosAtivos_nome(nome).get();

        return getJsonCompleto(pessoas, true);
    }

    public List<Pessoa> getContatosAtivos_email(String email) {
        pessoas = new ArrayList<>();
        pessoas = pessoaRepository.getContatosAtivos_email(email).get();

        return getJsonCompleto(pessoas, true);
    }

    public List<Pessoa> getContatosDesativados_nome(String nome) {
        pessoas = new ArrayList<>();
        pessoas = pessoaRepository.getContatosDesativados_nome(nome).get();

        return getJsonCompleto(pessoas, false);
    }

    public void excluirContato(Long idPessoa) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(idPessoa);

        List<Telefone> telefones = pessoa.get().getTelefones();

        telefones.stream().forEach(telefone -> {
            telefoneService.desativar(telefone.getId());
        });
        pessoaRepository.desativar(pessoa.get().getId());
    }

    public void restaurarContato(Long idPessoa){
        List<Telefone> telefonesDesativados = telefoneService.getTelefonesDesativados(idPessoa);

        telefonesDesativados.stream().forEach(telefone -> {
            telefoneService.restaurar(telefone.getId());
        });

        pessoaRepository.restaurar(idPessoa);
    }

    //busca todos os telefones das pessoas
    public List<Pessoa> getJsonCompleto(List<Pessoa> pessoas, boolean ativos) {

        pessoas.stream().forEach(p -> {
            if (ativos) {
                p.setTelefones(telefoneService.getTelefonesAtivos(p.getId()));
            } else {
                p.setTelefones(telefoneService.getTelefonesDesativados(p.getId()));
            }
        });

        return pessoas;
    }
}
