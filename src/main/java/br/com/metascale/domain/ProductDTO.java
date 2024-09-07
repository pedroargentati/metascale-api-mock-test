package br.com.metascale.domain;

import java.util.Date;

import org.springframework.boot.context.properties.bind.DefaultValue;

import br.com.metascale.domain.entity.Product;

public record ProductDTO(
	Integer product_id,
	String product_name,
	String description,
	Date release_date,
	@DefaultValue(value = "MOBILE") String product_type
) {
	
	public ProductDTO(Product product) {
		this(product.getId(), product.getProduct_name(), product.getDescription(), product.getRelease_date(), product.getProduct_type().getType());
	}
	
}
