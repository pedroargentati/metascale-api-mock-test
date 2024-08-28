package br.com.metascale.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.metascale.domain.ClienteProdutoDTO;
import br.com.metascale.domain.entity.ClienteProduto;
import br.com.metascale.repository.ClienteProdutoRepository;

@Service
public class ClienteProdutoService {

	@Autowired
	private ClienteProdutoRepository clienteProdutoRepository;

	public List<ClienteProdutoDTO> getAll() {
		return clienteProdutoRepository.findAll()
				.stream()
				.map(ClienteProdutoDTO::new)
				.collect(Collectors.toList());
	}

	public ClienteProdutoDTO getBydId(Integer cliente_id) {
		var clienteProduto = clienteProdutoRepository.findById(cliente_id);

		return clienteProduto.isPresent() ? new ClienteProdutoDTO(clienteProduto.get()) : null;
	}

	public ClienteProdutoDTO create(ClienteProdutoDTO cliente) {
		var clienteSaved = clienteProdutoRepository.save(new ClienteProduto(cliente));

		return new ClienteProdutoDTO(clienteSaved);
	}

	public ClienteProdutoDTO update(ClienteProdutoDTO cliente, Integer cliente_id) {
		var optionalCliente = clienteProdutoRepository.findById(cliente_id);
		if (!optionalCliente.isPresent()) {
			return null;
		}

		var clienteExistente = optionalCliente.get();

		clienteExistente.updateClienteProduto(cliente);
		clienteProdutoRepository.save(clienteExistente);

		return new ClienteProdutoDTO(clienteExistente);
	}

}
