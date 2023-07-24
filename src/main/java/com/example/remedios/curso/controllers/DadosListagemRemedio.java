package com.example.remedios.curso.controllers;

import com.example.remedios.curso.remedio.Laboratorio;
import com.example.remedios.curso.remedio.Remedio;
import com.example.remedios.curso.remedio.Via;

import java.time.LocalDate;

public record DadosListagemRemedio(
        Long id,
        String nome,
        Via via,
        String lote,
        Laboratorio laboratorio,
        LocalDate validade
) {

    public DadosListagemRemedio(Remedio remedio){
        this(remedio.getId(), remedio.getNome(), remedio.getVia(), remedio.getLote(), remedio.getLaboratorio(), remedio.getValidade());
    }
}

