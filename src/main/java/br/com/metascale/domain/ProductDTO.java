package br.com.metascale.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.bind.DefaultValue;

import br.com.metascale.domain.entity.Product;

public record ProductDTO(
	String id,
	String product_name,
	@DefaultValue(value = "MOBILE") String product_type,
	List<DescriptionDTO> descriptions
) {
	
	public ProductDTO(Product product) {
		this(product.getId(), product.getProduct_name(), product.getProduct_type().getType(), new ArrayList<>());
	}
	
	public ProductDTO(Product product, List<DescriptionDTO> descriptions) {
		this(product.getId(), product.getProduct_name(), product.getProduct_type().getType(), descriptions);
	}
	
}
