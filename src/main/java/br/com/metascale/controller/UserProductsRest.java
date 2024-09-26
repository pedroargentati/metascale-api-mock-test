package br.com.metascale.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.metascale.domain.UserProductsDTO;
import br.com.metascale.service.UsersProductsService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/metascale/userproducts")
public class UserProductsRest {

	@Autowired
	private UsersProductsService userProductService;

	@PostMapping("/")
	public ResponseEntity<UserProductsDTO> create(@Valid @RequestBody UserProductsDTO clientes) {
		UserProductsDTO clienteProdutoSaved = userProductService.create(clientes);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/api/metascale/userproducts/{customer_product_id}")
				.buildAndExpand(clienteProdutoSaved.id())
				.toUri();

		return ResponseEntity.created(location).body(clienteProdutoSaved);
	}

	@PutMapping("/{customer_product_id}")
	public ResponseEntity<UserProductsDTO> change(@PathVariable String customer_product_id, @RequestBody UserProductsDTO user) {
		var userProductUpdated = userProductService.update(user, customer_product_id);
		return ResponseEntity.ok(userProductUpdated);
	}

}
