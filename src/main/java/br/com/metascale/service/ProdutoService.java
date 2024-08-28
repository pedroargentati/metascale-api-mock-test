package br.com.metascale.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.metascale.domain.ProdutoDTO;
import br.com.metascale.domain.entity.Produto;
import br.com.metascale.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	public List<ProdutoDTO> getAll() {
		return produtoRepository.findAll()
				.stream()
				.map(ProdutoDTO::new)
				.collect(Collectors.toList());
	}
	
	public ProdutoDTO getBydId(Integer produto_id) {
		var produto = produtoRepository.findById(produto_id);
		
		return produto.isPresent() ? new ProdutoDTO(produto.get()) : null;
	}
	
	public ProdutoDTO create(ProdutoDTO produto) {
		var produtoSaved = produtoRepository.save(new Produto(produto));

		return new ProdutoDTO(produtoSaved);
	}
	
	public ProdutoDTO update(ProdutoDTO produto, Integer produto_id) {
		var optionalProduto = produtoRepository.findById(produto_id);
		if (!optionalProduto.isPresent()) {
			return null;
		}
		
		var produtoExistente = optionalProduto.get();
		
		produtoExistente.updateProduto(produto);
		produtoRepository.save(produtoExistente);
		
		return new ProdutoDTO(produtoExistente);
	}
	
}
