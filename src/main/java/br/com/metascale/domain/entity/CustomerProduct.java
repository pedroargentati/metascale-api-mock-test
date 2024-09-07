package br.com.metascale.domain.entity;

import java.math.BigDecimal;
import java.util.Date;

import br.com.metascale.constants.Status;
import br.com.metascale.domain.CustomerProductDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "customerproduct")
@Entity(name = "CustomerProduct")
public class CustomerProduct {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer customer_product_id;
	private Integer customer_id;
	private Integer product_id;
	private Date association_date;
	private String feedback;
	private BigDecimal price;
	
	@Enumerated(EnumType.STRING)
	private Status status;

	public CustomerProduct() {}

	public CustomerProduct(CustomerProductDTO customerProduct) {
	    this.customer_product_id = customerProduct.customer_product_id();
	    this.customer_id = customerProduct.customer_id();
	    this.product_id = customerProduct.product_id();
	    this.association_date = customerProduct.association_date();
	    this.feedback = customerProduct.feedback();
	    this.status = Status.of(customerProduct.status());
	}

	public void updateCustomerProduct(CustomerProductDTO customerProduct) {
	    if (customerProduct.feedback() != null) {
	        this.feedback = customerProduct.feedback();
	    }
	    
	    if (customerProduct.price() != null) {
	    	this.price = customerProduct.price();
	    }
	}

	public Integer getCustomer_product_id() {
	    return customer_product_id;
	}

	public void setCustomer_product_id(Integer customer_product_id) {
	    this.customer_product_id = customer_product_id;
	}

	public Integer getCustomer_id() {
	    return customer_id;
	}

	public void setCustomer_id(Integer customer_id) {
	    this.customer_id = customer_id;
	}

	public Integer getProduct_id() {
	    return product_id;
	}

	public void setProduct_id(Integer product_id) {
	    this.product_id = product_id;
	}

	public Date getAssociation_date() {
	    return association_date == null ? new Date() : association_date;
	}

	public void setAssociation_date(Date association_date) {
	    this.association_date = association_date;
	}

	public String getFeedback() {
	    return feedback;
	}

	public void setFeedback(String feedback) {
	    this.feedback = feedback;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
}
