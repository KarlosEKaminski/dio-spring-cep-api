package com.karlos.diospring.controller;

import com.karlos.diospring.model.Cliente;
import com.karlos.diospring.services.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("clientes")
public class ClienteRestController {
    private final ClienteService clienteService;

    public ClienteRestController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable UUID uuid) {
        return ResponseEntity.ok(clienteService.buscarPorId(uuid));
    }

    @GetMapping
    public ResponseEntity<Iterable> buscarTodos() {
        return ResponseEntity.ok(clienteService.buscarTodos());
    }

    @PostMapping
    public ResponseEntity<Cliente> inserir(@RequestBody Cliente cliente) {
        clienteService.inserir(cliente);
        return ResponseEntity.ok(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(@PathVariable UUID uuid, @RequestBody Cliente cliente) {
        clienteService.atualizar(uuid, cliente);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Cliente> deletar(@PathVariable UUID uuid) {
        clienteService.deletar(uuid);
        return ResponseEntity.ok().build();
    }


}
