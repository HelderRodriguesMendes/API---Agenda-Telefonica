package com.testePratico.agendaContatos.repository;

import com.testePratico.agendaContatos.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    @Transactional
    @Query(value = "select * from pessoa where habilitado = true order by primeiro_nome", nativeQuery = true)
    Optional<List<Pessoa>> getContatosAtivos();

    @Transactional
    @Query(value = "select * from pessoa where habilitado = true and primeiro_nome like %?1% limit 100", nativeQuery = true)
    Optional<List<Pessoa>> getContatosAtivos_nome( String nome);

    @Transactional
    @Query(value = "select * from pessoa where habilitado = true and email like %?1% limit 100", nativeQuery = true)
    Optional<List<Pessoa>> getContatosAtivos_email( String email);

    @Transactional
    @Modifying
    @Query(value = "update pessoa set habilitado = false where pessoa_id = ?1", nativeQuery = true)
    public void desativar(Long id);

    @Transactional
    @Query(value = "select * from pessoa where habilitado = false and primeiro_nome like %?1% limit 100", nativeQuery = true)
    Optional<List<Pessoa>> getContatosDesativados_nome( String nome);

    @Transactional
    @Modifying
    @Query(value = "update pessoa set habilitado = true where pessoa_id = ?1", nativeQuery = true)
    public void restaurar(Long id);
}
