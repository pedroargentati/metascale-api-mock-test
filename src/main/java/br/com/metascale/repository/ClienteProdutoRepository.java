package br.com.metascale.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.metascale.domain.entity.ClienteProduto;

public interface ClienteProdutoRepository extends JpaRepository<ClienteProduto, Integer>{

}
