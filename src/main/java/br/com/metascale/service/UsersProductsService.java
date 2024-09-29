package br.com.metascale.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import br.com.metascale.core.exceptions.RecordNotFoundException;
import br.com.metascale.domain.DescriptionDTO;
import br.com.metascale.domain.PriceDTO;
import br.com.metascale.domain.ProductDTO;
import br.com.metascale.domain.UserProductsDTO;
import br.com.metascale.domain.entity.Product;
import br.com.metascale.domain.entity.User;
import br.com.metascale.domain.entity.UserProduct;
import br.com.metascale.domain.entity.id.UserProductId;
import br.com.metascale.repository.ProductDescriptionRepository;
import br.com.metascale.repository.ProductRepository;
import br.com.metascale.repository.UserRepository;
import br.com.metascale.repository.UsersProductRepository;

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
				.map(userProduct -> {
					try {
						return toUserProductsDTO(userProduct);
					} catch (RecordNotFoundException e) {
						e.printStackTrace();
					}
					return null;
				})
				.collect(Collectors.toList());
	}
	
	public List<UserProductsDTO> getAllUserProducts(String user_id) throws NotFoundException, RecordNotFoundException {
		Optional<User> user = this.userRepository.findById(user_id);
		if (!user.isPresent()) {
			throw new RecordNotFoundException("Usuário não encontrado.");
		}
		
		List<UserProduct> userProducts = userProductsRepository.findByUserId(user_id);
		if (userProducts == null || userProducts.isEmpty()) {
			throw new NotFoundException();
		}

		return userProducts.stream()
				.map(userProduct -> {
					try {
						return toUserProductsDTO(userProduct);
					} catch (RecordNotFoundException e) {
						e.printStackTrace();
					}
					return null;
				})
				.collect(Collectors.toList());
	}
	
	public UserProductsDTO getById(String user_id, String product_id) throws RecordNotFoundException {
		var userProduct = userProductsRepository.findById(new UserProductId(user_id, product_id));

		return userProduct.isPresent() ? this.toUserProductsDTO(userProduct.get()) : null;
	}

	public UserProductsDTO create(UserProductsDTO userProducts) {
		var clienteSaved = userProductsRepository.save(new UserProduct(userProducts));

		return new UserProductsDTO(clienteSaved);
	}

	public UserProductsDTO update(UserProductsDTO user, String user_id) {
		var userProducts = userProductsRepository.findById(new UserProductId(user_id, user.id()));
		if (!userProducts.isPresent()) {
			return null;
		}
		
		var existingUserProduct = userProducts.get();

		existingUserProduct.updateUsersProduct(user);
		userProductsRepository.save(existingUserProduct);

		return new UserProductsDTO(existingUserProduct);
	}
	
	private UserProductsDTO toUserProductsDTO(UserProduct userProduct) throws RecordNotFoundException {
		String product_id = userProduct.getProduct_id();

		List<String> identifiers = this.getIdentifiersByProductId(product_id);

		// Obter as descrições do produto principal
		List<DescriptionDTO> descriptions = this.getProductDescriptions(product_id);

		// Buscar produto principal
		Product product = productRepository
				.findById(product_id)
				.orElseThrow(() -> new RecordNotFoundException("Produto com ID " + product_id + " não encontrado"));

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

	public List<DescriptionDTO> getProductDescriptions(String productId) {
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
