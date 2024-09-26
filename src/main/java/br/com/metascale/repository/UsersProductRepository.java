package br.com.metascale.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.metascale.domain.entity.UserProduct;

public interface UsersProductRepository extends JpaRepository<UserProduct, String> {
	@Query("SELECT u from UserProduct u where u.user_id = :ui")
	List<UserProduct> findByUserId(@Param(value = "ui") String ui);
	
    // Busca os user_id dos usuários que possuem um determinado produto
    @Query("SELECT u.user_id FROM UserProduct u WHERE u.product_id = :productId")
    List<String> findUserIdsByProductId(String productId);
	
}
