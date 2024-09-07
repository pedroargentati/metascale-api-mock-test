package br.com.metascale.constants;

public enum ProductType {
	MOBILE("mobile"),
	LANDLINE("landline"),
	INTERNET("internet"),
	IPTV("iptv"),
	BUNDLE("bundle"),
	VALUE_ADDED_SERVICE("value_added_service");
	
	private String type;
	
	ProductType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return this.type;
	}
	
	public static ProductType of(String tipo) {
		for( ProductType type : ProductType.values() ) {
			if( type.getType().equalsIgnoreCase( tipo ) ) return type;
		}
		return ProductType.MOBILE;
	}
}
