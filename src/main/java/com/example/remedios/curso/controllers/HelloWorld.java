package com.example.remedios.curso.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Criação do end-point
@RestController
@RequestMapping("/hello")
public class HelloWorld {
    @GetMapping  // identifica para o spring o verbo a ser usado
    public String helloWorld(){
        return "Hello World";
    }
}
