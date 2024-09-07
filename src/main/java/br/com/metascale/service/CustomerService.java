package br.com.metascale.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.metascale.domain.CustomerDTO;
import br.com.metascale.domain.entity.Customer;
import br.com.metascale.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	public List<CustomerDTO> getAll() {
		return customerRepository.findAll()
				.stream()
				.map(CustomerDTO::new)
				.collect(Collectors.toList());
	}
	
	public CustomerDTO getBydId(Integer cliente_id) {
		var clientes = customerRepository.findById(cliente_id);
		
		return clientes.isPresent() ? new CustomerDTO(clientes.get()) : null;
	}
	
	public CustomerDTO create(CustomerDTO cliente) {
		var clienteSaved = customerRepository.save(new Customer(cliente));

		return new CustomerDTO(clienteSaved);
	}
	
	public CustomerDTO update(CustomerDTO cliente, Integer cliente_id) {
		var optionalCliente = customerRepository.findById(cliente_id);
		if (!optionalCliente.isPresent()) {
			return null;
		}
		
		var clienteExistente = optionalCliente.get();
		
		clienteExistente.updateCustomer(cliente);
		customerRepository.save(clienteExistente);
		
		return new CustomerDTO(clienteExistente);
	}
	
}
