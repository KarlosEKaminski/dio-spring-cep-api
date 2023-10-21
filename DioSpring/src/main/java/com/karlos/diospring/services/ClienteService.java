package com.karlos.diospring.services;

import com.karlos.diospring.model.Cliente;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface ClienteService {

    Iterable<Cliente> buscarTodos();

    Cliente buscarPorId(UUID uuid);
    void inserir(Cliente cliente);
    void atualizar(UUID uuid, Cliente cliente);
    void deletar(UUID uuid);
}
