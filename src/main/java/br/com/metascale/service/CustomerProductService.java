package br.com.metascale.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.metascale.domain.CustomerProductDTO;
import br.com.metascale.domain.entity.CustomerProduct;
import br.com.metascale.repository.CustomerProductRepository;

@Service
public class CustomerProductService {

	@Autowired
	private CustomerProductRepository clienteProdutoRepository;

	public List<CustomerProductDTO> getAll() {
		return clienteProdutoRepository.findAll()
				.stream()
				.map(CustomerProductDTO::new)
				.collect(Collectors.toList());
	}

	public CustomerProductDTO getBydId(Integer cliente_id) {
		var clienteProduto = clienteProdutoRepository.findById(cliente_id);

		return clienteProduto.isPresent() ? new CustomerProductDTO(clienteProduto.get()) : null;
	}

	public CustomerProductDTO create(CustomerProductDTO cliente) {
		var clienteSaved = clienteProdutoRepository.save(new CustomerProduct(cliente));

		return new CustomerProductDTO(clienteSaved);
	}

	public CustomerProductDTO update(CustomerProductDTO cliente, Integer cliente_id) {
		var optionalCliente = clienteProdutoRepository.findById(cliente_id);
		if (!optionalCliente.isPresent()) {
			return null;
		}

		var clienteExistente = optionalCliente.get();

		clienteExistente.updateCustomerProduct(cliente);
		clienteProdutoRepository.save(clienteExistente);

		return new CustomerProductDTO(clienteExistente);
	}

}
