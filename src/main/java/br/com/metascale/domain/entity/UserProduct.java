package br.com.metascale.domain.entity;

import java.util.Date;

import br.com.metascale.constants.Status;
import br.com.metascale.domain.UserProductsDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "userproduct")
@Entity(name = "UserProduct")
public class UserProduct {

	@Id
	private String user_id;
	private String product_id;
	private Date start_date;
	private Date end_date;
	private String description;
	private String type;
	private String recurring_period;
	private Float value;

	@Enumerated(EnumType.STRING)
	private Status status;

	public UserProduct() {
	}

	public UserProduct(UserProductsDTO customerProduct) {
		this.status = Status.of(customerProduct.status());
	}

	public void updateCustomerProduct(UserProductsDTO customerProduct) {
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRecurring_period() {
		return recurring_period;
	}

	public void setRecurring_period(String recurring_period) {
		this.recurring_period = recurring_period;
	}

	public Float getValue() {
		return value;
	}

	public void setValue(Float value) {
		this.value = value;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
