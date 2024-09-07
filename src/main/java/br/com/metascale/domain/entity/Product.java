package br.com.metascale.domain.entity;

import java.util.Date;

import br.com.metascale.constants.ProductType;
import br.com.metascale.domain.ProductDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "product")
@Entity(name = "Product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String product_name;
	private String description;
	private Date release_date;
	
	@Enumerated(EnumType.STRING)
	private ProductType product_type;
	
	public Product() {}
	
	public Product(ProductDTO produto) {
		this.id = produto.product_id();
		this.product_name = produto.product_name();
		this.description = produto.description();
		this.release_date = produto.release_date();
		this.product_type = ProductType.of(produto.product_type());
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getRelease_date() {
		return release_date;
	}

	public void setRelease_date(Date release_date) {
		this.release_date = release_date;
	}

	public ProductType getProduct_type() {
		return product_type;
	}

	public void setProduct_type(ProductType product_type) {
		this.product_type = product_type;
	}
	
	public void updateProduto(ProductDTO produto) {
		if (produto.product_name() != null) {
			this.product_name = produto.product_name();			
		}

		if (produto.description() != null) {
			this.description = produto.description();			
		}
		
		if (produto.product_type() != null) {
			this.product_type = ProductType.of(produto.product_type());			
		}

	}

	
}
