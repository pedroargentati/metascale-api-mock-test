package br.com.metascale.domain;

import br.com.metascale.constants.Category;

public record ProductDescriptionDTO(
		String id,
		String text,
		String url,
		Category category
) {}
