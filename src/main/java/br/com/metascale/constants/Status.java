package br.com.metascale.constants;

public enum Status {
	
	ACTIVE("active"),
	ACTIVATING("activating"),
	SUSPENDED("suspended"),
	CANCELLED("cancelled");
	
	private String status;
	
	Status(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return this.status;
	}
	
	public static Status of(String tipo) {
		for( Status type : Status.values() ) {
			if( type.getStatus().equalsIgnoreCase( tipo ) ) return type;
		}
		return Status.ACTIVE;
	}

}
