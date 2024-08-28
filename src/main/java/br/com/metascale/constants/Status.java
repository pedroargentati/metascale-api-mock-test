package br.com.metascale.constants;

public enum Status {
	
	EM_ANDAMENTO("EM_ANDAMENTO");
	
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
		return Status.EM_ANDAMENTO;
	}

}
