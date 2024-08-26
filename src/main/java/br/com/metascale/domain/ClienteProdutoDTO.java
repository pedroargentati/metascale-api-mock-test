package br.com.metascale.domain;

import java.util.Date;

import br.com.metascale.domain.entity.ClienteProduto;

public record ClienteProdutoDTO(
		Integer cliente_produto_id,
		Integer cliente_id,
		Integer produto_id,
		Date data_associacao,
		String feedback
) {
	
	public ClienteProdutoDTO(ClienteProduto cliente) {
		this(cliente.getCliente_produto_id(), cliente.getCliente_id(), cliente.getProduto_id(), cliente.getData_associacao(), cliente.getFeedback());
	}

}
