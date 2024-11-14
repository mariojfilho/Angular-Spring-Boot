package com.github.mariojfilho.clientes.model.repository;

import com.github.mariojfilho.clientes.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
