package br.com.metascale.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.metascale.domain.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

}
