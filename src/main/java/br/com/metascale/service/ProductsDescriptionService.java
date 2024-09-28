package br.com.metascale.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.metascale.domain.DescriptionDTO;
import br.com.metascale.domain.ProductDTO;
import br.com.metascale.domain.entity.ProductDescription;
import br.com.metascale.repository.ProductDescriptionRepository;

@Service
public class ProductsDescriptionService {

	@Autowired
	private ProductDescriptionRepository productsDescriptionRepository;
	
	public void updateDescriptions(ProductDTO produto) {
		List<DescriptionDTO> descriptions = produto.descriptions();
		if (descriptions == null || descriptions.isEmpty()) {
			return;
		}
		
		List<ProductDescription> productDescriptions = descriptions.stream()
				.map((description) -> new ProductDescription(produto, description))
				.collect(Collectors.toList());
		
		this.productsDescriptionRepository.saveAll(productDescriptions);
	}
	
}
