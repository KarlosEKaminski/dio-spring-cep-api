package com.karlos.diospring.services.impi;

import com.karlos.diospring.model.Cliente;
import com.karlos.diospring.model.Endereco;
import com.karlos.diospring.repositories.ClienteRepository;
import com.karlos.diospring.repositories.EnderecoRepository;
import com.karlos.diospring.services.ClienteService;
import com.karlos.diospring.services.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ClienteServiceImpi implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private ViaCepService viaCepService;

    @Override
    public Iterable<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorId(UUID uuid) {
        Optional<Cliente> optional = clienteRepository.findById(uuid);
        return optional.get();
    }

    @Override
    public void inserir(Cliente cliente) {
        salvarClienteComCep(cliente);
    }

    private void salvarClienteComCep(Cliente cliente) {
        String cep = cliente.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
            Endereco novoEndereco = viaCepService.consultarCep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });
        cliente.setEndereco(endereco);
        clienteRepository.save(cliente);
    }

    @Override
    public void atualizar(UUID uuid, Cliente cliente) {
        Optional<Cliente> optional = clienteRepository.findById(uuid);
        if(optional.isPresent()) {
            salvarClienteComCep(cliente);
        }
    }

    @Override
    public void deletar(UUID uuid) {
        clienteRepository.deleteById(uuid);
    }
}
