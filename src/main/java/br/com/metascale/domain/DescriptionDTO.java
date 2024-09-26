package br.com.metascale.domain;

import org.springframework.boot.context.properties.bind.DefaultValue;

public record DescriptionDTO(
		String text,
		String url,
		@DefaultValue(value = "GENERAL") String category
) {}