package com.example.remedios.curso.controllers;

import com.example.remedios.curso.remedio.DadosCadastroRemedio;
import com.example.remedios.curso.remedio.Remedio;
import com.example.remedios.curso.remedio.RemedioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/remedios")
public class RemedioControler {
    @Autowired
    private RemedioRepository repository;
    @PostMapping
    @Transactional // evita perda de dados
    public void cadastrar(@RequestBody @Valid DadosCadastroRemedio dados){
        repository.save(new Remedio(dados));
    }

    @GetMapping
    public List<DadosListagemRemedio> listar() {
        return repository.findAllByAtivoTrue().stream().map(DadosListagemRemedio::new).toList();
    }

    @PutMapping()
    @Transactional // só salve se a transação for concluída
    public void atualizar(@RequestBody @Valid DadosAtualizarRemedio dados){
        var remedio = repository.getReferenceById(dados.id());
        remedio.atualizarInformacoes(dados);
    }

    @DeleteMapping("/{id}") // parâmetro dinâmico
    public void excluir(@PathVariable Long id){
        repository.deleteById(id);
    }

    @DeleteMapping("/inativar/{id}")
    @Transactional
    public void inativar(@PathVariable Long id) {
        var remedio = repository.getReferenceById(id);
        remedio.inativar();
    }
   
}
