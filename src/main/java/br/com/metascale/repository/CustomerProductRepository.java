package br.com.metascale.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.metascale.domain.entity.UserProduct;

public interface CustomerProductRepository extends JpaRepository<UserProduct, Integer>{

}
