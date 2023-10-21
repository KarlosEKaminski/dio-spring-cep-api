package com.karlos.diospring.repositories;

import com.karlos.diospring.model.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ClienteRepository extends CrudRepository<Cliente, UUID> {
}
