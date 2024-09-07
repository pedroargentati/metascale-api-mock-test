package br.com.metascale.domain;

import br.com.metascale.domain.entity.Customer;
import jakarta.validation.constraints.Email;

public record CustomerDTO(
		Integer customer_id,
		String customer_name,
		@Email String customer_email,
		String customer_phone,
		String city,
		String state
) {
	
	public CustomerDTO(Customer customer) {
		this(customer.getCustomer_id(), customer.getCustomer_name(), customer.getCustomer_email(), customer.getCustomer_phone(), customer.getCity(), customer.getState());
	}

}
