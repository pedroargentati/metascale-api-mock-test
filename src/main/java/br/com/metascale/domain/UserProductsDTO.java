package br.com.metascale.domain;

import java.util.List;

import org.springframework.boot.context.properties.bind.DefaultValue;

import br.com.metascale.domain.entity.Product;
import br.com.metascale.domain.entity.UserProduct;

public record UserProductsDTO(
	    String id,
	    String product_name,
	    String product_type,
	    @DefaultValue(value = "ACTIVE") String status,
	    String start_date,
	    List<String> identifiers,
	    List<DescriptionDTO> descriptions,
	    List<ProductDTO> sub_products,
	    PriceDTO price
		
) {
	
	public UserProductsDTO(UserProduct dto) {
		this(dto.getProduct_id(), null, null, dto.getStatus().getStatus(), null, null, null, null, null);
	}
	
	public UserProductsDTO(UserProduct userProduct, Product product) {
		this(userProduct.getProduct_id(), product.getProduct_name(), product.getProduct_type().getType(), userProduct.getStatus().getStatus(), userProduct.getStart_date().toString(), null, null, null, null);
	}

}
