package com.example.remedios.curso.controllers;

import com.example.remedios.curso.remedio.Laboratorio;
import com.example.remedios.curso.remedio.Via;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizarRemedio(
        @NotNull
        Long id,
        String nome,
        Via via,
        Laboratorio laboratorio) {

}
