package br.com.metascale.domain.entity;

import br.com.metascale.domain.UsersDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "users")
@Entity(name = "User")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String email;
	private String phone;
	private String city;
	private String state;

	public User() {}

	public User(UsersDTO customers) {
	    this.id = customers.user_id();
	    this.name = customers.name();
	    this.email = customers.email();
	    this.phone = customers.phone();
	    this.city = customers.city();
	    this.state = customers.state();
	}

	public void updateCustomer(UsersDTO customer) {
	    if (customer.name() != null) {
	        this.name = customer.name();
	    }

	    if (customer.email() != null) {
	        this.email = customer.email();
	    }

	    if (customer.phone() != null) {
	        this.phone = customer.phone();
	    }

	    if (customer.city() != null) {
	        this.city = customer.city();
	    }

	    if (customer.state() != null) {
	        this.state = customer.state();
	    }
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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
