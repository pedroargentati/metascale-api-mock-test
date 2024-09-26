package br.com.metascale.constants;

public enum Type {

	RECURRING("recurring"),
	USAGE("dates"),
	ONE_SHOT("one-shot");
	
	private String type;
	
	Type(String category) {
		this.type = category;
	}
	
	public String getType() {
		return this.type;
	}
	
	public static Type of(String type) {
		for (Type t : Type.values()) {
			if (t.getType().equalsIgnoreCase(type)) return t;
		}
		return Type.RECURRING;
	}
	
}
