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

import br.com.metascale.core.exceptions.RecordNotFoundException;
import br.com.metascale.domain.UserProductsDTO;
import br.com.metascale.service.UsersProductsService;

@RestController
@RequestMapping("/api/metascale/userproducts")
public class UserProductsRest {

	@Autowired
	private UsersProductsService userProductService;
	
	@GetMapping("")
	public ResponseEntity<List<UserProductsDTO>> getAll() {
		var userProduct = userProductService.getAll();
		return ResponseEntity.ok(userProduct);
	}

	@GetMapping("/{user_id}/{product_id}")
	public ResponseEntity<UserProductsDTO> get(@PathVariable String user_id, @PathVariable String product_id) throws RecordNotFoundException {
		var user = userProductService.getById(user_id, product_id);
		return user != null
				? ResponseEntity.ok(user)
				: ResponseEntity.notFound().build();
	}

	@PostMapping("")
	public ResponseEntity<UserProductsDTO> createUserProduct(@RequestBody UserProductsDTO userProductsDTO) {
		UserProductsDTO userProductsSaved = userProductService.create(userProductsDTO);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/api/metascale/product/{product_id}")
				.buildAndExpand(userProductsSaved.id()).toUri();

		return ResponseEntity.created(location).body(userProductsSaved);
	}
	
	@PutMapping("")
	public ResponseEntity<UserProductsDTO> change(@PathVariable String user_id, @RequestBody UserProductsDTO user) {
		var userProductUpdated = userProductService.update(user, user_id);
		return ResponseEntity.ok(userProductUpdated);
	}

}
