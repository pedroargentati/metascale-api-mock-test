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

import br.com.metascale.domain.CustomerProductDTO;
import br.com.metascale.service.CustomerProductService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/metascale/customerproduct")
public class CustomerProductRest {

	@Autowired
	private CustomerProductService customerProductService;

	@GetMapping("")
	public ResponseEntity<List<CustomerProductDTO>> get() {
		var clientes = customerProductService.getAll();
		return (clientes != null && !clientes.isEmpty())
				? ResponseEntity.ok(clientes)
				: ResponseEntity.noContent().build();
	}

	@GetMapping("/{customer_product_id}")
	public ResponseEntity<CustomerProductDTO> get(@PathVariable Integer customer_product_id) {
		var clientes = customerProductService.getBydId(customer_product_id);
		return clientes != null
				? ResponseEntity.ok(clientes)
				: ResponseEntity.notFound().build();
	}

	@PostMapping("/")
	public ResponseEntity<CustomerProductDTO> create(@Valid @RequestBody CustomerProductDTO clientes) {
		CustomerProductDTO clienteProdutoSaved = customerProductService.create(clientes);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/api/metascale/customerproduct/{customer_product_id}")
				.buildAndExpand(clienteProdutoSaved.customer_id())
				.toUri();

		return ResponseEntity.created(location).body(clienteProdutoSaved);
	}

	@PutMapping("/{customer_product_id}")
	public ResponseEntity<CustomerProductDTO> change(@PathVariable Integer customer_product_id,
			@RequestBody CustomerProductDTO clientes) {
		var customerUpdated = customerProductService.update(clientes, customer_product_id);
		return ResponseEntity.ok(customerUpdated);
	}

}
