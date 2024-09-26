package br.com.metascale.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import br.com.metascale.domain.DescriptionDTO;
import br.com.metascale.domain.PriceDTO;
import br.com.metascale.domain.ProductDTO;
import br.com.metascale.domain.UserProductsDTO;
import br.com.metascale.domain.entity.Product;
import br.com.metascale.domain.entity.ProductDescription;
import br.com.metascale.domain.entity.User;
import br.com.metascale.domain.entity.UserProduct;
import br.com.metascale.repository.ProductDescriptionRepository;
import br.com.metascale.repository.ProductRepository;
import br.com.metascale.repository.UserRepository;
import br.com.metascale.repository.UsersProductRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class UsersProductsService {

	@Autowired
	private UsersProductRepository userProductsRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProductDescriptionRepository productDescriptionRepository;
	
	@Autowired
	private ProductRepository productRepository;

	public List<UserProductsDTO> getAll() {
		return userProductsRepository.findAll()
				.stream()
				.map(UserProductsDTO::new)
				.collect(Collectors.toList());
	}
	
	public List<UserProductsDTO> getAllUserProducts(String user_id) throws NotFoundException {
		Optional<User> user = this.userRepository.findById(user_id);
		if (!user.isPresent()) {
			throw new EntityNotFoundException("Usuário não encontrado.");
		}
		
        List<UserProduct> userProducts = userProductsRepository.findByUserId(user_id);
        if (userProducts == null || userProducts.isEmpty()) {
			throw new NotFoundException();
        }
        
		return userProducts.stream()
				.map(this::toUserProductsDTO)
				.collect(Collectors.toList());
	}
	
	public UserProductsDTO getBydId(String cliente_id) {
		var clienteProduto = userProductsRepository.findById(cliente_id);

		return clienteProduto.isPresent() ? new UserProductsDTO(clienteProduto.get()) : null;
	}

	public UserProductsDTO create(UserProductsDTO cliente) {
		var clienteSaved = userProductsRepository.save(new UserProduct(cliente));

		return new UserProductsDTO(clienteSaved);
	}

	public UserProductsDTO update(UserProductsDTO cliente, String cliente_id) {
		var optionalCliente = userProductsRepository.findById(cliente_id);
		if (!optionalCliente.isPresent()) {
			return null;
		}

		var clienteExistente = optionalCliente.get();

		clienteExistente.updateCustomerProduct(cliente);
		userProductsRepository.save(clienteExistente);

		return new UserProductsDTO(clienteExistente);
	}
	
	private UserProductsDTO toUserProductsDTO(UserProduct userProduct) {
		String product_id = userProduct.getProduct_id();

		List<String> identifiers = this.getIdentifiersByProductId(product_id);

		// Obter as descrições do produto principal
		List<DescriptionDTO> descriptions = this.getProductDescriptions(product_id);

		// Buscar produto principal
		Product product = productRepository
				.findById(product_id)
				.orElseThrow(() -> new IllegalArgumentException("Produto com ID " + product_id + " não encontrado"));

		// Buscar subprodutos e suas descrições
		List<ProductDTO> subProducts = productRepository.findByParentId(product_id)
				.stream()
				.map(subProduct -> {
					List<DescriptionDTO> subProductDescriptions = this.getProductDescriptions(subProduct.getId());
					return new ProductDTO(subProduct, subProductDescriptions);
				}).collect(Collectors.toList());

		return new UserProductsDTO(
				product_id,
				product.getProduct_name(),
				product.getProduct_type().getType(),
				userProduct.getStatus().getStatus(),
				userProduct.getStart_date().toString(),
				identifiers,
				descriptions,
				subProducts,
				new PriceDTO(userProduct)
		);
	}

	private List<DescriptionDTO> getProductDescriptions(String productId) {
		return productDescriptionRepository.findByProductId(productId)
				.stream()
				.map(DescriptionDTO::new)
				.collect(Collectors.toList());
	}

	// Método para retornar os números de telefone dos usuários que possuem determinado produto
	public List<String> getIdentifiersByProductId(String productId) {
		// Buscar os user_id dos usuários que possuem o produto
		List<String> userIds = userProductsRepository.findUserIdsByProductId(productId);

		// Buscar os telefones dos usuários e retornar como lista de Strings
		return userIds.stream()
				.map(userId -> userRepository.findById(userId))
				.filter(Optional::isPresent)
				.map(user -> user.get().getPhone())
				.collect(Collectors.toList());
	}
}
