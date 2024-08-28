package br.com.metascale.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.metascale.domain.entity.Clientes;

public interface ClientesRepository extends JpaRepository<Clientes, Integer>{

}
