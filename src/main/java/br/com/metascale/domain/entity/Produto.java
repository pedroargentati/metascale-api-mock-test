package br.com.metascale.domain.entity;

import java.util.Date;

import br.com.metascale.constants.Status;
import br.com.metascale.domain.ProdutoDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "produtos")
@Entity(name = "Produto")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer produto_id;
	private String nome_produto;
	private String descricao;
	private Date data_lancamento = new Date();
	private Status status;
	
	public Produto() {}
	
	public Produto(ProdutoDTO produto) {
		this.produto_id = produto.produto_id();
		this.nome_produto = produto.nome_produto();
		this.descricao = produto.descricao();
		this.data_lancamento = produto.data_lancamento();
		this.status = produto.status();
	}
	
	public void updateProduto(ProdutoDTO produto) {
		if (produto.nome_produto() != null) {
			this.nome_produto = produto.nome_produto();			
		}

		if (produto.descricao() != null) {
			this.descricao = produto.descricao();			
		}
		
		if (produto.status() != null) {
			this.status = produto.status();			
		}

	}

	public Integer getProduto_id() {
		return produto_id;
	}

	public void setProduto_id(Integer produto_id) {
		this.produto_id = produto_id;
	}

	public String getNome_produto() {
		return nome_produto;
	}

	public void setNome_produto(String nome_produto) {
		this.nome_produto = nome_produto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getData_lancamento() {
		return data_lancamento == null ? new Date() : data_lancamento;
	}

	public void setData_lancamento(Date data_lancamento) {
		this.data_lancamento = data_lancamento;
	}

	public Status getStatus() {
		return status == null ? Status.EM_ANDAMENTO : status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Produto [produto_id=" + produto_id + ", nome_produto=" + nome_produto + "]";
	}
	
	
}
