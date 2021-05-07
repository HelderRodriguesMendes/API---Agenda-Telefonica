package com.testePratico.agendaContatos.repository;

import com.testePratico.agendaContatos.model.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Long> {
    @Transactional
    @Query(value = "select * from telefone where habilitado = true and pessoa_id = ?1", nativeQuery = true)
    Optional<List<Telefone>> getTelefonesAtivos(Long cliente_id);

    @Transactional
    @Modifying
    @Query(value = "update telefone set habilitado = false where telefone_id = ?1", nativeQuery = true)
    public void desativar(Long id);

    @Transactional
    @Query(value = "select * from telefone where habilitado = false and pessoa_id = ?1", nativeQuery = true)
    Optional<List<Telefone>> getTelefonesDesativados(Long cliente_id);

    @Transactional
    @Modifying
    @Query(value = "update telefone set habilitado = true where telefone_id = ?1", nativeQuery = true)
    public void restaurar(Long id);
}
