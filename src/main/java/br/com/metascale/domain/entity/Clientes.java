package br.com.metascale.domain.entity;

import br.com.metascale.domain.ClientesDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "clientes")
@Entity(name = "Clientes")
public class Clientes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cliente_id;
	private String nome_cliente;
	private String email_cliente;
	private String telefone_cliente;
	private String cidade;
	private String estado;
	
	public Clientes() {}
	
	public Clientes(ClientesDTO clientes) {
		this.cliente_id = clientes.cliente_id();
		this.nome_cliente = clientes.nome_cliente();
		this.email_cliente = clientes.email_cliente();
		this.telefone_cliente = clientes.telefone_cliente();
		this.cidade = clientes.cidade();
		this.estado = clientes.estado();
	}
	
	public void updateCliente(ClientesDTO cliente) {
		if (cliente.nome_cliente() != null) {
			this.nome_cliente = cliente.nome_cliente();
		}
		
		if (cliente.email_cliente() != null) {
			this.email_cliente = cliente.email_cliente();
		}
		
		if (cliente.telefone_cliente() != null) {
			this.telefone_cliente = cliente.telefone_cliente();
		}
		
		if (cliente.cidade() != null) {
			this.cidade = cliente.cidade();
		}
		
		if (cliente.estado() != null) {
			this.estado = cliente.estado();
		}
		
	}

	public Integer getCliente_id() {
		return cliente_id;
	}

	public void setCliente_id(Integer cliente_id) {
		this.cliente_id = cliente_id;
	}

	public String getNome_cliente() {
		return nome_cliente;
	}

	public void setNome_cliente(String nome_cliente) {
		this.nome_cliente = nome_cliente;
	}

	public String getEmail_cliente() {
		return email_cliente;
	}

	public void setEmail_cliente(String email_cliente) {
		this.email_cliente = email_cliente;
	}

	public String getTelefone_cliente() {
		return telefone_cliente;
	}

	public void setTelefone_cliente(String telefone_cliente) {
		this.telefone_cliente = telefone_cliente;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}
