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

import br.com.metascale.domain.ProdutoDTO;
import br.com.metascale.service.ProdutoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/metascale/produto")
public class ProdutoRest {

	@Autowired
	private ProdutoService produtoService;

	@GetMapping("/")
	public ResponseEntity<List<ProdutoDTO>> get() {
		var clientes = produtoService.getAll();
		return (clientes == null || !clientes.isEmpty())
				? ResponseEntity.ok(clientes)
				: ResponseEntity.noContent().build();
	}

	@GetMapping("/{produto_id}")
	public ResponseEntity<ProdutoDTO> get(@PathVariable Integer produto_id) {
		var produto = produtoService.getBydId(produto_id);
		return produto != null
				? ResponseEntity.ok(produto)
				: ResponseEntity.notFound().build();
	}

	@PostMapping("/")
	public ResponseEntity<ProdutoDTO> create(@Valid @RequestBody ProdutoDTO produto) {
		var produtoSaved = produtoService.create(produto);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/api/metascale/produto/{produto_id}")
				.buildAndExpand(produtoSaved.produto_id())
				.toUri();

		return ResponseEntity.created(location).body(produtoSaved);
	}

	@PutMapping("/{produto_id}")
	public ResponseEntity<ProdutoDTO> change(@PathVariable Integer produto_id, @RequestBody ProdutoDTO produto) {
		var produtoUpdated = produtoService.update(produto, produto_id);

		return ResponseEntity.ok(produtoUpdated);

	}

}
