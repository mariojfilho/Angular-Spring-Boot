package com.github.mariojfilho.clientes.rest;

import com.github.mariojfilho.clientes.model.entity.Cliente;
import com.github.mariojfilho.clientes.model.repository.ClienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.http.HttpResponse;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteRepository repository;

    @Autowired
    public ClienteController(ClienteRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente salvar ( @RequestBody @Valid Cliente cliente){
        return repository.save(cliente);
    }

    @GetMapping("{id}")
    public Cliente acharPorId ( @PathVariable Integer id ) {
        return repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não Encontrado"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar ( @PathVariable Integer id ) {
        repository
                .findById(id)
                .map(cliente -> {
                    repository.delete(cliente);
                    return Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não Encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar( @PathVariable Integer id, @RequestBody @Valid Cliente clienteAtualizado) {
        repository
                .findById(id)
                .map(cliente -> {
                    clienteAtualizado.setId(cliente.getId());
                    return repository.save(clienteAtualizado);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não Encontrado"));
    }

}
