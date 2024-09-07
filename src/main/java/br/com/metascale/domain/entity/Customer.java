package br.com.metascale.domain.entity;

import br.com.metascale.domain.CustomerDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "customer")
@Entity(name = "Client")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer customer_id;
	private String customer_name;
	private String customer_email;
	private String customer_phone;
	private String city;
	private String state;

	public Customer() {}

	public Customer(CustomerDTO customers) {
	    this.customer_id = customers.customer_id();
	    this.customer_name = customers.customer_name();
	    this.customer_email = customers.customer_email();
	    this.customer_phone = customers.customer_phone();
	    this.city = customers.city();
	    this.state = customers.state();
	}

	public void updateCustomer(CustomerDTO customer) {
	    if (customer.customer_name() != null) {
	        this.customer_name = customer.customer_name();
	    }

	    if (customer.customer_email() != null) {
	        this.customer_email = customer.customer_email();
	    }

	    if (customer.customer_phone() != null) {
	        this.customer_phone = customer.customer_phone();
	    }

	    if (customer.city() != null) {
	        this.city = customer.city();
	    }

	    if (customer.state() != null) {
	        this.state = customer.state();
	    }
	}

	public Integer getCustomer_id() {
	    return customer_id;
	}

	public void setCustomer_id(Integer customer_id) {
	    this.customer_id = customer_id;
	}

	public String getCustomer_name() {
	    return customer_name;
	}

	public void setCustomer_name(String customer_name) {
	    this.customer_name = customer_name;
	}

	public String getCustomer_email() {
	    return customer_email;
	}

	public void setCustomer_email(String customer_email) {
	    this.customer_email = customer_email;
	}

	public String getCustomer_phone() {
	    return customer_phone;
	}

	public void setCustomer_phone(String customer_phone) {
	    this.customer_phone = customer_phone;
	}

	public String getCity() {
	    return city;
	}

	public void setCity(String city) {
	    this.city = city;
	}

	public String getState() {
	    return state;
	}

	public void setState(String state) {
	    this.state = state;
	}

}
