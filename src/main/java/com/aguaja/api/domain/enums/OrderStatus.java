package com.aguaja.api.domain.enums;

public enum OrderStatus {
	OPEN(1),
	IN_PROGRESS(2),
	CONCLUDED(3),
	CANCELED(4);
	
	private int code;

	private OrderStatus(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
	
	public static OrderStatus valueOf(int code) {
		for(OrderStatus value : OrderStatus.values()){
			if(value.getCode()  == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid OrderStatus code");
	}
}
