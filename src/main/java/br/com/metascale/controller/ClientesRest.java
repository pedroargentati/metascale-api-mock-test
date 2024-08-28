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

import br.com.metascale.domain.ClientesDTO;
import br.com.metascale.service.ClientesService;

@RestController
@RequestMapping("/api/metascale/clientes")
public class ClientesRest {

	@Autowired
	private ClientesService clientesService;

	@GetMapping("/")
	public ResponseEntity<List<ClientesDTO>> get() {
		var clientes = clientesService.getAll();
		return (clientes == null || !clientes.isEmpty())
				? ResponseEntity.ok(clientes)
				: ResponseEntity.noContent().build();
	}

	@GetMapping("/{clientes_id}")
	public ResponseEntity<ClientesDTO> get(@PathVariable Integer clientes_id) {
		var clientes = clientesService.getBydId(clientes_id);
		return clientes != null
				? ResponseEntity.ok(clientes)
				: ResponseEntity.notFound().build();
	}

	@PostMapping("/")
	public ResponseEntity<ClientesDTO> createCliente(@RequestBody ClientesDTO clienteDTO) {
		ClientesDTO savedCliente = clientesService.create(clienteDTO);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/api/metascale/clientes/{clientes_id}")
				.buildAndExpand(savedCliente.cliente_id()).toUri();

		return ResponseEntity.created(location).body(savedCliente);
	}

	@PutMapping("/{clientes_id}")
	public ResponseEntity<ClientesDTO> change(@PathVariable Integer clientes_id, @RequestBody ClientesDTO clientes) {
		var clientesUpdated = clientesService.update(clientes, clientes_id);
		return ResponseEntity.ok(clientesUpdated);
	}
}
