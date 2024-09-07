package br.com.metascale.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.metascale.domain.ProductDTO;
import br.com.metascale.domain.entity.Product;
import br.com.metascale.repository.ProdutoRepository;

@Service
public class ProductService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	public List<ProductDTO> getAll() {
		return produtoRepository.findAll()
				.stream()
				.map(ProductDTO::new)
				.collect(Collectors.toList());
	}
	
	public ProductDTO getBydId(Integer produto_id) {
		var produto = produtoRepository.findById(produto_id);
		
		return produto.isPresent() ? new ProductDTO(produto.get()) : null;
	}
	
	public ProductDTO create(ProductDTO produto) {
		var produtoSaved = produtoRepository.save(new Product(produto));

		return new ProductDTO(produtoSaved);
	}
	
	public ProductDTO update(ProductDTO produto, Integer produto_id) {
		var optionalProduto = produtoRepository.findById(produto_id);
		if (!optionalProduto.isPresent()) {
			return null;
		}
		
		var produtoExistente = optionalProduto.get();
		
		produtoExistente.updateProduto(produto);
		produtoRepository.save(produtoExistente);
		
		return new ProductDTO(produtoExistente);
	}
}