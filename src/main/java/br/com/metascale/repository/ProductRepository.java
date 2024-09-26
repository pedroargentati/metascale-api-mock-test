package br.com.metascale.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.metascale.domain.entity.Product;

public interface ProductRepository extends JpaRepository<Product, String> {
	@Query("SELECT p from Product p where p.parent_id = :pid")
	List<Product> findByParentId(@Param(value = "pid") String pid);
}
