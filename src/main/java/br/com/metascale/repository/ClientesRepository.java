package br.com.metascale.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.metascale.domain.entity.Clientes;

public interface ClientesRepository extends CrudRepository<Clientes, Integer>{

}
