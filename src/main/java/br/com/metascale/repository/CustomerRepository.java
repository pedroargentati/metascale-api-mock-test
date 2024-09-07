package br.com.metascale.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.metascale.domain.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
