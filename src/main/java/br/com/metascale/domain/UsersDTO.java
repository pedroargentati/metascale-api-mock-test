package br.com.metascale.domain;

import br.com.metascale.domain.entity.User;
import jakarta.validation.constraints.Email;

public record UsersDTO(
		String user_id,
		String name,
		@Email String email,
		String phone,
		String city,
		String state
) {
	
	public UsersDTO(User customer) {
		this(customer.getId(), customer.getName(), customer.getEmail(), customer.getPhone(), customer.getCity(), customer.getState());
	}

}
