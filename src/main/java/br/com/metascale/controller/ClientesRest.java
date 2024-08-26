package br.com.metascale.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.metascale.domain.ClientesDTO;
import br.com.metascale.service.ClientesService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/metascale/clientes")
public class ClientesRest {

    @Autowired
    private ClientesService clientesService;

    @GetMapping("/{clientes_id}")
    public ResponseEntity<ClientesDTO> get(@PathVariable Integer clientes_id) {
        var clientes = clientesService.getBydId(clientes_id);
        return clientes != null ? ResponseEntity.ok(clientes) : ResponseEntity.notFound().build();
    }

    @PostMapping("/")
    public ResponseEntity<ClientesDTO> create(@Valid @RequestBody ClientesDTO clientes) {
        var clientesSaved = clientesService.create(clientes);
        return ResponseEntity.ok(clientesSaved);
    }
    
    @PutMapping("/{clientes_id}")
    public ResponseEntity<ClientesDTO> change(@PathVariable Integer clientes_id, @RequestBody ClientesDTO clientes) {
        var clientesUpdated = clientesService.update(clientes, clientes_id);
        return ResponseEntity.ok(clientesUpdated);
    }
}
