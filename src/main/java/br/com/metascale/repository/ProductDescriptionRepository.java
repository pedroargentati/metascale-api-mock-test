package br.com.metascale.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.metascale.domain.entity.ProductDescription;

public interface ProductDescriptionRepository extends JpaRepository<ProductDescription, String> {
	@Query("SELECT d from ProductDescription d where d.id = :pd")
	List<ProductDescription> findByProductId(@Param(value = "pd") String pd);
}
