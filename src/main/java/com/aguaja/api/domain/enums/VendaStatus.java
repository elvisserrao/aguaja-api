package com.aguaja.api.domain.enums;

public enum VendaStatus {
	EM_ABERTO(1),
	EM_ANDAMENTO(2),
	CONCLUIDO(3),
	CANCELADO(4);
	
	private int code;

	private VendaStatus(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
	
	public static VendaStatus valueOf(int code) {
		for(VendaStatus value : VendaStatus.values()){
			if(value.getCode()  == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid VendaStatus code");
	}
}
