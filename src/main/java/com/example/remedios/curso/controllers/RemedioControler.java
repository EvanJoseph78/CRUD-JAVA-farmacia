package com.example.remedios.curso.controllers;

import com.example.remedios.curso.remedio.DadosCadastroRemedio;
import com.example.remedios.curso.remedio.DadosDetalhamentoRemedio;
import com.example.remedios.curso.remedio.Remedio;
import com.example.remedios.curso.remedio.RemedioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/remedios")
public class RemedioControler {
    @Autowired
    private RemedioRepository repository;

    @PostMapping
    @Transactional // evita perda de dados

    public ResponseEntity<DadosDetalhamentoRemedio> cadastrar(@RequestBody @Valid DadosCadastroRemedio dados, UriComponentsBuilder uriBuilder) {
        var remedio = new Remedio(dados);
        repository.save(remedio);

        var uri = uriBuilder.path("/remedios/{id}").buildAndExpand(remedio.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoRemedio(remedio));
    }

    @GetMapping
    public ResponseEntity<List<DadosListagemRemedio>> listar() {
        var lista = repository.findAllByAtivoTrue().stream().map(DadosListagemRemedio::new).toList();
        return ResponseEntity.ok(lista);
    }

    @PutMapping()
    @Transactional // só salve se a transação for concluída
    public ResponseEntity<DadosDetalhamentoRemedio> atualizar(@RequestBody @Valid DadosAtualizarRemedio dados) {
        var remedio = repository.getReferenceById(dados.id());
        remedio.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoRemedio(remedio));
    }

    @DeleteMapping("/{id}") // parâmetro dinâmico
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/inativar/{id}")
    @Transactional
    public ResponseEntity<Void> inativar(@PathVariable Long id) {
        var remedio = repository.getReferenceById(id);
        remedio.inativar();

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/reativar/{id}")
    @Transactional
    public ResponseEntity<Void> reativar(@PathVariable Long id) {
        var remedio = repository.getReferenceById(id);
        remedio.reativar();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}") // parâmetro dinâmico
    public ResponseEntity<DadosDetalhamentoRemedio> detalhar(@PathVariable Long id) {
        var remedio = repository.getReferenceById(id);

        return ResponseEntity.ok(new DadosDetalhamentoRemedio(remedio));
    }

}
