package br.com.metascale.domain.entity;

import java.util.Set;

import br.com.metascale.constants.ProductType;
import br.com.metascale.domain.ProductDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Table(name = "product")
@Entity(name = "Product")
public class Product {

    @Id
    private String id;
    
    private String product_name;
    
    @Enumerated(EnumType.STRING)
    private ProductType product_type;
    
    private String parent_id;
    
    // Relacionamento com sub-produtos
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    private Set<Product> subProducts;
    
    public Product() {}

    public Product(ProductDTO produto) {
        this.id = produto.id();
        this.product_name = produto.product_name();
        this.product_type = ProductType.of(produto.product_type());
    }

    // Getters e Setters
    
    public void updateProduto(ProductDTO produto) {
        if (produto.product_name() != null) {
            this.product_name = produto.product_name();
        }

        if (produto.product_type() != null) {
            this.product_type = ProductType.of(produto.product_type());
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

	public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public ProductType getProduct_type() {
        return product_type;
    }

    public void setProduct_type(ProductType product_type) {
        this.product_type = product_type;
    }

    public Set<Product> getSubProducts() {
        return subProducts;
    }

    public void setSubProducts(Set<Product> subProducts) {
        this.subProducts = subProducts;
    }
}
