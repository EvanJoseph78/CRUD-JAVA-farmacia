package com.example.remedios.curso.remedio;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

import java.time.LocalDate;

import com.example.remedios.curso.controllers.DadosAtualizarRemedio;

@Table(name = "Remedio") // tabela vai ser representado no banco por Remedios
@Entity(name = "remedios") // demonstra que a classe representa uma entidade
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")

public class Remedio {

    public Remedio(DadosCadastroRemedio dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.via = dados.via();
        this.lote = dados.lote();
        this.validade = dados.validade();
        this.quantidade = dados.quantidade();
        this.laboratorio = dados.laboratorio();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Enumerated(EnumType.STRING)
    private Via via;
    private String lote;
    private int quantidade;
    private LocalDate validade;
    @Enumerated(EnumType.STRING)
    private Laboratorio laboratorio;
    private Boolean ativo;

    public void atualizarInformacoes(@Valid DadosAtualizarRemedio dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }

        if (dados.via() != null) {
            this.via = dados.via();
        }

        if (dados.laboratorio() != null) {
            this.laboratorio = dados.laboratorio();
        }
    }

    public void inativar() {
        this.ativo = false;
    }

}
