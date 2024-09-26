package br.com.metascale.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.metascale.domain.UserProductsDTO;
import br.com.metascale.domain.UsersDTO;
import br.com.metascale.service.UsersProductsService;
import br.com.metascale.service.UsersService;

@RestController
@RequestMapping("/api/metascale/users")
public class UsersRest {

	@Autowired
	private UsersService usersService;
	
	@Autowired
	private UsersProductsService usersProductService;

	@GetMapping("/{user_id}/products")
	public ResponseEntity<List<UserProductsDTO>> getUserProducts(@PathVariable String user_id) {
	    var userProducts = usersProductService.getAllUserProducts(user_id);
	    return (userProducts == null || userProducts.isEmpty())
	            ? ResponseEntity.noContent().build()
	            : ResponseEntity.ok(userProducts);
	}


	@GetMapping("/")
	public ResponseEntity<List<UsersDTO>> getAll() {
		var user = usersService.getAll();
		return (user == null || !user.isEmpty())
				? ResponseEntity.ok(user)
				: ResponseEntity.noContent().build();
	}

	@GetMapping("/{user_id}")
	public ResponseEntity<UsersDTO> get(@PathVariable Integer user_id) {
		var user = usersService.getBydId(user_id);
		return user != null
				? ResponseEntity.ok(user)
				: ResponseEntity.notFound().build();
	}

	@PostMapping("/")
	public ResponseEntity<UsersDTO> createCliente(@RequestBody UsersDTO clienteDTO) {
		UsersDTO userSaved = usersService.create(clienteDTO);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/api/metascale/users/{user_id}")
				.buildAndExpand(userSaved.user_id()).toUri();

		return ResponseEntity.created(location).body(userSaved);
	}

	@PutMapping("/{user_id}")
	public ResponseEntity<UsersDTO> change(@PathVariable Integer user_id, @RequestBody UsersDTO user) {
		var userUpdated = usersService.update(user, user_id);
		return ResponseEntity.ok(userUpdated);
	}

}
