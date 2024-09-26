package br.com.metascale.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.metascale.domain.entity.UserProduct;

public interface UsersProductRepository extends JpaRepository<UserProduct, String> {
	@Query("SELECT u from UserProduct u where u.user_id = :ui")
	List<UserProduct> findByuser_id(@Param(value = "ui") String ui);
}
