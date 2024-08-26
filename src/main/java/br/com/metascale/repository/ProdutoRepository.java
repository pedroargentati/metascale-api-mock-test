package br.com.metascale.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.metascale.domain.entity.Produto;

public interface ProdutoRepository extends CrudRepository<Produto, Integer>{

}
