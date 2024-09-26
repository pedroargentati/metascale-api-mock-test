package br.com.metascale.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.metascale.domain.UsersDTO;
import br.com.metascale.domain.entity.User;
import br.com.metascale.repository.UserRepository;

@Service
public class UsersService {

	@Autowired
	private UserRepository customerRepository;

	public List<UsersDTO> getAll() {
		return customerRepository.findAll()
				.stream()
				.map(UsersDTO::new)
				.collect(Collectors.toList());
	}
	
	public UsersDTO getBydId(Integer user_id) {
		var user = customerRepository.findById(user_id);
		
		return user.isPresent() ? new UsersDTO(user.get()) : null;
	}
	
	public UsersDTO create(UsersDTO user) {
		var userSaved = customerRepository.save(new User(user));

		return new UsersDTO(userSaved);
	}
	
	public UsersDTO update(UsersDTO user, Integer user_id) {
		var optionalUser = customerRepository.findById(user_id);
		if (!optionalUser.isPresent()) {
			return null;
		}
		
		var existingUser = optionalUser.get();
		
		existingUser.updateCustomer(user);
		customerRepository.save(existingUser);
		
		return new UsersDTO(existingUser);
	}
	
}
