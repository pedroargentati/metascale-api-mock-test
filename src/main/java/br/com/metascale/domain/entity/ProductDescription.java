package br.com.metascale.domain.entity;

import br.com.metascale.constants.Category;
import br.com.metascale.domain.DescriptionDTO;
import br.com.metascale.domain.ProductDTO;
import br.com.metascale.domain.ProductDescriptionDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "productdescription")
@Entity(name = "ProductDescription")
public class ProductDescription {
	
	@Id
	private String id;
	private String text;
	private String url;
	@Enumerated(EnumType.STRING)
	private Category category;
	
	public ProductDescription() {}
	
	public ProductDescription(ProductDescriptionDTO productDescription) {
		this.id = productDescription.id();
		this.text = productDescription.text();
		this.url = productDescription.url();
		this.category = productDescription.category();
	}
	
	public ProductDescription(ProductDTO produto, DescriptionDTO desc) {
		this.id = produto.id();
		this.text = desc.text();
		this.url = desc.url();
		this.category = Category.of(desc.category());
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
