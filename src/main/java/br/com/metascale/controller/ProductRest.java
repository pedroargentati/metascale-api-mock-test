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

import br.com.metascale.domain.ProductDTO;
import br.com.metascale.service.ProductService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/metascale/product")
public class ProductRest {

	@Autowired
	private ProductService productService;

	@GetMapping("")
	public ResponseEntity<List<ProductDTO>> get() {
		var clientes = productService.getAll();
		return (clientes == null || !clientes.isEmpty())
				? ResponseEntity.ok(clientes)
				: ResponseEntity.noContent().build();
	}

	@GetMapping("/{product_id}")
	public ResponseEntity<ProductDTO> get(@PathVariable Integer product_id) {
		var produto = productService.getBydId(product_id);
		return produto != null
				? ResponseEntity.ok(produto)
				: ResponseEntity.notFound().build();
	}

	@PostMapping("/")
	public ResponseEntity<ProductDTO> create(@Valid @RequestBody ProductDTO produto) {
		var produtoSaved = productService.create(produto);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/api/metascale/product/{product_id}")
				.buildAndExpand(produtoSaved.product_id())
				.toUri();

		return ResponseEntity.created(location).body(produtoSaved);
	}

	@PutMapping("/{product_id}")
	public ResponseEntity<ProductDTO> change(@PathVariable Integer product_id, @RequestBody ProductDTO produto) {
		var produtoUpdated = productService.update(produto, product_id);

		return ResponseEntity.ok(produtoUpdated);

	}

}
