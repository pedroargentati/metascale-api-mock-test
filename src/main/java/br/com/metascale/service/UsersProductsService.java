package br.com.metascale.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.metascale.domain.UserProductsDTO;
import br.com.metascale.domain.entity.UserProduct;
import br.com.metascale.repository.CustomerProductRepository;

@Service
public class UsersProductsService {

	@Autowired
	private CustomerProductRepository clienteProdutoRepository;

	public List<UserProductsDTO> getAll() {
		return clienteProdutoRepository.findAll()
				.stream()
				.map(UserProductsDTO::new)
				.collect(Collectors.toList());
	}
	
	public UserProductsDTO getBydId(Integer cliente_id) {
		var clienteProduto = clienteProdutoRepository.findById(cliente_id);

		return clienteProduto.isPresent() ? new UserProductsDTO(clienteProduto.get()) : null;
	}

	public UserProductsDTO create(UserProductsDTO cliente) {
		var clienteSaved = clienteProdutoRepository.save(new UserProduct(cliente));

		return new UserProductsDTO(clienteSaved);
	}

	public UserProductsDTO update(UserProductsDTO cliente, Integer cliente_id) {
		var optionalCliente = clienteProdutoRepository.findById(cliente_id);
		if (!optionalCliente.isPresent()) {
			return null;
		}

		var clienteExistente = optionalCliente.get();

		clienteExistente.updateCustomerProduct(cliente);
		clienteProdutoRepository.save(clienteExistente);

		return new UserProductsDTO(clienteExistente);
	}

}
