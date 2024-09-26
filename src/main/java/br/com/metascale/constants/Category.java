package br.com.metascale.constants;

public enum Category {

	GENERAL("general"),
	DATES("dates"),
	PROMOTION("promotion");
	
	private String category;
	
	Category(String category) {
		this.category = category;
	}
	
	public String getType() {
		return this.category;
	}
	
	public static Category of(String category) {
		for (Category type : Category.values()) {
			if (type.getType().equalsIgnoreCase(category)) return type;
		}
		return Category.GENERAL;
	}
	
}
