package com.testePratico.agendaContatos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "pessoa")
@EqualsAndHashCode(of = { "id" })
public class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true, name = "pessoa_id")
    private Long id;

    @Column(nullable = false, columnDefinition = "varchar(30)")
    @NotEmpty(message = "Informe o Primeiro Nome")
    private String primeiroNome;

    @Column(nullable = false, columnDefinition = "varchar(30)")
    @NotEmpty(message = "Informe o Ultimo Nome")
    private String ultimoNome;

    @Column(nullable = false, columnDefinition = "varchar(30)")
    @NotEmpty(message = "Informe o E-mail")
    private String email;

    @JsonIgnore
    private Boolean habilitado;

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
    @NotEmpty(message = "Informe ao menos um telefone")
    private List<Telefone> telefones = new ArrayList<>();
}
