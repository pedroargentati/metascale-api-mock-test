package br.com.metascale.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.metascale.domain.ClienteProdutoDTO;
import br.com.metascale.service.ClienteProdutoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/metascale/clienteproduto")
public class ClienteProdutoRest {

    @Autowired
    private ClienteProdutoService clienteProdutoService;

    @GetMapping("/{cliente_produto_id}")
    public ResponseEntity<ClienteProdutoDTO> get(@PathVariable Integer cliente_produto_id) {
        var clientes = clienteProdutoService.getBydId(cliente_produto_id);
        return clientes != null ? ResponseEntity.ok(clientes) : ResponseEntity.notFound().build();
    }

    @PostMapping("/")
    public ResponseEntity<ClienteProdutoDTO> create(@Valid @RequestBody ClienteProdutoDTO clientes) {
        var clientesSaved = clienteProdutoService.create(clientes);
        return ResponseEntity.ok(clientesSaved);
    }
    
    @PutMapping("/{cliente_produto_id}")
    public ResponseEntity<ClienteProdutoDTO> change(@PathVariable Integer cliente_produto_id, @RequestBody ClienteProdutoDTO clientes) {
        var clientesUpdated = clienteProdutoService.update(clientes, cliente_produto_id);
        return ResponseEntity.ok(clientesUpdated);
    }
	
}
