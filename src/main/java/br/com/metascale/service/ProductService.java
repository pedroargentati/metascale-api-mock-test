package br.com.metascale.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.metascale.core.exceptions.RecordNotFoundException;
import br.com.metascale.domain.DescriptionDTO;
import br.com.metascale.domain.ProductDTO;
import br.com.metascale.domain.entity.Product;
import br.com.metascale.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository produtoRepository;
	
	@Autowired
	private UsersProductsService usersProductsService;
	
	@Autowired
	private ProductsDescriptionService productsDescriptionService;

	@Autowired
	private UsersProductsService productsService;
	
	public List<ProductDTO> getAll() {
		return produtoRepository.findAll()
				.stream()
				.map(ProductDTO::new)
				.collect(Collectors.toList());
	}
	
	public ProductDTO getById(String product_id) throws RecordNotFoundException {
		var produto = produtoRepository.findById(product_id);
		if (!produto.isPresent()) {
			throw new RecordNotFoundException("Produto com o código ".concat(product_id).concat(" não encontrado."));
		}
		
		List<DescriptionDTO> descriptions = usersProductsService.getProductDescriptions(product_id);
		return new ProductDTO(produto.get(), descriptions);
	}
	
	public ProductDTO create(ProductDTO produto) {
		var produtoSaved = produtoRepository.save(new Product(produto));

		return new ProductDTO(produtoSaved);
	}
	
	public ProductDTO update(ProductDTO produto, String product_id) throws RecordNotFoundException {
		var optionalProduto = produtoRepository.findById(product_id);
		if (!optionalProduto.isPresent()) {
			throw new RecordNotFoundException("Produto com o código ".concat(product_id).concat(" não encontrado."));
		}
		
		var produtoExistente = optionalProduto.get();
		
		produtoExistente.updateProduto(produto);
		produtoRepository.save(produtoExistente);
		
		productsDescriptionService.updateDescriptions(produto);
		
		List<DescriptionDTO> descriptions = this.productsService.getProductDescriptions(product_id);

		return new ProductDTO(produtoExistente, descriptions);
	}
}