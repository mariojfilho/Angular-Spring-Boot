package com.github.mariojfilho.clientes.model.repository;

import com.github.mariojfilho.clientes.model.entity.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoRpository extends JpaRepository<Servico, Integer> {
}
