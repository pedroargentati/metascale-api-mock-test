package br.com.metascale.domain;

import org.springframework.boot.context.properties.bind.DefaultValue;

import br.com.metascale.domain.entity.ProductDescription;

public record DescriptionDTO(
		String text,
		String url,
		@DefaultValue(value = "GENERAL") String category
) {
	public DescriptionDTO(ProductDescription productDescription) {
		this(productDescription.getText(), productDescription.getUrl(), productDescription.getCategory().getType());
	}
}