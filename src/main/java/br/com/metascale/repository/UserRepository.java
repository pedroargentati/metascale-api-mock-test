package br.com.metascale.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.metascale.domain.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

}
