package br.com.metascale.domain;

import java.util.Date;

import org.springframework.boot.context.properties.bind.DefaultValue;

import br.com.metascale.domain.entity.CustomerProduct;

public record CustomerProductDTO(
		Integer customer_product_id,
		Integer customer_id,
		Integer product_id,
		Date association_date,
		String feedback,
		@DefaultValue(value = "ACTIVE") String status
) {
	
	public CustomerProductDTO(CustomerProduct customerProduct) {
		this(customerProduct.getCustomer_product_id(), customerProduct.getCustomer_id(), customerProduct.getProduct_id(), customerProduct.getAssociation_date(), customerProduct.getFeedback(), customerProduct.getStatus().getStatus());
	}

}
