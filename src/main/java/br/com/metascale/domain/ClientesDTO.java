package br.com.metascale.domain;

import br.com.metascale.domain.entity.Clientes;
import jakarta.validation.constraints.Email;

public record ClientesDTO(
		Integer cliente_id,
		String nome_cliente,
		@Email String email_cliente,
		String telefone_cliente,
		String cidade,
		String estado
) {
	
	public ClientesDTO(Clientes clientes) {
		this(clientes.getCliente_id(), clientes.getNome_cliente(), clientes.getEmail_cliente(), clientes.getTelefone_cliente(), clientes.getCidade(), clientes.getEstado());
	}

}
