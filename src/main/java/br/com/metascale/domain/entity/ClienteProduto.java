package br.com.metascale.domain.entity;

import java.util.Date;

import br.com.metascale.domain.ClienteProdutoDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "Clienteproduto")
@Entity(name = "ClienteProduto")
public class ClienteProduto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cliente_produto_id;
	private Integer cliente_id;
	private Integer produto_id;
	private Date data_associacao;
	private String feedback;

	public ClienteProduto() {}
	
	public ClienteProduto(ClienteProdutoDTO clienteProduto) {
		this.cliente_produto_id = clienteProduto.cliente_produto_id();
		this.cliente_id = clienteProduto.cliente_id();
		this.produto_id = clienteProduto.produto_id();
		this.data_associacao = clienteProduto.data_associacao();
		this.feedback = clienteProduto.feedback();
	}
	
	public void updateClienteProduto(ClienteProdutoDTO clienteProduto) {
		if (feedback != null) {
			this.feedback = clienteProduto.feedback();
		}
	}

	public Integer getCliente_produto_id() {
		return cliente_produto_id;
	}

	public void setCliente_produto_id(Integer cliente_produto_id) {
		this.cliente_produto_id = cliente_produto_id;
	}

	public Integer getCliente_id() {
		return cliente_id;
	}

	public void setCliente_id(Integer cliente_id) {
		this.cliente_id = cliente_id;
	}

	public Integer getProduto_id() {
		return produto_id;
	}

	public void setProduto_id(Integer produto_id) {
		this.produto_id = produto_id;
	}

	public Date getData_associacao() {
		return data_associacao == null ? new Date() : data_associacao;
	}

	public void setData_associacao(Date data_associacao) {
		this.data_associacao = data_associacao;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	
	
}
