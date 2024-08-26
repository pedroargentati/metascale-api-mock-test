package br.com.metascale.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.metascale.domain.ClientesDTO;
import br.com.metascale.domain.entity.Clientes;
import br.com.metascale.repository.ClientesRepository;

@Service
public class ClientesService {

	@Autowired
	private ClientesRepository clientesRepository;
	
	public ClientesDTO getBydId(Integer cliente_id) {
		var clientes = clientesRepository.findById(cliente_id);
		
		return clientes.isPresent() ? new ClientesDTO(clientes.get()) : null;
	}
	
	public ClientesDTO create(ClientesDTO cliente) {
		var clienteSaved = clientesRepository.save(new Clientes(cliente));

		return new ClientesDTO(clienteSaved);
	}
	
	public ClientesDTO update(ClientesDTO cliente, Integer cliente_id) {
		var optionalCliente = clientesRepository.findById(cliente_id);
		if (!optionalCliente.isPresent()) {
			return null;
		}
		
		var clienteExistente = optionalCliente.get();
		
		clienteExistente.updateCliente(cliente);
		clientesRepository.save(clienteExistente);
		
		return new ClientesDTO(clienteExistente);
	}
	
}
