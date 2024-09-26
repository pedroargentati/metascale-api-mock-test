package br.com.metascale.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.metascale.domain.entity.Product;

public interface ProductRepository extends JpaRepository<Product, String> {

}
