package br.com.metascale.domain.entity.id;

import java.io.Serializable;
import java.util.Objects;

@SuppressWarnings("serial")
public class UserProductId implements Serializable{
	private String user_id;
	private String product_id;

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

	@Override
	public int hashCode() {
		return Objects.hash(product_id, user_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserProductId other = (UserProductId) obj;
		return Objects.equals(product_id, other.product_id) && Objects.equals(user_id, other.user_id);
	}

}
