package br.com.metascale.domain;

import java.util.Date;

import org.springframework.boot.context.properties.bind.DefaultValue;

import br.com.metascale.constants.Status;
import br.com.metascale.domain.entity.Produto;

public record ProdutoDTO(
	Integer produto_id,
	String nome_produto,
	String descricao,
	Date data_lancamento,
	@DefaultValue(value = "EM_ANDAMENTO") Status status
) {
	
	public ProdutoDTO(Produto produto) {
		this(produto.getProduto_id(), produto.getNome_produto(), produto.getDescricao(), produto.getData_lancamento(), produto.getStatus());
	}
	
}
