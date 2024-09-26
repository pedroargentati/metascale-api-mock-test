package br.com.metascale.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.metascale.domain.UsersDTO;
import br.com.metascale.domain.entity.User;
import br.com.metascale.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class UsersService {

	@Autowired
	private UserRepository userRepository;

	public List<UsersDTO> getAll() {
		return userRepository.findAll()
				.stream()
				.map(UsersDTO::new)
				.collect(Collectors.toList());
	}
	
    public User findUserById(String id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + id));
    }
	
	public UsersDTO getBydId(String user_id) {
		var user = userRepository.findById(user_id);
		
		return user.isPresent() ? new UsersDTO(user.get()) : null;
	}
	
	public UsersDTO create(UsersDTO user) {
		var userSaved = userRepository.save(new User(user));

		return new UsersDTO(userSaved);
	}
	
	public UsersDTO update(UsersDTO user, String user_id) {
		var optionalUser = userRepository.findById(user_id);
		if (!optionalUser.isPresent()) {
			return null;
		}
		
		var existingUser = optionalUser.get();
		
		existingUser.updateCustomer(user);
		userRepository.save(existingUser);
		
		return new UsersDTO(existingUser);
	}
	
}
