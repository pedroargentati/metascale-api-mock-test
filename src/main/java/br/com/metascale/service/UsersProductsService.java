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
		
        List<UserProduct> userProducts = userProductsRepository.findByuser_id(user_id);
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
        List<String> identifiers = List.of("+51939791073");
        List<ProductDescription> listProductDescription = Optional.ofNullable(this.productDescriptionRepository.findByProductId(userProduct.getProduct_id())).orElse(Collections.emptyList());

        List<DescriptionDTO> descriptions = listProductDescription.stream()
        		.map(DescriptionDTO::new)
        		.collect(Collectors.toList());
        
        Optional<Product> product = this.productRepository.findById(userProduct.getProduct_id());
        
        return new UserProductsDTO(
                userProduct.getProduct_id(),
                product.get().getProduct_name(),
                product.get().getProduct_type().getType(),
                userProduct.getStatus().getStatus(),
                userProduct.getStart_date().toString(),
                identifiers,
                descriptions, 
                null,
                new PriceDTO(userProduct) 
        );
    }

}
