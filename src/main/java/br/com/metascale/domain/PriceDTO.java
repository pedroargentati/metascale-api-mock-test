package br.com.metascale.domain;

import org.springframework.boot.context.properties.bind.DefaultValue;

import br.com.metascale.domain.entity.UserProduct;
import jakarta.validation.constraints.Pattern;

public record PriceDTO(
		String description,
		@DefaultValue(value = "recurring") String type,
		@Pattern(regexp = "^(daily|weekly|monthly|yearly|\\d{1,4}-(days|hours))$") String recurring_period,
		AmountDTO amount
) {
	
	public PriceDTO(UserProduct up) {
		this(up.getDescription(), up.getType(), up.getRecurring_period(), new AmountDTO(up.getValue()));
	}

}