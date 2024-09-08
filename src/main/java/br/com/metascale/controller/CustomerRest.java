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

import br.com.metascale.domain.CustomerDTO;
import br.com.metascale.service.CustomerService;

@RestController
@RequestMapping("/api/metascale/customer")
public class CustomerRest {

	@Autowired
	private CustomerService customerService;

	@GetMapping("")
	public ResponseEntity<List<CustomerDTO>> get() {
		var clientes = customerService.getAll();
		return (clientes == null || !clientes.isEmpty())
				? ResponseEntity.ok(clientes)
				: ResponseEntity.noContent().build();
	}

	@GetMapping("/{customer_id}")
	public ResponseEntity<CustomerDTO> get(@PathVariable Integer customer_id) {
		var clientes = customerService.getBydId(customer_id);
		return clientes != null
				? ResponseEntity.ok(clientes)
				: ResponseEntity.notFound().build();
	}

	@PostMapping("/")
	public ResponseEntity<CustomerDTO> createCliente(@RequestBody CustomerDTO clienteDTO) {
		CustomerDTO savedCliente = customerService.create(clienteDTO);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/api/metascale/customer/{customer_id}")
				.buildAndExpand(savedCliente.customer_id()).toUri();

		return ResponseEntity.created(location).body(savedCliente);
	}

	@PutMapping("/{customer_id}")
	public ResponseEntity<CustomerDTO> change(@PathVariable Integer customer_id, @RequestBody CustomerDTO clientes) {
		var clientesUpdated = customerService.update(clientes, customer_id);
		return ResponseEntity.ok(clientesUpdated);
	}
}
